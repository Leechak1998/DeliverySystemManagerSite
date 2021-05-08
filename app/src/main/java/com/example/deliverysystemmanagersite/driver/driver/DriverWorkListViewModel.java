package com.example.deliverysystemmanagersite.driver.driver;

import com.example.deliverysystemmanagersite.model.Driver;
import com.example.deliverysystemmanagersite.model.Packages;

import java.util.ArrayList;
import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class DriverWorkListViewModel extends ViewModel {
    private final MutableLiveData<String> selected = new MutableLiveData<String>();

    public void select(String s) {
        selected.setValue(s);
    }

    public LiveData<String> getSelected() {
        return selected;
    }

    public List<Packages> getList(){
        //连接数据库
        List<Packages> packagesList = new ArrayList<>();
        Packages d1 = new Packages(0, "jackson", "South", "China");
        Packages d2 = new Packages(1, "Sam", "London", "UK");

        packagesList.add(d1);
        packagesList.add(d2);

        return packagesList;
    }

}
