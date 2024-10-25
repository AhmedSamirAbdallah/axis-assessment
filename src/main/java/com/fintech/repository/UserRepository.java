package com.fintech.repository;

import com.fintech.common.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    Boolean existsByEmail(String email);

    Boolean existsBySsn(String Ssn);
}
