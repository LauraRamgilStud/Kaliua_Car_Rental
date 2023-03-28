package com.example.kaliuacarrental.model;
import java.sql.Date;

public class Car {
    private String carLicense;
    private String carStatus;
    private String carBrand;
    private String carModel;
    private String carFuelType;
    private Date carRegistrationDate;
    private int carKmDriven;
    private int groupID;

    public Car() {
    }

    public Car(String carLicense, String carStatus, String carBrand, String carModel, String carFuelType, Date carRegistrationDate, int carKmDriven, int groupID) {
        this.carLicense = carLicense;
        this.carStatus = carStatus;
        this.carBrand = carBrand;
        this.carModel = carModel;
        this.carFuelType = carFuelType;
        this.carRegistrationDate = carRegistrationDate;
        this.carKmDriven = carKmDriven;
        this.groupID = groupID;
    }

    public String getCarLicense() {
        return carLicense;
    }

    public void setCarLicense(String carLicense) {
        this.carLicense = carLicense;
    }

    public String getCarStatus() {
        return carStatus;
    }

    public void setCarStatus(String carStatus) {
        this.carStatus = carStatus;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarFuelType() {
        return carFuelType;
    }

    public void setCarFuelType(String carFuelType) {
        this.carFuelType = carFuelType;
    }

    public Date getCarRegistrationDate() {
        return carRegistrationDate;
    }

    public void setCarRegistrationDate(Date carRegistrationDate) {
        this.carRegistrationDate = carRegistrationDate;
    }

    public int getCarKmDriven() {
        return carKmDriven;
    }

    public void setCarKmDriven(int carKmDriven) {
        this.carKmDriven = carKmDriven;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

}

