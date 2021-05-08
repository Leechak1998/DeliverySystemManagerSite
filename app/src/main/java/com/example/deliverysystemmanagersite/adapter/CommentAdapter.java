package com.example.deliverysystemmanagersite.adapter;

import android.view.View;
import android.widget.ArrayAdapter;

import com.example.deliverysystemmanagersite.model.Driver;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.Vendor;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter {
    public CommentAdapter(){

    }

    public ArrayAdapter<?> setDepartureListAdapter(List<Vendor> list, View view){
        //从得到的list 转化为ArrayAdapter<String>
        List<String> stringList = new ArrayList();
        for (int i=0; i<list.size(); i++){
            stringList.add(list.get(i).getVendor_name());
        }
        ArrayAdapter<?> arr_adapter= new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, stringList);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arr_adapter;
    }

    public ArrayAdapter<?> setDestinationListAdapter(List<Site> list, View view){
        //从得到的list 转化为ArrayAdapter<String>
        List<String> stringList = new ArrayList();
        for (int i=0; i<list.size(); i++){
            stringList.add(list.get(i).getSite_name());
        }
        ArrayAdapter<?> arr_adapter= new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, stringList);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arr_adapter;
    }

    public ArrayAdapter<?> setDriverListAdapter(List<Driver> list, View view){
        //从得到的list 转化为ArrayAdapter<String>
        List<String> stringList = new ArrayList();
        for (int i=0; i<list.size(); i++){
            stringList.add(list.get(i).getDriver_name());
        }
        ArrayAdapter<?> arr_adapter= new ArrayAdapter<>(view.getContext(), android.R.layout.simple_spinner_item, stringList);
        arr_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        return arr_adapter;
    }
}
