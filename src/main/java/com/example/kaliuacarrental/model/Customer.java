package com.example.kaliuacarrental.model;

import java.sql.Date;

public class Customer {
    private String customerLicense;
    private Date customerLicenseDate;
    private String customerName;
    private String customerEmail;
    private int customerPhoneNumber;
    private String customerAddress;
    private int customerZip;
    private String customerCity;

    public Customer() {
    }

    public Customer(String customerLicense, Date customerLicenseDate, String customerName, String customerEmail, int customerPhoneNumber, String customerAddress, int customerZip, String customerCity) {
        this.customerLicense = customerLicense;
        this.customerLicenseDate = customerLicenseDate;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
        this.customerZip = customerZip;
        this.customerCity = customerCity;
    }

    public String getCustomerLicense() {
        return customerLicense;
    }

    public void setCustomerLicense(String customerLicense) {
        this.customerLicense = customerLicense;
    }

    public Date getCustomerLicenseDate() {
        return customerLicenseDate;
    }

    public void setCustomerLicenseDate(Date customerLicenseDate) {
        this.customerLicenseDate = customerLicenseDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber(int customerPhoneNumber) {
        this.customerPhoneNumber = customerPhoneNumber;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public int getCustomerZip() {
        return customerZip;
    }

    public void setCustomerZip(int customerZip) {
        this.customerZip = customerZip;
    }

    public String getCustomerCity() {
        return customerCity;
    }

    public void setCustomerCity(String customerCity) {
        this.customerCity = customerCity;
    }
}
