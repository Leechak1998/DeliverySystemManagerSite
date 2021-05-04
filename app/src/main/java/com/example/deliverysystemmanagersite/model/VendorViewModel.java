package com.example.deliverysystemmanagersite.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class VendorViewModel extends ViewModel {

    private List<Vendor> VendorList = new ArrayList<>();

    public VendorViewModel() {
        init();
    }

    public void init(){
        Vendor vendor1 = new Vendor("Sainsbury","123",1,"123@soton.ac.uk","Crossing");
        Vendor vendor2 = new Vendor("waitrose","12323",2,"1244@soton.ac.uk","City central");
        Vendor vendor3 = new Vendor("Sainsbury","1212313",3,"12222@soton.ac.uk","Law court");
        Vendor vendor4 = new Vendor("Sainsbury","122133213",4,"1221313@soton.ac.uk","Ocean village");
        VendorList.add(vendor1);
        VendorList.add(vendor2);
        VendorList.add(vendor3);
        VendorList.add(vendor4);
    }
    public List<Vendor> getText(){
        return VendorList;
    }
}