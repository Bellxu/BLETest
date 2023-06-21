package com.bellxu.bletest;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BluetoothViewAdapter extends RecyclerView.Adapter<BluetoothViewAdapter.BluetoothViewHolder> {
    private  List<BluetoothBean> data;
    private  ItemClickListener itemClickListener;

    public BluetoothViewAdapter(List<BluetoothBean> bList) {
        this.data = bList;
    }

    @NonNull
    @Override
    public BluetoothViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bluetooth_layout_item, parent, false);
        return new BluetoothViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BluetoothViewHolder viewHolder, int position) {
        BluetoothBean bluetoothBean = data.get(position);
        viewHolder.tv_device_name.setText("设备名:"+bluetoothBean.getDeviceName());
        viewHolder.tv_blue_address.setText("设备地址:"+bluetoothBean.getDeviceHardwareAddress());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null) {
                    itemClickListener.onItemClick(viewHolder.itemView,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size();
    }


    public static class BluetoothViewHolder extends RecyclerView.ViewHolder {

        public TextView tv_device_name;
        public TextView tv_blue_address;

        public BluetoothViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_device_name = itemView.findViewById(R.id.tv_device_name);
            tv_blue_address = itemView.findViewById(R.id.tv_device_address);
        }
    }

    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }
}
