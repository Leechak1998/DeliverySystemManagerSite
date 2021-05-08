package com.example.deliverysystemmanagersite.controller.fragment;

import android.app.AlertDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;

import android.widget.Toast;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.PackageAdapter;
import com.example.deliverysystemmanagersite.model.PackageViewModel;
import com.example.deliverysystemmanagersite.model.Packages;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

public class PackageFragment extends Fragment {

    private PackageFragment fra;
    private List<Packages> pList;
    private PackageViewModel packageViewModel;
    private View root;
    private ListView listView;
    private ImageButton btnSearch;
    private ImageButton btnFilter;
    private EditText etSearchBar;
    private PackageAdapter adapter;
    private final String[] items = new String[]{"Order Num: Increasing", "Order Num: Decreasing","Time: New-Past", "Time: Past-New"};

    public static PackageFragment newInstance() {
        PackageFragment fragment = new PackageFragment();
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        packageViewModel = new ViewModelProvider(requireActivity()).get(PackageViewModel.class);
        root = inflater.inflate(R.layout.fragment_package, container, false);
        init();
        setListener();
        packageViewModel = new ViewModelProvider(requireActivity()).get(PackageViewModel.class);
        return root;
    }

    public void init() {
        listView = (ListView) root.findViewById(R.id.lv_package);
        btnSearch = (ImageButton) root.findViewById(R.id.imgBtnSearch);
        btnFilter = (ImageButton) root.findViewById(R.id.imgBtnFilter);
        etSearchBar = (EditText) root.findViewById(R.id.etSearch);

        packageViewModel = new PackageViewModel();
        pList = new ArrayList<>();
        pList = packageViewModel.getList();
        listView.setAdapter(new PackageAdapter(getActivity(), R.layout.packages_item, pList));

        fra = this;
    }

    private void setListener() {

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            packageViewModel.selectPackage(pList.get(i));

            Navigation.findNavController(root);
            NavHostFragment.findNavController(fra).navigate(R.id.navigation_packDetail);

        });

        btnSearch.setOnClickListener(view -> {
            if (etSearchBar.length() == 0){
                Toast.makeText(getActivity(), "Your input is empty", Toast.LENGTH_LONG).show();
            }else {
                adapter = new PackageAdapter(getActivity(), R.layout.packages_item, packageViewModel.findItem(etSearchBar.getText().toString()));
            }
        });

        btnFilter.setOnClickListener(view -> {
            android.app.AlertDialog alertDialog = new AlertDialog.Builder(getActivity())
                .setTitle("Sort By...")
                    .setSingleChoiceItems(items, -1, (dialogInterface, i) -> {
                        Toast.makeText(getActivity(), "you chose" + items[i] + " chose num: " + i, Toast.LENGTH_SHORT).show();
                        adapter = new PackageAdapter(getActivity(), R.layout.packages_item,packageViewModel.sortList(i));
                    })
                    .setPositiveButton("Submit", (dialogInterface, i) -> {
                                Toast.makeText(getActivity(), "submit!", Toast.LENGTH_SHORT).show();
                                listView.setAdapter(adapter);
                            }

                    )
                    .setNegativeButton("Cancel", null)
                    .create();
            alertDialog.show();
        });

        TouchListener(btnSearch);
        //Click one of the listview items, go to item details page

    }

    //Click image button, change background color
    public void TouchListener(View v){
        v.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                view.setBackgroundColor(Color.parseColor("#d3d3d3"));
            }else if (motionEvent.getAction() == MotionEvent.ACTION_UP){
                view.setBackgroundColor(Color.WHITE);
            }
            return false;
        });
    }

}