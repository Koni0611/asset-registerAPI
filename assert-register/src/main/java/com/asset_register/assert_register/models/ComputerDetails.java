package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class ComputerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String computerForm;

    @Column(nullable = false)
    private String computerManufacturer;

    @Column(nullable = false)
    private String computerModelName;

    @Column(nullable = false)
    private String computerModelNumber;

    @Column(nullable = false)
    private String serialNumber;

    @Column(nullable = false)
    private String operationSystem;

    @Column(nullable = false)
    private String processor;

    @Column(nullable = false)
    private String randomAccesMemory;

    @Column(nullable = false)
    private String hardDriveCapacity;

    @Column(nullable = false)
    private String monitorManufacturer;

    @Column(nullable = false)
    private String keyboardManufacturer;

    @Column(nullable = false)
    private String mouseManufacturer;

    @Column(nullable = true)
    private Long userId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getComputerForm() {
        return computerForm;
    }

    public void setComputerForm(String computerForm) {
        this.computerForm = computerForm;
    }

    public String getComputerManufacturer() {
        return computerManufacturer;
    }

    public void setComputerManufacturer(String computerManufacturer) {
        this.computerManufacturer = computerManufacturer;
    }

    public String getComputerModelName() {
        return computerModelName;
    }

    public void setComputerModelName(String computerModelName) {
        this.computerModelName = computerModelName;
    }

    public String getComputerModelNumber() {
        return computerModelNumber;
    }

    public void setComputerModelNumber(String computerModelNumber) {
        this.computerModelNumber = computerModelNumber;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getOperationSystem() {
        return operationSystem;
    }

    public void setOperationSystem(String operationSystem) {
        this.operationSystem = operationSystem;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getRandomAccesMemory() {
        return randomAccesMemory;
    }

    public void setRandomAccesMemory(String randomAccesMemory) {
        this.randomAccesMemory = randomAccesMemory;
    }

    public String getHardDriveCapacity() {
        return hardDriveCapacity;
    }

    public void setHardDriveCapacity(String hardDriveCapacity) {
        this.hardDriveCapacity = hardDriveCapacity;
    }

    public String getMonitorManufacturer() {
        return monitorManufacturer;
    }

    public void setMonitorManufacturer(String monitorManufacturer) {
        this.monitorManufacturer = monitorManufacturer;
    }

    public String getKeyboardManufacturer() {
        return keyboardManufacturer;
    }

    public void setKeyboardManufacturer(String keyboardManufacturer) {
        this.keyboardManufacturer = keyboardManufacturer;
    }

    public String getMouseManufacturer() {
        return mouseManufacturer;
    }

    public void setMouseManufacturer(String mouseManufacturer) {
        this.mouseManufacturer = mouseManufacturer;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

}
