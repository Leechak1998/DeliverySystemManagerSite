package com.example.deliverysystemmanagersite.model;

public class Driver {

    private String driver_name;
    private String tel;
    private int driver_id;
    private String email;

    public Driver(String driver_name, String tel, int driver_id, String email){
        this.driver_name = driver_name;
        this.driver_id = driver_id;
        this.email = email;
        this.tel = tel;
    }

    //Manager inite details of new driver.
    public Driver(String driver_name, String email){
        this.driver_name = driver_name;
        this.email = email;
    }

    public String getDriver_name(){
        return this.driver_name;
    }

    public String getTel(){
        return this.tel;
    }

    public int getDriver_id(){
        return this.driver_id;
    }

    public String getEmail(){
        return this.email;
    }

    public void setName(){
        System.out.println("Change name");
    }

    public void setTel(){
        System.out.println("Change tel");
    }

    public void setEmail(){
        System.out.println("Change email");
    }

}

