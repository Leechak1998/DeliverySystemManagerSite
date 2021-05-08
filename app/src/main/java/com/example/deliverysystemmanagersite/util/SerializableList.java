package com.example.deliverysystemmanagersite.util;

import com.example.deliverysystemmanagersite.model.Driver;
import com.example.deliverysystemmanagersite.model.Packages;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.Vendor;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SerializableList implements Serializable {
    private List<Packages> packagesList;
    private List<Driver> driverList;
    private List<Vendor> vendorList;
    private List<Site> siteList;

    public List<Packages> getPackagesList(){
        return packagesList;
    }

    public void setPackagesList(List<Packages> list){
        this.packagesList = list;
    }

    public List<Driver> getDriverList(){
        return driverList;
    }

    public void setDriverList(List<Driver> list){
        this.driverList = list;
    }


    public List<Vendor> getVendorList() {
        return vendorList;
    }

    public void setVendorList(List<Vendor> vendorList) {
        this.vendorList = vendorList;
    }

    public List<Site> getSiteList() {
        return siteList;
    }

    public void setSiteList(List<Site> siteList) {
        this.siteList = siteList;
    }
}
