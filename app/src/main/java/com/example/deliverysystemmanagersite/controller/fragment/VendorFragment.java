package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.VendorAdapter;
import com.example.deliverysystemmanagersite.adapter.driverAdpter.DriverWorkListAdapter;
import com.example.deliverysystemmanagersite.driver.driver.DriverWorkListViewModel;
import com.example.deliverysystemmanagersite.model.Packages;
import com.example.deliverysystemmanagersite.model.Vendor;
import com.example.deliverysystemmanagersite.model.VendorViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class VendorFragment extends Fragment {

    private VendorViewModel vendorViewModel;
    private ListView listView;
    private View root;
    private VendorFragment fra;
    private List<Vendor> pList;

    public static VendorFragment newInstance() {
        VendorFragment fragment = new VendorFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_vendor,container,false);
        init();

        vendorViewModel = new ViewModelProvider(requireActivity()).get(VendorViewModel.class);
        return root;
    }

    public void init(){
        listView = (ListView) root.findViewById(R.id.Vendor_lv);
        fra = this;

        vendorViewModel = new VendorViewModel();
        pList = new ArrayList<>();
        pList = vendorViewModel.getList();
        listView.setAdapter(new VendorAdapter(getActivity(), R.layout.vendor_layout, pList));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            vendorViewModel.selectVendor(pList.get(i));

            Navigation.findNavController(root);
            NavHostFragment.findNavController(fra).navigate(R.id.navigation_VendorDetail);
        });

    }

}