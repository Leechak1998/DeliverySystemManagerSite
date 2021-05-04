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
import android.widget.ListView;

import com.example.deliverysystemmanagersite.R;

import java.util.ArrayList;
import java.util.List;

public class DriverWorkListFragment extends Fragment {
    private View root;
    private DriverWorkListFragment fra;
    private DriverWorkListViewModel viewModel;
    private List<String> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_work_list, container, false);
        init();
        viewModel = new ViewModelProvider(requireActivity()).get(DriverWorkListViewModel.class);
        /*

        model = new ViewModelProvider(requireActivity()).get(SharedViewModel.class);
        itemSelector.setOnClickListener(item -> {
            model.select(item);
        });

         */
        return root;
    }

    public void init(){
        fra = this;
        ListView listView = (ListView) root.findViewById(R.id.lv);
        viewModel = new DriverWorkListViewModel();
        list = new ArrayList<>();

        list = viewModel.getList();
        listView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                viewModel.select(list.get(i));

                Navigation.findNavController(root);
                NavHostFragment.findNavController(fra).navigate(R.id.navigation_workList_details_driver);
            }
        });

    }
}