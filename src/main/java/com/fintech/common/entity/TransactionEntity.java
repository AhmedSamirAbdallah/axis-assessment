package com.fintech.common.entity;

import com.fintech.common.enums.TransactionType;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionEntity extends BaseEntity{

    @Id
    @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
    private Long id;

    @NotNull(message = "Account is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @Column(name = "transaction_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero")
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "transaction_date", nullable = false)
    private LocalDateTime transactionDate;

    @Column(name = "description")
    private String description;

    @Column(name = "reference_id", unique = true)
    private String referenceId;

    @Column(name = "currency", nullable = false)
    private String currency;
}
