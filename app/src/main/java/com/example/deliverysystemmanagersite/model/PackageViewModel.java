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
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.controller.fragment.PackageFragment;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;
import com.example.deliverysystemmanagersite.util.SerializableList;
import com.google.android.gms.maps.SupportMapFragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PackageViewModel extends ViewModel{

    private static int SOT_BY_NUMBER_INC = 0;
    private static int SOT_BY_NUMBER_DEC = 1;
    private static int SORT_BY_STATE = 2;

    private List<Packages> packagesList = new ArrayList<>();
    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<Packages> selectedPackage = new MutableLiveData<>();

    public PackageViewModel() {
        initPack();
    }

    public void selectPackage(Packages s) {
        selectedPackage.setValue(s);
    }

    public LiveData<String> getSelected() {
        return selected;
    }

    public List<Packages> getList(){
        return packagesList;

    }

    public LiveData<Packages> getSelectedPackage() {
        return selectedPackage;
    }

    public List<Packages> getText() {
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
                    break;

            }
        }
    };


    public void initPack(){
        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
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
                    String readingStatus = PackageList.getJSONObject(i).getString("readable");
                    packagesList.add(new Packages(packageId,departure,driver,tel,vendor,destination,date,state,readingStatus));

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
        }else if(requirement == SORT_BY_STATE){
            System.out.println("SORT_BY_STATE");
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-1-i; j++){
                    if(Integer.parseInt(packagesList.get(j).getState()) < Integer.parseInt(packagesList.get(j+1).getState())){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }
        } }}
        return packagesList;

    }
    private int IntDate(String date){
        int month = Integer.parseInt(date.substring(0,1));
        int day = Integer.parseInt(date.substring(3,4));
        int year = Integer.parseInt(date.substring(3,4));
        return year*10000+month*100+day;
    }
    public List<Packages> findItem(String input){
        List<Packages> pl = new ArrayList<>();
        
        return pl;
    }



}