package com.fintech.common.entity;

import com.fintech.common.enums.UserAccountStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UserEntity extends BaseEntity {
    @Id
    @SequenceGenerator(name = "user_seq", sequenceName = "user_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
    private Long id;

    @NotBlank(message = "First name is required")
    @Column(name = "first_name")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Column(name = "last_name")
    private String lastName;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    @Column(name = "email", unique = true)
    private String email;

    @NotBlank(message = "SSN is required")
    @Column(name = "ssn", unique = true)
    private String ssn;

    @Pattern(regexp = "^\\+?[1-9]\\d{1,14}$", message = "Phone number must be valid according to international formats")
    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "address")
    private String address;

    @Column(name = "user_account_status")
    @Enumerated(EnumType.STRING)
    private UserAccountStatus status;

    @NotBlank(message = "Security answer is required")
    @Column(name = "security_answer")
    private String securityAnswer;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<AccountEntity> accountEntityList;
}