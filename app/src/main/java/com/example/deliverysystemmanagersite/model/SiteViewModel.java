package com.example.deliverysystemmanagersite.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SiteViewModel extends ViewModel {

    private List<Site> SiteList = new ArrayList<>();

    public SiteViewModel() {
        init();
    }

    public void init(){
        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String dataList = htc.doGet("http://10.0.2.2:8339/selectSiteAll");
            try{
                JSONArray siteList = new JSONArray(dataList);
                for (int i=0; i<siteList.length(); i++){
                    int siteId = siteList.getJSONObject(i).getInt("siteId");
                    String siteName = siteList.getJSONObject(i).getString("siteName");
                    String email = siteList.getJSONObject(i).getString("email");
                    String tel = siteList.getJSONObject(i).getString("telephoneNumber");
                    String address = siteList.getJSONObject(i).getString("address");
                    SiteList.add(new Site(siteName,tel,siteId,email,address));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }).start();
    }
    public List<Site> getText(){
        return SiteList;
    }
}
