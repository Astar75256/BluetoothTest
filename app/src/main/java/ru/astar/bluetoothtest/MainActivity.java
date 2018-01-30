package ru.astar.bluetoothtest;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_ENABLE_BLUETOOTH = 1001;
    public final String TAG = getClass().getSimpleName();

    private Button btLedOne;
    private Button btLedTwo;

    private boolean isEnabledLedOne = false;
    private boolean isEnabledLedTwo = false;

    private BluetoothAdapter mBluetoothAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btLedOne = findViewById(R.id.btLedOne);
        btLedTwo = findViewById(R.id.btLedTwo);
        btLedOne.setOnClickListener(clickListener);
        btLedTwo.setOnClickListener(clickListener);

        mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (mBluetoothAdapter == null) {
            Log.d(TAG, "onCreate: Ваше устройство не поддерживает bluetooth");
            finish();
        }

        // включаем bluetooth
        enableBluetooth();
    }

    /**
     * Включаем bluetooth
     */
    private void enableBluetooth() {
        Log.d(TAG, "enableBluetooth()");
        if (!mBluetoothAdapter.isEnabled()) {
            Log.d(TAG, "enableBluetooth: Bluetooth выключен, пытаемся включить");
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQ_ENABLE_BLUETOOTH);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // принудительно пытаемся включить bluetooth
        if (requestCode == REQ_ENABLE_BLUETOOTH) {
            if (!mBluetoothAdapter.isEnabled()) {
                Log.d(TAG, "onActivityResult: Повторно пытаемся отправить запрос на включение bluetooth");
                enableBluetooth();
            }
        }
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            // нажата кнопка для управления первым светодиодом
            if (view.equals(btLedOne)) {
                isEnabledLedOne = !isEnabledLedOne;
                Log.d(TAG, "onClick: isEnabledLedOne = " + isEnabledLedOne);
            }

            // нажата кнопка для управления вторым светодиодом
            if (view.equals(btLedTwo)) {
                isEnabledLedTwo = !isEnabledLedTwo;
                Log.d(TAG, "onClick: isEnabledLedTwo = " + isEnabledLedTwo);
            }
        }
    };
}
