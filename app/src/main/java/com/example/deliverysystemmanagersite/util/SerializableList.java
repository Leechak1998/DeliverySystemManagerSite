package com.example.deliverysystemmanagersite.util;

import com.example.deliverysystemmanagersite.model.Packages;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

public class SerializableList implements Serializable {
    private List<Packages> packagesList;

    public List<Packages> getPackagesList(){
        return packagesList;
    }

    public void setPackagesList(List<Packages> list){
        this.packagesList = list;
    }



}
