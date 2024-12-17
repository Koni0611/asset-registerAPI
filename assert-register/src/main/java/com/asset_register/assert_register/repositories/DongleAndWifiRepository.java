package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.DongleAndWifi;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DongleAndWifiRepository extends JpaRepository<DongleAndWifi, Long> {
    List<DongleAndWifi> findByUserId(Long userId);
}