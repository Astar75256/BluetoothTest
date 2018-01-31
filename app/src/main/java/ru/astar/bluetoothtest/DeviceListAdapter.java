package ru.astar.bluetoothtest;

import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by molot on 30.01.2018.
 */

public class DeviceListAdapter extends ArrayAdapter<BluetoothDevice> {

    private LayoutInflater mLayoutInflater;
    private int mResourceView;
    private ArrayList<BluetoothDevice> mDevices = new ArrayList<>();

    public DeviceListAdapter(Context context, int resource, ArrayList<BluetoothDevice> devices) {
        super(context, resource, devices);

        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mResourceView = resource;
        mDevices = devices;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mLayoutInflater.inflate(mResourceView, null);

        BluetoothDevice device = mDevices.get(position);

        TextView tvName = convertView.findViewById(R.id.tvNameDevice);
        TextView tvAddress = convertView.findViewById(R.id.tvAddressDevice);

        tvName.setText(device.getName());
        tvAddress.setText(device.getAddress());


        return convertView;
    }
}
