package com.example.deliverysystemmanagersite.model;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.ViewModel;

public class DriverViewModel extends ViewModel {

    private List<Driver> DriverList = new ArrayList<>();

    public DriverViewModel(){
        init();
    }
    public void init(){
        System.out.println("初始化driver");
        Driver driver1 = new Driver("Mike","1231",1,"123@sonton.ac.uk");
        Driver driver2 = new Driver("John","1234",2,"124@sonton.ac.uk");
        Driver driver3 = new Driver("123","1235",3,"125@sonton.ac.uk");
        Driver driver4 = new Driver("Jack","1236",4,"122@sonton.ac.uk");
        Driver driver5 = new Driver("Mary","1238",5,"121@sonton.ac.uk");

        DriverList.add(driver1);
        DriverList.add(driver2);
        DriverList.add(driver3);
        DriverList.add(driver4);
        DriverList.add(driver5);
    }
    public List<Driver> getText() {
        return DriverList;
    }

}