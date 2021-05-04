package com.example.deliverysystemmanagersite.model;

import java.util.ArrayList;
import java.util.List;

public class AddPackagesModel {

    private List<String> listFrom = new ArrayList<>();
    private List<String> listTo = new ArrayList<>();
    private List<String> listDriver = new ArrayList<>();

    final private static int ADD_FROM = 0;
    final private static int ADD_TO = 1;
    final private static int ADD_DRIVER = 2;

    public AddPackagesModel(){
        listFrom.add("China");
        listFrom.add("Britain");
        listFrom.add("France");

        listTo.add("Crossings");
        listTo.add("Crecent");
        listTo.add("Cumberland");

        listDriver.add("Jackson");
        listDriver.add("Sam");
        listDriver.add("Tim");
    }

    public List<String> initList( int index){
        List<String> list = new ArrayList<>();
        switch (index){
            case ADD_FROM:
                //数据库请求
                list = listFrom;
                return list;
            case ADD_TO:
                list = listTo;
                return list;
            case ADD_DRIVER:
                list = listDriver;
                return list;
            default:
                return null;
        }
    }

}
