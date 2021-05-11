package com.example.deliverysystemmanagersite.driver.driver;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.Toast;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.adapter.PackageAdapter;
import com.example.deliverysystemmanagersite.adapter.driverAdpter.DriverWorkListAdapter;
import com.example.deliverysystemmanagersite.model.Packages;

import java.util.ArrayList;
import java.util.List;

public class DriverWorkListFragment extends Fragment {
    private View root;

    private ListView listView;
    private ImageButton btnSearch;
    private ImageButton btnFilter;
    private EditText etSearchBar;

    private DriverWorkListFragment fra;
    private DriverWorkListViewModel viewModel;
    private List<Packages> pList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new DriverWorkListViewModel();
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
        btnSearch = (ImageButton) root.findViewById(R.id.imgBtnSearch);
        btnFilter = (ImageButton) root.findViewById(R.id.imgBtnFilter);
        etSearchBar = (EditText) root.findViewById(R.id.etSearch);

        System.out.println("检查是否有未读信息");
        String s = viewModel.getReadStatus();
        System.out.println("信息状态为 " + s);
        if (s.equals("0")) {
            tipClick(root);
        }

        pList = new ArrayList<>();
        System.out.println("获取list请求");
        pList = viewModel.getList();
        listView.setAdapter(new DriverWorkListAdapter(getActivity(), R.layout.packages_item, pList));

        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            viewModel.selectPackage(pList.get(i));

            Navigation.findNavController(root);
            NavHostFragment.findNavController(fra).navigate(R.id.navigation_workList_details_driver);
        });


    }


    public void tipClick(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("new message");
        builder.setMessage("You have new parcel!");
        builder.setIcon(R.mipmap.ic_launcher_round);
        //点击对话框以外的区域是否让对话框消失
        builder.setCancelable(true);
        //设置正面按钮
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "你点击了是的", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        //设置反面按钮
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Toast.makeText(context, "你点击了不是", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();

        //显示对话框
        dialog.show();
    }
}