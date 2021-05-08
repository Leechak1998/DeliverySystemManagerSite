package com.example.deliverysystemmanagersite.controller.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.driver.driver.DriverWorkListViewModel;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.SiteViewModel;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import java.util.List;

public class SiteDetailFragment extends Fragment {

    private TextView site_name;
    private TextView tel;
    private TextView site_id;
    private TextView email;
    private TextView site_address;
    private TextView site_admin;
    private SiteViewModel siteViewModel;
    private List<Site> site_List;
    private Site site_selected;
    private ImageButton Edit;
    private View root;



    // TODO: Rename and change types and number of parameters
    public static SiteDetailFragment newInstance(String param1, String param2) {
        SiteDetailFragment fragment = new SiteDetailFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_site_detail, container, false);
        siteViewModel = new ViewModelProvider(requireActivity()).get(SiteViewModel.class);
        siteViewModel.getSelectedSite().observe(getViewLifecycleOwner(), item -> {
            init();
            site_name.setText(siteViewModel.getSelectedSite().getValue().getSite_name());
            tel.setText(siteViewModel.getSelectedSite().getValue().getTel());
            site_id.setText(siteViewModel.getSelectedSite().getValue().getSite_id()+"");
            email.setText(siteViewModel.getSelectedSite().getValue().getEmail());
            site_address.setText(siteViewModel.getSelectedSite().getValue().getAddress());

            setListener();
        });

        return root;
    }

    public void init(){
        site_name = (TextView) root.findViewById(R.id.Site_Name);
        tel = (TextView) root.findViewById(R.id.Site_Tel);
        site_id = (TextView) root.findViewById(R.id.Site_Id);
        email = (TextView) root.findViewById(R.id.Site_Email);
        site_address = (TextView) root.findViewById(R.id.Site_Address);

        Edit = (ImageButton) root.findViewById(R.id.Site_edit);
    }

    public void setListener(){


        Edit.setOnClickListener(view->{
            String Tel = tel.getText().toString();
            String Site_name = site_name.getText().toString();
            String Email = email.getText().toString();
            String Site_id = site_id.getText().toString();
            String Address = site_address.getText().toString();
            if(!Tel.equals("")&&!Site_name.equals("")&&!Address.equals("")&&!Email.equals("")){
            new Thread(() -> {
                HttpConnectionUtil htc = new HttpConnectionUtil();

                Site s = new Site(Site_name,Tel,Integer.parseInt(Site_id),Email,Address);
                System.out.println(htc.doGet("http://10.0.2.2:8339/updateSite?siteName=" + s.getSite_name() +"&telephoneNumber="+s.getTel()+ "&siteId=" + s.getSite_id() + "&email="+s.getEmail()+"&address="+s.getAddress()));
                tel.setText(Tel);
                site_name.setText(Site_name);
                email.setText(Email);
                site_address.setText(Address);
            }).start();}else{
                Toast.makeText(getActivity(), "Your should fill all the information.", Toast.LENGTH_LONG).show();
            }

        });
    }
}