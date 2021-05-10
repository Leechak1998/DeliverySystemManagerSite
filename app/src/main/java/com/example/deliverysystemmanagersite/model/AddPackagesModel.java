package com.example.deliverysystemmanagersite.model;

import android.os.Bundle;
import android.os.Message;

import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;
import com.example.deliverysystemmanagersite.util.SerializableList;

import org.json.JSONArray;
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


//        Vendor v1 = new Vendor("Vendor1", "123", 0, "123@qq", "Hampton");
//        Site s1 = new Site("Site1", "321", 0, "321@qq", "Crossings");
//        Driver d1 = new Driver("jackson", "12333", 0 , "jack@qq");
//
//        Vendor v2 = new Vendor("Vendor2", "123", 0, "123@qq", "Hampton");
//        Site s2 = new Site("Site2", "321", 0, "321@qq", "Crossings");
//        Driver d2 = new Driver("jackson2", "12333", 0 , "jack@qq");
        requestData();
        System.out.println("之后");
//        list_departure.add(v1);
//        list_site.add(s1);
//        list_driver.add(d1);
//        list_departure.add(v2);
//        list_site.add(s2);
//        list_driver.add(d2);


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

        new Thread(()->{
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String vendorList = htc.doGet("http://10.0.2.2:8339/selectVendorAll");
            String siteList = htc.doGet("http://10.0.2.2:8339/selectSiteAll");
            String driverList = htc.doGet("http://10.0.2.2:8339/selectDriverAll");
            try{
                JSONArray driverListData = new JSONArray(driverList);
                JSONArray siteListData = new JSONArray(siteList);
                JSONArray vendorListData = new JSONArray(vendorList);
                for (int i=0; i<driverListData.length(); i++){
                    int driverId = driverListData.getJSONObject(i).getInt("driverId");
                    String driverName = driverListData.getJSONObject(i).getString("driverName");
                    String email = driverListData.getJSONObject(i).getString("email");
                    String tel = driverListData.getJSONObject(i).getString("telephoneNumber");
                    list_driver.add(new Driver(driverName,tel,driverId,email));
                }
                for (int i=0; i<siteListData.length(); i++){
                    int siteId = siteListData.getJSONObject(i).getInt("siteId");
                    String siteName = siteListData.getJSONObject(i).getString("siteName");
                    String email = siteListData.getJSONObject(i).getString("email");
                    String tel = siteListData.getJSONObject(i).getString("telephoneNumber");
                    String address = siteListData.getJSONObject(i).getString("address");
                    list_site.add(new Site(siteName,tel,siteId,email,address));
                }
                for (int i=0; i<vendorListData.length(); i++){
                    int vendorId = vendorListData.getJSONObject(i).getInt("vendorId");
                    String vendorName = vendorListData.getJSONObject(i).getString("vendorName");
                    String email = vendorListData.getJSONObject(i).getString("email");
                    String tel = vendorListData.getJSONObject(i).getString("telephoneNumber");
                    String address = vendorListData.getJSONObject(i).getString("address");
                    list_departure.add(new Vendor(vendorName,tel,vendorId,email,address));
                }
//                System.out.println("site:"+list_site.size());
//                System.out.println("vendor:"+list_departure.size());
//                System.out.println("driver:"+list_driver.size());
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();

    }

    //vendor_id state send_data received_data departure destination
    //String dep, String des, String dri, int state
    public void sendPackageDataToServer(Vendor vendor, Site site, Driver driver, int state){
        int driverID = driver.getDriver_id();
        String vendorName = vendor.getVendor_name();
        String departure = vendor.getAddress();
        String destination = site.getAddress();

//        System.out.println("driverID=" + driverID + "  vendorName=" + vendorName + "  departure=" + departure + "  destination=" + destination);
        new Thread(()->{
            HttpConnectionUtil htc = new HttpConnectionUtil();
            System.out.println(htc.doGet("http://10.0.2.2:8339/createPackage?driverId=" + driverID + "&vendorName=" + vendorName + "&departure=" + departure + "&destination=" + destination));
        }).start();

    }

}
