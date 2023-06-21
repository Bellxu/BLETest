package com.bellxu.bletest;

import android.bluetooth.BluetoothDevice;

import androidx.annotation.Nullable;

public class BluetoothBean {
    private String deviceName;
    private String deviceHardwareAddress;
    private BluetoothDevice bluetoothDevice;


    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceHardwareAddress() {
        return deviceHardwareAddress;
    }

    public void setDeviceHardwareAddress(String deviceHardwareAddress) {
        this.deviceHardwareAddress = deviceHardwareAddress;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof BluetoothBean) {
            return ((BluetoothBean) obj).getDeviceName().equals(this.deviceName) && ((BluetoothBean) obj).getDeviceHardwareAddress().equals(this.deviceHardwareAddress);
        }
        return false;
    }

    public void setDevice(BluetoothDevice bluetoothDevice) {
        this.bluetoothDevice=bluetoothDevice;
    }

    public BluetoothDevice getBluetoothDevice() {
        return bluetoothDevice;
    }
}
