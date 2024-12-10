package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class Printer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String printerManfacturer;

    @Column
    private String printerModel;

    @Column
    private String printerSerilaNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrinterManfacturer() {
        return printerManfacturer;
    }

    public void setPrinterManfacturer(String printerManfacturer) {
        this.printerManfacturer = printerManfacturer;
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

}
