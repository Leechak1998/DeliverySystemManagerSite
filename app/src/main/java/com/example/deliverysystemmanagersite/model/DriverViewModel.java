package com.example.deliverysystemmanagersite.model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;
import com.example.deliverysystemmanagersite.util.SerializableList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DriverViewModel extends ViewModel {

    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<Driver> selectedDriver = new MutableLiveData<>();
    private List<Driver> driverList = new ArrayList<>();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Bundle bundle = msg.getData();
                    SerializableList s = new SerializableList();
                    s = (SerializableList)bundle.getSerializable("data");
                    driverList = s.getDriverList();
                    System.out.println("----name:" + driverList.get(0).getDriver_name());
                    break;

            }
        }
    };

    public DriverViewModel(){
        init();
    }

    public void init(){
        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String dataList = htc.doGet("http://10.0.2.2:8339/selectDriverAll");
            try{
                JSONArray driverListData = new JSONArray(dataList);
                for (int i=0; i<driverListData.length(); i++){
                    int driverId = driverListData.getJSONObject(i).getInt("driverId");
                    String driverName = driverListData.getJSONObject(i).getString("driverName");
                    String email = driverListData.getJSONObject(i).getString("email");
                    String tel = driverListData.getJSONObject(i).getString("telephoneNumber");
                    driverList.add(new Driver(driverName,tel,driverId,email));
                }

                SerializableList serializableList = new SerializableList();
                serializableList.setDriverList(driverList);

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


    public void selectDriver(Driver s) {
        selectedDriver.setValue(s);
    }

    public LiveData<String> getSelected() {
        return selected;
    }

    public List<Driver> getList(){
        System.out.println("111");
        return driverList;

    }

    public LiveData<Driver> getSelectedDriver() {
        return selectedDriver;
    }

}