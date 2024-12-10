package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class DongleAndWifi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String dongleOrRouterManfacturer;

    @Column
    private String dongleOrRouterModel;

    @Column
    private String dongleOrRouterImeiNumber;

    @Column
    private String dongleOrRouterSerialNumber;

    @Column
    private String mobileNumber;

    @Column
    private String mobileNumberOperator;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDongleOrRouterManfacturer() {
        return dongleOrRouterManfacturer;
    }

    public void setDongleOrRouterManfacturer(String dongleOrRouterManfacturer) {
        this.dongleOrRouterManfacturer = dongleOrRouterManfacturer;
    }

    public String getDongleOrRouterModel() {
        return dongleOrRouterModel;
    }

    public void setDongleOrRouterModel(String dongleOrRouterModel) {
        this.dongleOrRouterModel = dongleOrRouterModel;
    }

    public String getDongleOrRouterImeiNumber() {
        return dongleOrRouterImeiNumber;
    }

    public void setDongleOrRouterImeiNumber(String dongleOrRouterImeiNumber) {
        this.dongleOrRouterImeiNumber = dongleOrRouterImeiNumber;
    }

    public String getDongleOrRouterSerialNumber() {
        return dongleOrRouterSerialNumber;
    }

    public void setDongleOrRouterSerialNumber(String dongleOrRouterSerialNumber) {
        this.dongleOrRouterSerialNumber = dongleOrRouterSerialNumber;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMobileNumberOperator() {
        return mobileNumberOperator;
    }

    public void setMobileNumberOperator(String mobileNumberOperator) {
        this.mobileNumberOperator = mobileNumberOperator;
    }

}
