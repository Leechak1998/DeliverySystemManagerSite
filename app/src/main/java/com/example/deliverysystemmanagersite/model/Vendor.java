package com.example.deliverysystemmanagersite.model;

public class Vendor {
    private String vendor_name;
    private String tel;
    private int vendor_id;
    private String email;
    private String address;

    public Vendor(String Vendor_Name,String Tel,int Vendor_Id, String Email, String Address){
        this.vendor_name = Vendor_Name;
        this.vendor_id = Vendor_Id;
        this.tel = Tel;
        this.email = Email;
        this.address = Address;
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getTel() {
        return tel;
    }

    public String getVendor_name() {
        return vendor_name;
    }
}
