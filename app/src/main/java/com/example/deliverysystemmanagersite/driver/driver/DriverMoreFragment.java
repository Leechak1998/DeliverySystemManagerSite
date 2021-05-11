package com.example.deliverysystemmanagersite.driver.driver;

import android.media.Image;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deliverysystemmanagersite.R;
import com.example.deliverysystemmanagersite.model.Driver;
import com.example.deliverysystemmanagersite.model.DriverViewModel;
import com.example.deliverysystemmanagersite.util.HttpConnectionUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class DriverMoreFragment extends Fragment {
    private View root;
    private EditText et_driver_name;
    private EditText et_driver_tel;
    private EditText et_driver_email;
    private EditText et_driver_id;
    private ImageButton btn_sub;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_driver_more, container, false);

        init();

        return root;
    }

    private void init() {
        et_driver_name = (EditText) root.findViewById(R.id.et_driver_name);
        et_driver_tel = (EditText) root.findViewById(R.id.et_driver_tel);
        et_driver_email = (EditText) root.findViewById(R.id.et_driver_email);
        et_driver_id = (EditText) root.findViewById(R.id.et_driver_id);
        btn_sub = (ImageButton) root.findViewById(R.id.btn_sub);

        new Thread(() -> {
            HttpConnectionUtil htc = new HttpConnectionUtil();
            int id = Integer.parseInt(htc.doGet("http://10.0.2.2:8339/retrieveDriverId"));
            String data = htc.doGet("http://10.0.2.2:8339/selectDriverById?driverId=" + id);
            try {
                JSONObject jsonObject = new JSONObject(data);

                String driverId = jsonObject.getInt("driverId")+"";
                String driverName = jsonObject.getString("driverName");
                String email = jsonObject.getString("email");
                String telephoneNumber = jsonObject.getString("telephoneNumber");

                Message mes = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("driverId", driverId);
                bundle.putString("driverName", driverName);
                bundle.putString("email", email);
                bundle.putString("telephoneNumber", telephoneNumber);
                mes.what = 0;
                mes.setData(bundle);
                handler.sendMessageAtFrontOfQueue(mes);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }).start();

        btn_sub.setOnClickListener(view -> {
            new Thread(() -> {
                HttpConnectionUtil htc = new HttpConnectionUtil();
                int id = Integer.parseInt(htc.doGet("http://10.0.2.2:8339/retrieveDriverId"));
                String driverName = et_driver_name.getText().toString();
                String driverTel = et_driver_tel.getText().toString();
                String driverEmail = et_driver_email.getText().toString();
                htc.doGet("http://10.0.2.2:8339/updateDriver?driverId=" + id + "&driverName=" + driverName + "&driverTelephoneNumber=" + driverTel + "&driverEmail=" + driverEmail);
            }).start();
            Toast.makeText(getActivity(), "success!", Toast.LENGTH_SHORT).show();

            Navigation.findNavController(root);
            NavHostFragment.findNavController(this).navigate(R.id.navigation_mine_driver);

        });
    }

    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 0:
                    Bundle bundle = msg.getData();
                    et_driver_name.setText(bundle.getString("driverName"));
                    et_driver_tel.setText(bundle.getString("telephoneNumber"));
                    et_driver_email.setText(bundle.getString("email"));
                    et_driver_id.setText(bundle.getString("driverId"));
                    break;
            }
        }
    };
}