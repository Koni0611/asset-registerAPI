package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.ComputerDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerDetailsRepository extends JpaRepository<ComputerDetails, Long> {
    List<ComputerDetails> findByUserId(Long userId);
}