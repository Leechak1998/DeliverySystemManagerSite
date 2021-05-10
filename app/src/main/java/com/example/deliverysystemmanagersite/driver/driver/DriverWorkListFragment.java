package com.example.deliverysystemmanagersite.driver.driver;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.PackageAdapter;
import com.example.deliverysystemmanagersite.adapter.driverAdpter.DriverWorkListAdapter;
import com.example.deliverysystemmanagersite.model.Packages;

import java.util.ArrayList;
import java.util.List;

public class DriverWorkListFragment extends Fragment {
    private View root;

    private ListView listView;

    private DriverWorkListFragment fra;
    private DriverWorkListViewModel viewModel;
    private List<Packages> pList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_work_list, container, false);
        init();

        viewModel = new ViewModelProvider(requireActivity()).get(DriverWorkListViewModel.class);
        return root;
    }

    public void init(){
        fra = this;
        listView = (ListView) root.findViewById(R.id.lv);

        viewModel = new DriverWorkListViewModel();
        pList = new ArrayList<>();
        pList = viewModel.getList();
        listView.setAdapter(new DriverWorkListAdapter(getActivity(), R.layout.packages_item, pList));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            viewModel.selectPackage(pList.get(i));

            Navigation.findNavController(root);
            NavHostFragment.findNavController(fra).navigate(R.id.navigation_workList_details_driver);
        });

    }
}