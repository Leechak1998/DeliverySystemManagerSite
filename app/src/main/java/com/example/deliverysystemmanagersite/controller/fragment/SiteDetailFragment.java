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

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Site;
import com.example.deliverysystemmanagersite.model.SiteViewModel;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import java.util.List;

public class SiteDetailFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

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

    public SiteDetailFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static SiteDetailFragment newInstance(String param1, String param2) {
        SiteDetailFragment fragment = new SiteDetailFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            siteViewModel = new ViewModelProvider(this).get(SiteViewModel.class);
            site_List = siteViewModel.getText();
            site_selected = site_List.get(getArguments().getInt("index"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_site_detail, container, false);
        init();
        setListener();
        return root;
    }

    public void init(){
        site_name = (TextView) root.findViewById(R.id.Site_Name);
        tel = (TextView) root.findViewById(R.id.Site_Tel);
        site_id = (TextView) root.findViewById(R.id.Site_Id);
        email = (TextView) root.findViewById(R.id.Site_Email);
        site_address = (TextView) root.findViewById(R.id.Site_Address);


        Edit = (ImageButton) root.findViewById(R.id.Site_edit);

        site_name.setText(site_selected.getSite_name());
        tel.setText(site_selected.getTel());
        site_id.setText(site_selected.getSite_id()+"");
        email.setText(site_selected.getEmail());
        site_address.setText(site_selected.getAddress());

    }
    public void setListener(){
        Edit.setOnClickListener(view->{
            HttpConnectionUtil htc = new HttpConnectionUtil();
            String Tel = tel.getText().toString();
            String Site_name = site_name.getText().toString();
            String Email = email.getText().toString();
            String Site_id = site_id.getText().toString();
            String Address = site_address.getText().toString();
            Site s = new Site(Site_name,Tel,Integer.parseInt(Site_id),Email,Address);
            htc.doGet("http://10.0.2.2:8339/updateSite?siteName=" + s.getSite_name() +"&tel="+s.getTel()+ "&siteId=" + s.getSite_id() + "&email="+s.getEmail()+"&address="+s.getAddress());
            tel.setText(Tel);
            site_name.setText(Site_name);
            email.setText(Email);
            site_address.setText(Address);
        });
    }
}