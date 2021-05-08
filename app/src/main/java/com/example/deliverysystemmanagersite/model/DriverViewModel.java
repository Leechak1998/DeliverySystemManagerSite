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
        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String dataList = htc.doGet("http://10.0.2.2:8339/selectDriverAll");
            try{
                JSONArray driverList = new JSONArray(dataList);
                for (int i=0; i<driverList.length(); i++){
                    int driverId = driverList.getJSONObject(i).getInt("driverId");
                    String driverName = driverList.getJSONObject(i).getString("driverName");
                    String email = driverList.getJSONObject(i).getString("email");
                    String tel = driverList.getJSONObject(i).getString("telephoneNumber");
                    DriverList.add(new Driver(driverName,tel,driverId,email));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public List<Driver> getText() {
        return DriverList;
    }

}