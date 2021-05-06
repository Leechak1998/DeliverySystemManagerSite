package com.example.deliverysystemmanagersite.model;

import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class AddPackagesModel {

    private List<String> listFrom = new ArrayList<>();
    private List<String> listTo = new ArrayList<>();
    private List<String> listDriver = new ArrayList<>();

    final private static int ADD_FROM = 0;
    final private static int ADD_TO = 1;
    final private static int ADD_DRIVER = 2;

    public AddPackagesModel(){
        //数据库请求，现在先写死
        //requestData();
        listFrom.add("China");
        listFrom.add("Britain");
        listFrom.add("France");

        listTo.add("Crossings");
        listTo.add("Crecent");
        listTo.add("Cumberland");

        listDriver.add("Jackson");
        listDriver.add("Sam");
        listDriver.add("Tim");
    }

    public List<String> initList( int index){
        List<String> list = new ArrayList<>();
        switch (index){
            case ADD_FROM:
                list = listFrom;
                return list;
            case ADD_TO:
                list = listTo;
                return list;
            case ADD_DRIVER:
                list = listDriver;
                return list;
            default:
                return null;
        }
    }

    public void requestData(){
        HttpConnectionUtil htc = new HttpConnectionUtil();
        String test = htc.doGet("http://10.0.2.2:8339/....");
        try {
            JSONObject jsonObject = new JSONObject(test);
            String um = jsonObject.getString("username");
            System.out.println("username:" + um);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        listFrom.add("China");
        listTo.add("Crossings");
        listDriver.add("Jackson");
    }
}
