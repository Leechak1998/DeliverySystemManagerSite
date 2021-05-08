package com.example.deliverysystemmanagersite.model;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;
import com.example.deliverysystemmanagersite.util.SerializableList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SiteViewModel extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<String>();
    private final MutableLiveData<Site> selectedSite = new MutableLiveData<>();
    private List<Site> siteList = new ArrayList<>();

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Bundle bundle = msg.getData();
                    SerializableList s = new SerializableList();
                    s = (SerializableList)bundle.getSerializable("data");
                    siteList = s.getSiteList();
                    //System.out.println("----id:" + packagesList.get(0).getPackageId() + "destination" + packagesList.get(0).getDestination());
                    break;

            }
        }
    };

    public SiteViewModel() {
        init();
    }

    public void init(){
        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String dataList = htc.doGet("http://10.0.2.2:8339/selectSiteAll");
            try{
                JSONArray siteListData = new JSONArray(dataList);
                for (int i=0; i<siteListData.length(); i++){
                    int siteId = siteListData.getJSONObject(i).getInt("siteId");
                    String siteName = siteListData.getJSONObject(i).getString("siteName");
                    String email = siteListData.getJSONObject(i).getString("email");
                    String tel = siteListData.getJSONObject(i).getString("telephoneNumber");
                    String address = siteListData.getJSONObject(i).getString("address");
                    siteList.add(new Site(siteName,tel,siteId,email,address));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void selectSite(Site s) {
        selectedSite.setValue(s);
    }


    public List<Site> getList(){
        return siteList;

    }

    public LiveData<Site> getSelectedSite() {
        return selectedSite;
    }

}
