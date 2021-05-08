package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.example.deliverysystemmanagersite.model.Driver;
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

    public DriverWorkListViewModel(){
        init();
    }

    private void init() {
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

}
