package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.SiteAdapter;
import com.example.deliverysystemmanagersite.adapter.driverAdpter.DriverWorkListAdapter;
import com.example.deliverysystemmanagersite.driver.driver.DriverWorkListViewModel;
import com.example.deliverysystemmanagersite.model.Packages;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.SiteViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class SiteFragment extends Fragment {

    private SiteViewModel siteViewModel;
    private SiteAdapter adapter;
    private ListView listView;
    private View root;
    private SiteFragment fra;
    private List<Site> pList;

    public static SiteFragment newInstance() {
        SiteFragment fragment = new SiteFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_site,container,false);
        init();
        setListener();
        siteViewModel = new ViewModelProvider(requireActivity()).get(SiteViewModel.class);
        return root;
    }

    public void init(){
        listView = (ListView) root.findViewById(R.id.Site_lv);
//        adapter = new SiteAdapter(getActivity(),R.layout.site_layout,siteViewModel.getText());
//        listView.setAdapter(adapter);
        fra = this;

        siteViewModel = new SiteViewModel();
        pList = new ArrayList<>();
        pList = siteViewModel.getList();
        listView.setAdapter(new SiteAdapter(getActivity(), R.layout.site_layout, pList));
    }
    private void setListener() {
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                siteViewModel.selectSite(pList.get(i));

                Navigation.findNavController(root);
                NavHostFragment.findNavController(fra).navigate(R.id.navigation_SiteDetail);

            }
        });
    }
}
