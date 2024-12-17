package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class DongleAndWifi {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String dongleOrRouterManfacturer;

    @Column(nullable = false)
    private String dongleOrRouterModel;

    @Column(nullable = false)
    private String dongleOrRouterImeiNumber;

    @Column(nullable = false)
    private String dongleOrRouterSerialNumber;

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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public DongleAndWifi save(DongleAndWifi dongleWifi) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
