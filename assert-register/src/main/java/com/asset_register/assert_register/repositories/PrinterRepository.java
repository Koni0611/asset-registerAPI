package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.Printer;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterRepository extends JpaRepository<Printer, Long> {

    List<Printer> findByUserId(Long userId);
}