package com.example.deliverysystemmanagersite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.controller.fragment.PackageFragment;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PackageViewModel extends ViewModel {



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
        return packagesList;
    }

    public void initPack(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpConnectionUtil htc = new HttpConnectionUtil();
                String dataList = htc.doGet("http://10.0.2.2:8339/getPackage");
                try{
                    JSONObject JSON_obj = new JSONObject(dataList);
                    JSONArray PackageList = JSON_obj.getJSONArray("drivers");
                    for (int i=0; i<PackageList.length(); i++){
                        int packageId = PackageList.getJSONObject(i).getInt("packageId");
                        String driver = PackageList.getJSONObject(i).getString("driver");
                        String vendor = PackageList.getJSONObject(i).getString("vendor");
                        String tel = PackageList.getJSONObject(i).getString("tel");
                        String departure = PackageList.getJSONObject(i).getString("departure");
                        String destination = PackageList.getJSONObject(i).getString("destination");
                        String date = PackageList.getJSONObject(i).getString("date");
                        String state = PackageList.getJSONObject(i).getString("state");
                        packagesList.add(new Packages(packageId,departure,driver,tel,vendor,destination,date,state));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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