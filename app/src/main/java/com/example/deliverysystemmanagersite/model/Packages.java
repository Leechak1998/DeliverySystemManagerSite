package com.example.deliverysystemmanagersite.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Packages {
    private int packageId;
    private String driver;
    private String vendor;
    private String tel;
    private String departure;
    private String destination;
    private Date delivery_date;
    private String date;
    private String state;

    public Packages(int packageId,String departure, String driver, String tel, String vendor, String destination, Date delivery_date, String state){
        this.packageId =  packageId;
        this.driver = driver;
        this.tel = tel;
        this.vendor = vendor;
        this.destination = destination;
        this.delivery_date = delivery_date;
        this.departure = departure;
        this.state = state;
        SimpleDateFormat ft = new SimpleDateFormat("MM/dd/yyyy");
        date = ft.format(delivery_date);
    }

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

    public String getTel() {
        return tel;
    }

    public String getDeparture() {
        return departure;
    }

    public String getState() {
        return state;
    }

    public Date getDelivery_date(){
        return delivery_date;
    }

    public String getDate(){
        return date;
    }

    }
