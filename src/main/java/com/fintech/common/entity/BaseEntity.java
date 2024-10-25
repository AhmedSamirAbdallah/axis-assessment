package com.fintech.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public abstract class BaseEntity {

    @Column(name = "deleted")
    private Boolean deleted = false;

    @Column(name = "modified_date")
    @UpdateTimestamp
    private LocalDateTime modifiedDate;

    @Column(name = "modified_by")
    @LastModifiedBy
    private String modifiedBy;

    @Column(name = "created_date")
    @CreationTimestamp
    private LocalDateTime createdDate;

    @Column(name = "created_by")
    @CreatedBy
    private String createdBy;


}
