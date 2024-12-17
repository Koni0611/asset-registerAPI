package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String printerManufacturer;

    @Column(nullable = false)
    private String printerModel;

    @Column(nullable = false)
    private String printerSerilaNumber;

    @Column(nullable = true)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrinterManufacturer() {
        return printerManufacturer;
    }

    public void setPrinterManufacturer(String printerManufacturer) {
        this.printerManufacturer = printerManufacturer;
    }

    public String getPrinterModel() {
        return printerModel;
    }

    public void setPrinterModel(String printerModel) {
        this.printerModel = printerModel;
    }

    public String getPrinterSerilaNumber() {
        return printerSerilaNumber;
    }

    public void setPrinterSerilaNumber(String printerSerilaNumber) {
        this.printerSerilaNumber = printerSerilaNumber;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
