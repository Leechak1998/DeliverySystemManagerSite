package com.example.deliverysystemmanagersite.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import androidx.lifecycle.ViewModel;

public class PackageViewModel extends ViewModel {

    //"Order Num: Increasing", "Order Num: Decreasing","Time: New-Past", "Time: Past-New"
    private static int SOT_BY_NUMBER_INC = 0;
    private static int SOT_BY_NUMBER_DEC = 1;
    private static int SORT_BY_TIME_DEC = 2;
    private static int SOT_BY_TIME_INC = 3;

    private List<Packages> packagesList = new ArrayList<>();

    public PackageViewModel() {
        initPack();
    }

    public List<Packages> getText() {
        return packagesList;
    }

    public void initPack(){
//        Packages pack1 = new Packages(1,"Jackson1","APPLE","Southampton");
//        packagesList.add(pack1);
//        Packages pack2 = new Packages(2,"Sam2","SONY","Sheffield");
//        packagesList.add(pack2);
//        Packages pack3 = new Packages(3,"Lily3","SAMSUNG","Southampton Crossings");
//        packagesList.add(pack3);
//        Packages pack4 = new Packages(4,"Jack4","APPLE","Hampton");
//        packagesList.add(pack4);
//        Packages pack5 = new Packages(5,"Jacob5","APPLE","Vita");
//        packagesList.add(pack5);
//        Packages pack6 = new Packages(6,"Yuki6","APPLE","Cumberland");
//        packagesList.add(pack6);
//        Packages pack7 = new Packages(7,"Bally7","APPLE","Vincent");
//        packagesList.add(pack7);

        Packages pack1 = new Packages(1,"Southampton","Jackson1","123123123","APPLE","Southampton",new Date(),"delivered");
        packagesList.add(pack1);
        Packages pack2 = new Packages(2,"London","Sam2","123123132","SONY","Sheffield", new Date(),"pending");
        packagesList.add(pack2);
        Packages pack3 = new Packages(3,"London","Lily3","123123","SAMSUNG","Southampton Crossings",new Date(),"delivering");
        packagesList.add(pack3);
    }

    public List<Packages> sortList(int requirement){
        Packages temp;
        if (requirement == SOT_BY_NUMBER_INC){
            System.out.println("SOT_BY_NUMBER_INC");
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-i-1; j++){
                    if(packagesList.get(j).getPackageId() > packagesList.get(j+1).getPackageId()){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }
                }
            }
        } else if (requirement == SOT_BY_NUMBER_DEC){
            System.out.println("SOT_BY_NUMBER_DEC");
            for(int i=0; i<packagesList.size(); i++){
                for(int j=0; j<packagesList.size()-1-i; j++){
                    if(packagesList.get(j).getPackageId() < packagesList.get(j+1).getPackageId()){
                        temp = packagesList.get(j);
                        packagesList.set(j, packagesList.get(j+1));
                        packagesList.set(j+1, temp);
                    }
                }
            }
        } else if (requirement == SORT_BY_TIME_DEC){
            System.out.println("SORT_BY_TIME_DEC");
        } else if (requirement == SOT_BY_TIME_INC){
            System.out.println("SOT_BY_TIME_INC");
        }

        return packagesList;
    }

    public List<Packages> findItem(String input){
        List<Packages> pl = new ArrayList<>();
        
        return pl;
    }
}