package com.asset_register.assert_register.models;

import jakarta.persistence.*;

@Entity
public class ComputerDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String computerForm;

    @Column
    private String computerManufacturer;

    @Column
    private String computerModelName;

    @Column
    private String computerModelNumber;

    @Column
    private String serialNumber;

    @Column
    private String operationSystem;

    @Column
    private String processor;

    @Column
    private String randomAccesMemory;

    @Column
    private String hardDriveCapacity;

    @Column
    private String monitorManufacturer;

    @Column
    private String keyboardManufacturer;

    @Column
    private String mouseManufacturer;

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

}
