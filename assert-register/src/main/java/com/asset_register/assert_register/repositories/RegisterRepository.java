package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.Register;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RegisterRepository extends JpaRepository<Register, Long> {
    Optional<Register> findByIdentityNumber(String identityNumber);

    Optional<Register> findByEmail(String email);
}