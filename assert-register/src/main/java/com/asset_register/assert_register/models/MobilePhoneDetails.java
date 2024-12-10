package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class MobilePhoneDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String mobilePhoneManufacturer;

    @Column
    private String mobilePhoneModel;

    @Column
    private String mobilePhoneImeiNumber;

    @Column
    private String mobilePhoneSerialNumber;

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

}
