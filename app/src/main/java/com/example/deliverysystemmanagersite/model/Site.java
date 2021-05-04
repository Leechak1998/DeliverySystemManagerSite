package com.example.deliverysystemmanagersite.model;

public class Site {
    private int site_id;
    private String site_name;
    private String tel;
    private String email;
    private String address;
    private String admin;
    public Site(String Site_Name,String Tel,int Site_Id, String Email, String Address, String Admin){
        this.site_name = Site_Name;
        this.site_id = Site_Id;
        this.tel = Tel;
        this.email = Email;
        this.address = Address;
        this.admin = Admin;
    }

    public int getSite_id() {
        return site_id;
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

    public String getSite_name() {
        return site_name;
    }

    public String getAdmin() {
        return admin;
    }
}
