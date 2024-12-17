package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.MobilePhoneDetails;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MobilePhoneDetailsRepository extends JpaRepository<MobilePhoneDetails, Long> {
    List<MobilePhoneDetails> findByUserId(Long userId);
}