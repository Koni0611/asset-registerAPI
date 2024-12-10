package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.MobilePhoneDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MobilePhoneDetailsRepository extends JpaRepository<MobilePhoneDetails, Long> {
}