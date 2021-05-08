package com.example.deliverysystemmanagersite.model;

import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class AddPackagesModel {

    private List<Vendor> list_departure = new ArrayList<>();
    private List<Site> list_site = new ArrayList<>();
    private List<Driver> list_driver = new ArrayList<>();

    final private static int ADD_FROM = 0;
    final private static int ADD_TO = 1;
    final private static int ADD_DRIVER = 2;

    public AddPackagesModel(){
        //requestData

        Vendor v1 = new Vendor("Vendor1", "123", 0, "123@qq", "Hampton");
        Site s1 = new Site("Site1", "321", 0, "321@qq", "Crossings", "jack");
        Driver d1 = new Driver("jackson", "12333", 0 , "jack@qq");

        Vendor v2 = new Vendor("Vendor2", "123", 0, "123@qq", "Hampton");
        Site s2 = new Site("Site2", "321", 0, "321@qq", "Crossings", "jack");
        Driver d2 = new Driver("jackson2", "12333", 0 , "jack@qq");

        list_departure.add(v1);
        list_site.add(s1);
        list_driver.add(d1);
        list_departure.add(v2);
        list_site.add(s2);
        list_driver.add(d2);

    }

    public List<?> init_list(int index){
        switch (index){
            case ADD_FROM:
                List<Vendor> list1 = list_departure;
                return list1;
            case ADD_TO:
                List<Site> list2 = list_site;
                return list2;
            case ADD_DRIVER:
                List<Driver> list3 = list_driver;
                return list3;
            default:
                return null;
        }
    }

    //获取所有Departure，destination，driver的信息。
    public void requestData(){
        HttpConnectionUtil htc = new HttpConnectionUtil();
        String departure_data = htc.doGet("http://10.0.2.2:8339/....");
        String destination_data = htc.doGet("http://10.0.2.2:8339/....");
        String driver_data = htc.doGet("http://10.0.2.2:8339/....");

        try {
            JSONObject jsonObject_departure = new JSONObject(departure_data);
            JSONObject jsonObject_destination = new JSONObject(destination_data);
            JSONObject jsonObject_driver = new JSONObject(driver_data);

            //JSONArray drivers = jsonObject_driver.getJSONArray("drivers");
            //for (int i=0; i<drivers.length(); i++){
            //  listFrom.add(drivers.getJSONObject(i).getString("usernmae"));
            // }

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        list_departure.add(v1);
//        list_site.add(s1);
//        list_driver.add(d1);
    }

    //vendor_id state send_data received_data departure destination
    public void sendPackageDataToServer(String dep, String des, String dri, int state){
        HttpConnectionUtil htc = new HttpConnectionUtil();
        htc.doGet("http://10.0.2.2:8338/...");
    }

}
