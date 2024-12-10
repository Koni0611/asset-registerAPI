package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.ComputerDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComputerDetailsRepository extends JpaRepository<ComputerDetails, Long> {
}