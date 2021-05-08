package com.example.deliverysystemmanagersite.model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.controller.fragment.PackageFragment;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;
import com.example.deliverysystemmanagersite.util.SerializableList;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PackageViewModel extends ViewModel implements Serializable {

    private static int SOT_BY_NUMBER_INC = 0;
    private static int SOT_BY_NUMBER_DEC = 1;
    private static int SORT_BY_TIME_DEC = 2;
    private static int SORT_BY_TIME_INC = 3;
    private static int SORT_BY_STATE = 4;

    private List<Packages> packagesList = new ArrayList<>();

    public PackageViewModel() {
        initPack();
    }

    public List<Packages> getText() {
        System.out.println("when???/");
        return packagesList;
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Bundle bundle = msg.getData();
                    SerializableList s = new SerializableList();
                    s = (SerializableList)bundle.getSerializable("data");
                    packagesList = s.getPackagesList();
                    System.out.println("----id:" + packagesList.get(0).getPackageId() + "destination" + packagesList.get(0).getDestination());
                    break;

            }
        }
    };


    public void initPack(){
        System.out.println("11111111");
        new Thread(() -> {
            System.out.println("22222222");
            HttpConnectionUtil htc = new HttpConnectionUtil();
            //127.0.0.1:8339/selectPackageAll
            String dataList = htc.doGet("http://10.0.2.2:8339/selectPackageAll");
            try{
                JSONArray JSON_obj = new JSONArray(dataList);
                JSONArray PackageList = JSON_obj;
                for (int i=0; i<PackageList.length(); i++) {
                    int packageId = PackageList.getJSONObject(i).getInt("packageId");
                    String driver = PackageList.getJSONObject(i).getString("driverId");
                    String vendor = PackageList.getJSONObject(i).getString("vendorName");
//                        String tel = PackageList.getJSONObject(i).getString("tel");
                    String tel = "123444";
                    String departure = PackageList.getJSONObject(i).getString("departure");
                    String destination = PackageList.getJSONObject(i).getString("destination");
                    String date = PackageList.getJSONObject(i).getString("receiveDate");
                    String state = PackageList.getJSONObject(i).getString("state");
                    packagesList.add(new Packages(packageId,departure,driver,tel,vendor,destination,date,state));

                }
                SerializableList serializableList = new SerializableList();
                serializableList.setPackagesList(packagesList);

                    Message mes = new Message();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("data" ,serializableList);

                    mes.setData(bundle);
                    mes.what = 0;
                    handler.sendMessage(mes);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();


    }

    public List<Packages> sortList(int requirement){
        Packages temp;
        if (requirement == SOT_BY_NUMBER_INC){
            System.out.println("SOT_BY_NUMBER_INC");
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-i-1; j++){
                    if(packagesList.get(j).getPackageId() > packagesList.get(j+1).getPackageId()){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }
                }
            }
        } else if (requirement == SOT_BY_NUMBER_DEC){
            System.out.println("SORT_BY_NUMBER_DEC");
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-1-i; j++){
                    if(packagesList.get(j).getPackageId() < packagesList.get(j+1).getPackageId()){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }
                }
            }
        } else if (requirement == SORT_BY_TIME_DEC){
            System.out.println("SORT_BY_TIME_DEC");
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-1-i; j++){
                    if(this.IntDate(packagesList.get(j).getDate()) < this.IntDate(packagesList.get(j+1).getDate())){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }
                }
            }
        } else if (requirement == SORT_BY_TIME_INC){
            System.out.println("SORT_BY_TIME_INC");
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-1-i; j++){
                    if(this.IntDate(packagesList.get(j).getDate()) > this.IntDate(packagesList.get(j+1).getDate())){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }
                }
            }
        }else if(requirement == SORT_BY_STATE){
            System.out.println("SORT_BY_STATE");
            HashMap<String,Integer> map = new HashMap<String,Integer>(){{
                put("pending",1);
                put("delivering",2);
                put("delivered",3);
            }};
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-1-i; j++){
                    if(map.get(packagesList.get(j).getState()) < map.get(packagesList.get(j+1).getPackageId())){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }

        } }}
        return packagesList;

    }
    private int IntDate(String date){
//        int month = Integer.parseInt(date.substring(0,1));
//        int day = Integer.parseInt(date.substring(3,4));
//        int year = Integer.parseInt(date.substring(3,4));
        return Integer.parseInt(date.substring(0,3))*10000+Integer.parseInt(date.substring(5,6))*100+Integer.parseInt(date.substring(8,9));
    }
    public List<Packages> findItem(String input){
        List<Packages> pl = new ArrayList<>();
        
        return pl;
    }



}