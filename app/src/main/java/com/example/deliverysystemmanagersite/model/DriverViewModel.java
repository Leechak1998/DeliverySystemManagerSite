package com.example.deliverysystemmanagersite.model;

import java.util.ArrayList;
import java.util.List;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverViewModel extends ViewModel {

    private List<Driver> DriverList = new ArrayList<>();

    public DriverViewModel(){
        init();
    }
    public void init(){
//        System.out.println("初始化driver");
//        Driver driver1 = new Driver("Mike","1231",1,"123@sonton.ac.uk");
//        Driver driver2 = new Driver("John","1234",2,"124@sonton.ac.uk");
//        Driver driver3 = new Driver("123","1235",3,"125@sonton.ac.uk");
//        Driver driver4 = new Driver("Jack","1236",4,"122@sonton.ac.uk");
//        Driver driver5 = new Driver("Mary","1238",5,"121@sonton.ac.uk");
//
//        DriverList.add(driver1);
//        DriverList.add(driver2);
//        DriverList.add(driver3);
//        DriverList.add(driver4);
//        DriverList.add(driver5);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpConnectionUtil htc = new HttpConnectionUtil();
                String dataList = htc.doGet("http://10.0.2.2:8339/getDriver");
                try{
                    JSONObject JSON_obj = new JSONObject(dataList);
                    JSONArray driverList = JSON_obj.getJSONArray("drivers");
//                    (String driver_name, String tel, int driver_id, String email)
                    for (int i=0; i<driverList.length(); i++){
                        int driverId = driverList.getJSONObject(i).getInt("driverId");
                        String driverName = driverList.getJSONObject(i).getString("driverName");
                        String email = driverList.getJSONObject(i).getString("email");
                        String tel = driverList.getJSONObject(i).getString("tel");
                        DriverList.add(new Driver(driverName,tel,driverId,email));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public List<Driver> getText() {
        return DriverList;
    }

}