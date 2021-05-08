package com.example.deliverysystemmanagersite.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.model.Vendor;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class VendorViewModel extends ViewModel {

    private List<Vendor> VendorList = new ArrayList<>();

    public VendorViewModel() {
        init();
    }

    public void init(){
//        Vendor vendor1 = new Vendor("Sainsbury","123",1,"123@soton.ac.uk","Crossing");
//        Vendor vendor2 = new Vendor("waitrose","12323",2,"1244@soton.ac.uk","City central");
//        Vendor vendor3 = new Vendor("Sainsbury","1212313",3,"12222@soton.ac.uk","Law court");
//        Vendor vendor4 = new Vendor("Sainsbury","122133213",4,"1221313@soton.ac.uk","Ocean village");
//        VendorList.add(vendor1);
//        VendorList.add(vendor2);
//        VendorList.add(vendor3);
//        VendorList.add(vendor4);
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpConnectionUtil htc = new HttpConnectionUtil();
                String dataList = htc.doGet("http://10.0.2.2:8339/selectVendorAll");
                try{
                    JSONArray vendorList = new JSONArray(dataList);
                    for (int i=0; i<vendorList.length(); i++){
                        int vendorId = vendorList.getJSONObject(i).getInt("vendorId");
                        String vendorName = vendorList.getJSONObject(i).getString("vendorName");
                        String email = vendorList.getJSONObject(i).getString("email");
                        String tel = vendorList.getJSONObject(i).getString("telephoneNumber");
                        String address = vendorList.getJSONObject(i).getString("address");
                        VendorList.add(new Vendor(vendorName,tel,vendorId,email,address));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public List<Vendor> getText(){
        return VendorList;
    }
}