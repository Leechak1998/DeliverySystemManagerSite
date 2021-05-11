package com.example.deliverysystemmanagersite.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Packages {
    private int packageId;
    private String driver;
    private String vendor;
    private String sendDate;
    private String departure;
    private String destination;
    private String date;
    private String state;
    private String readingStatus;

    public Packages(int packageId,String departure, String driver, String sendDate, String vendor, String destination, String date, String state, String readingStatus){
        this.packageId =  packageId;
        this.driver = driver;
        this.sendDate = sendDate;
        this.vendor = vendor;
        this.destination = destination;
        this.departure = departure;
        this.state = state;
        this.date = date;
        this.readingStatus = readingStatus;
    }

    public Packages(int packageId, String driver, String vendor, String destination,String state){
        this.packageId =  packageId;
        this.driver = driver;
        this.vendor = vendor;
        this.departure = vendor;
        this.state = state;
        this.destination = destination;
    }

    public int getPackageId() {
        return packageId;
    }

    public String getDriver() {
        return driver;
    }

    public String getVendor() {
        return vendor;
    }

    public String getDestination() {
        return destination;
    }


    public String getDeparture() {
        return departure;
    }

    public String getState() {
        return state;
    }
    public String getStringState(String state){
        HashMap<String,String> map = new HashMap<String,String>(){{
            put("0","pending");
            put("1","delivering");
            put("2","delivered");
        }};
        return  map.get(state);
    }

//    public Date getDelivery_date(){
//        return delivery_date;
//    }

    public String getDate(){
        return date;
    }

    public String getReadingStatus() {
        return readingStatus;
    }

    public void setReadingStatus(String readingStatus) {
        this.readingStatus = readingStatus;
    }

    public String getSendDate() {
        return sendDate;
    }

    public void setSendDate(String sendDate) {
        this.sendDate = sendDate;
    }
}
