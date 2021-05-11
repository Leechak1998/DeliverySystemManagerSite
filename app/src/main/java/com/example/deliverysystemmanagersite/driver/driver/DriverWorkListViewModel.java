package com.example.deliverysystemmanagersite.driver.driver;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.deliverysystemmanagersite.model.Packages;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;
import com.example.deliverysystemmanagersite.util.SerializableList;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DriverWorkListViewModel extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<Packages> selectedPackage = new MutableLiveData<>();
    private List<Packages> packagesList = new ArrayList<>();
    public String readStatus = "";
    private int flag = 0;

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Bundle bundle = msg.getData();
                    SerializableList s = (SerializableList)bundle.getSerializable("data");
                    packagesList = s.getPackagesList();
                    readStatus = bundle.getString("reading_status");
//                    System.out.println("拿到package list 和 readstatus:" + readStatus);
                    break;
                case 1:
                    Bundle bundle1 = msg.getData();
                    readStatus = bundle1.getString("reading_status");
                    System.out.println("=====readstatus:" + readStatus);
                    break;

            }
        }
    };

    public DriverWorkListViewModel(){
        init();
    }

    public void init() {
        Thread thread = new Thread(() -> {
            System.out.println("优先启动了吗");

            HttpConnectionUtil htc = new HttpConnectionUtil();
            int id = Integer.parseInt( htc.doGet("http://10.0.2.2:8339/retrieveDriverId"));
            System.out.println("看看ID " + id);

            String rs = htc.doGet("http://10.0.2.2:8339/checkPackageReadable?driverId=" + id);
            System.out.println("拿到了吗？" + rs);

            //获取所有包裹，URL有误！
            String dataList = htc.doGet("http://10.0.2.2:8339/selectPackageByDriverId?driverId=" + id);
            try{
                JSONArray JSON_obj = new JSONArray(dataList);
                JSONArray PackageList = JSON_obj;
                for (int i=0; i<PackageList.length(); i++) {
                    int packageId = PackageList.getJSONObject(i).getInt("packageId");
                    String driver = PackageList.getJSONObject(i).getString("driverId");
                    String vendor = PackageList.getJSONObject(i).getString("vendorName");
                    String sendDate = PackageList.getJSONObject(i).getString("sendDate");
                    String departure = PackageList.getJSONObject(i).getString("departure");
                    String destination = PackageList.getJSONObject(i).getString("destination");
                    String date = PackageList.getJSONObject(i).getString("receiveDate");
                    String state = PackageList.getJSONObject(i).getString("state");
                    String readingStatus = PackageList.getJSONObject(i).getString("readable");
                    packagesList.add(new Packages(packageId,departure,driver,sendDate,vendor,destination,date,state,readingStatus));
                }

                SerializableList serializableList = new SerializableList();
                serializableList.setPackagesList(packagesList);

                Message mes = new Message();
                Bundle bundle = new Bundle();
                bundle.putSerializable("data" ,serializableList);
                bundle.putString("reading_status", rs);
                mes.setData(bundle);
                mes.what = 0;
                handler.sendMessageAtFrontOfQueue(mes);
                //handler.sendMessage(mes);
                System.out.println("线程结束");

            } catch (JSONException e) {
                e.printStackTrace();
            }
        });

        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();

    }

    public String getReadStatus(){
        if (flag == 0){
            flag ++;
            return readStatus;
        }
        return "-1";
    }

    public void select(String s) {
        selected.setValue(s);
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

    public void sendReadingStatus(int readingStatus){
        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
            htc.doGet("http://10.0.2.2:8339/updatePackageReadable?packageId=" + readingStatus);
        }).start();
    }

}
