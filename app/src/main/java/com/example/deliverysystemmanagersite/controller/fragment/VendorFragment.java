package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.siteAdapter;
import com.example.deliverysystemmanagersite.adapter.vendorAdapter;
import com.example.deliverysystemmanagersite.model.SiteViewModel;
import com.example.deliverysystemmanagersite.model.VendorViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class VendorFragment extends Fragment {

    private VendorViewModel vendorViewModel;
    private vendorAdapter adapter;
    private ListView listView;
    private View root;
    private VendorFragment fra;

    public static VendorFragment newInstance() {
        VendorFragment fragment = new VendorFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        vendorViewModel = new ViewModelProvider(this).get(VendorViewModel.class);
        root = inflater.inflate(R.layout.fragment_vendor,container,false);
        init();
        setListener();
        return root;
    }

    public void init(){
        listView = (ListView) root.findViewById(R.id.Vendor_lv);
        adapter = new vendorAdapter(getActivity(),R.layout.vendor_layout,vendorViewModel.getText());
        listView.setAdapter(adapter);
        fra = this;
    }

    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putInt("index", i);
                Navigation.findNavController(root);
                NavHostFragment
                        .findNavController(fra)
                        .navigate(R.id.navigation_VendorDetail,bundle);

            }
        });
    }
}