package com.example.deliverysystemmanagersite.model;

public class Packages {
    private int packageId;
    private String driver;
    private String vendor;
    private String destination;

    public Packages(int packageId, String driver, String vendor, String destination){
        this.packageId =  packageId;
        this.driver = driver;
        this.vendor = vendor;
        this.destination = destination;
    }


    public int getPackageId() {
        return packageId;
    }

    public String getDriver() {
        return "Driver: " + driver;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDestination() {
        return destination;
    }
}
