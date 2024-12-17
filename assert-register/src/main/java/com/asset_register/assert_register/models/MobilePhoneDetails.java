package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class MobilePhoneDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String mobilePhoneManufacturer;

    @Column(nullable = false)
    private String mobilePhoneModel;

    @Column(nullable = false)
    private String mobilePhoneImeiNumber;

    @Column(nullable = false)
    private String mobilePhoneSerialNumber;

    @Column(nullable = false)
    private String mobileNumber;

    @Column(nullable = false)
    private String mobileNumberOperator;

    @Column(nullable = true)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMobilePhoneManufacturer() {
        return mobilePhoneManufacturer;
    }

    public void setMobilePhoneManufacturer(String mobilePhoneManufacturer) {
        this.mobilePhoneManufacturer = mobilePhoneManufacturer;
    }

    public String getMobilePhoneModel() {
        return mobilePhoneModel;
    }

    public void setMobilePhoneModel(String mobilePhoneModel) {
        this.mobilePhoneModel = mobilePhoneModel;
    }

    public String getMobilePhoneImeiNumber() {
        return mobilePhoneImeiNumber;
    }

    public void setMobilePhoneImeiNumber(String mobilePhoneImeiNumber) {
        this.mobilePhoneImeiNumber = mobilePhoneImeiNumber;
    }

    public String getMobilePhoneSerialNumber() {
        return mobilePhoneSerialNumber;
    }

    public void setMobilePhoneSerialNumber(String mobilePhoneSerialNumber) {
        this.mobilePhoneSerialNumber = mobilePhoneSerialNumber;
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
