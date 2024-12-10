package com.asset_register.assert_register.repositories;

import com.asset_register.assert_register.models.Printer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrinterRepository extends JpaRepository<Printer, Long> {
}