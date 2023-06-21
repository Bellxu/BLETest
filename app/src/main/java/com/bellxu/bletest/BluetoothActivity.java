package com.bellxu.bletest;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothHeadset;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class BluetoothActivity extends AppCompatActivity {
    private static final String TAG = "BluetoothActivity";

    private static final int REQUEST_ENABLE_BT = 996;
    String pin = "1234";  //此处为你要连接的蓝牙设备的初始密钥，一般为1234或0000
    BluetoothHeadset bluetoothHeadset;
    private List<BluetoothBean> bList = new ArrayList<>();


    private BluetoothProfile.ServiceListener profileListener = new BluetoothProfile.ServiceListener() {
        public void onServiceConnected(int profile, BluetoothProfile proxy) {
            if (profile == BluetoothProfile.HEADSET) {
                bluetoothHeadset = (BluetoothHeadset) proxy;
            }
        }

        public void onServiceDisconnected(int profile) {
            if (profile == BluetoothProfile.HEADSET) {
                bluetoothHeadset = null;
            }
        }
    };
    private BluetoothAdapter bluetoothAdapter;
    private RecyclerView rv;
    private BluetoothViewAdapter bluetoothViewAdapter;
    private BluetoothBean currentBluetoothBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        // Register for broadcasts when a device is discovered.
        initBlueTooth();
        initView();
    }

    private void initView() {
        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        bluetoothViewAdapter = new BluetoothViewAdapter(bList);
        rv.setAdapter(bluetoothViewAdapter);
        rv.performClick();
        bluetoothViewAdapter.setItemClickListener(new BluetoothViewAdapter.ItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                BluetoothBean bluetoothBean = bList.get(position);
                BluetoothDevice bluetoothDevice = bluetoothBean.getBluetoothDevice();
                if (bluetoothDevice.getBondState() != BluetoothDevice.BOND_BONDED) {
                    try {
                        currentBluetoothBean = bluetoothBean;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }


            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();


    }

    private void initBlueTooth() {
        // Get the default adapter
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Log.d(TAG, "initBlueTooth: 不支持蓝牙");
            return;
        }
        if (!bluetoothAdapter.isEnabled()) {
            Log.d(TAG, "initBlueTooth: 蓝牙不可用");
            Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

}