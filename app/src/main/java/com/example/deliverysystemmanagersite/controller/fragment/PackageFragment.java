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

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import static android.content.ContentValues.TAG;


public class PackageFragment extends Fragment {

    private PackageFragment fra;
    private PackageViewModel packageViewModel;
    private View root;
    private ListView listView;
    private ImageButton btnSearch;
    private ImageButton btnFilter;
    private EditText etSearchBar;
    private PackageAdapter adapter;
    private final String[] items = new String[]{"Order Num: Increasing", "Order Num: Decreasing","Time: New-Past", "Time: Past-New"};

    private static final int DEFAULT_OFFSCREEN_PAGES = 1;

    public static PackageFragment newInstance() {
        PackageFragment fragment = new PackageFragment();
        // Supply num input as an argument.
//        Bundle args = new Bundle();
//        args.putInt("num", num);
//        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这里我只是简单的用num区别标签，其实具体应用中可以使用真实的fragment对象来作为叶片
//        mNum = getArguments() != null ? getArguments().getInt("num") : 1;
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        packageViewModel = new ViewModelProvider(this).get(PackageViewModel.class);
        root = inflater.inflate(R.layout.fragment_package, container, false);
        init();
        setListener();


        return root;
    }

    public void init() {
        listView = (ListView) root.findViewById(R.id.lv_package);
        btnSearch = (ImageButton) root.findViewById(R.id.imgBtnSearch);
        btnFilter = (ImageButton) root.findViewById(R.id.imgBtnFilter);
        etSearchBar = (EditText) root.findViewById(R.id.etSearch);

        adapter = new PackageAdapter(getActivity(),
                R.layout.packages_item,packageViewModel.getText());
        listView.setAdapter(adapter);

        fra = this;
    }

    private void setListener() {

        btnSearch.setOnClickListener(view -> {
            if (etSearchBar.length() == 0){
                Toast.makeText(getActivity(), "Your input is empty", Toast.LENGTH_LONG).show();
            }else {
                adapter = new PackageAdapter(getActivity(), R.layout.packages_item, packageViewModel.findItem(etSearchBar.getText().toString()));

//                String text = etSearchBar.getText().toString();
//                Toast.makeText(getActivity(),text, Toast.LENGTH_LONG).show();
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
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Bundle bundle = new Bundle();
                bundle.putString("text","text");
//                Navigation.findNavController(root);
//                NavHostFragment
//                        .findNavController(fra)
//                        .navigate(R.id.navigation_packageDetails,bundle);

            }
        });
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

    public void setOffscreenPageLimit(int limit) {
        if (limit < DEFAULT_OFFSCREEN_PAGES) {
            Log.w(TAG, "Requested offscreen page limit " + limit
                    + " too small; defaulting to " + DEFAULT_OFFSCREEN_PAGES);
            limit = DEFAULT_OFFSCREEN_PAGES;
        }
        // ...
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            // Fetch data or something...
        }
    }


}