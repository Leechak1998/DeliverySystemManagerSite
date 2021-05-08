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
    private final MutableLiveData<Packages> selectedPackage = new MutableLiveData<>();

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
        //连接数据库
        List<Packages> packagesList = new ArrayList<>();
        Packages d1 = new Packages(0, "Crossings", "jackson", "12333","Apple", "China", "2020/10/11", "0");
        Packages d2 = new Packages(1, "Crecent", "Sam", "321515","Apple", "UK", "2100/10/11", "0");

        packagesList.add(d1);
        packagesList.add(d2);

        return packagesList;
    }

    public LiveData<Packages> getSelectedPackage() {
        return selectedPackage;
    }

}
