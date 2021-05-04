package com.example.deliverysystemmanagersite.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.Site;

import java.util.ArrayList;
import java.util.List;

public class SiteViewModel extends ViewModel {

    private List<Site> SiteList = new ArrayList<>();

    public SiteViewModel() {
        init();
    }

    public void init(){
        Site site1 = new Site("000","123",1,"123@soton.ac.uk","Crossing","Jack");
        Site site2 = new Site("waitrose","12323",2,"1244@soton.ac.uk","City central","Mary");
        Site site3 = new Site("Sainsbury","1212313",3,"12222@soton.ac.uk","Law court","Tom");
        Site site4 = new Site("Sainsbury","122133213",4,"1221313@soton.ac.uk","Ocean village","Tony");
        SiteList.add(site1);
        SiteList.add(site2);
        SiteList.add(site3);
        SiteList.add(site4);
    }
    public List<Site> getText(){
        return SiteList;
    }
}
