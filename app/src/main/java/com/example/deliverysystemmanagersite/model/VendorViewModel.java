package com.example.deliverysystemmanagersite.model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.model.Vendor;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;
import com.example.deliverysystemmanagersite.util.SerializableList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VendorViewModel extends ViewModel {

//    private List<Vendor> VendorList = new ArrayList<>();
    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<Vendor> selectedVendor = new MutableLiveData<>();
    private List<Vendor> vendorList = new ArrayList<>();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Bundle bundle = msg.getData();
                    SerializableList s = new SerializableList();
                    s = (SerializableList)bundle.getSerializable("data");
                    vendorList = s.getVendorList();
                    //System.out.println("----id:" + packagesList.get(0).getPackageId() + "destination" + packagesList.get(0).getDestination());
                    break;

            }
        }
    };

    public VendorViewModel() {
        init();
    }

    public void init(){
        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String dataList = htc.doGet("http://10.0.2.2:8339/selectVendorAll");
            try{
                JSONArray vendorListData = new JSONArray(dataList);
                for (int i=0; i<vendorListData.length(); i++){
                    int vendorId = vendorListData.getJSONObject(i).getInt("vendorId");
                    String vendorName = vendorListData.getJSONObject(i).getString("vendorName");
                    String email = vendorListData.getJSONObject(i).getString("email");
                    String tel = vendorListData.getJSONObject(i).getString("telephoneNumber");
                    String address = vendorListData.getJSONObject(i).getString("address");
                    vendorList.add(new Vendor(vendorName,tel,vendorId,email,address));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void selectVendor(Vendor s) {
        selectedVendor.setValue(s);
    }

    public List<Vendor> getList(){
        return vendorList;

    }

    public LiveData<Vendor> getSelectedVendor() {
        return selectedVendor;
    }
}