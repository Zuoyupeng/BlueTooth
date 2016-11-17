package com.zyp.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.UUID;

public class ClientActivity extends AppCompatActivity {

    BluetoothAdapter bluetoothAdapter;
    BluetoothSocket socket;
    BluetoothDevice device;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        new ClientAsyncTask().execute();
    }

    /**
     * 客户端连接服务器的方法
     */
    private void connect(){
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        device = bluetoothAdapter.getRemoteDevice("F8:A4:5F:D9:67:52");
        //38:BC:1A:31:7E:9E
        try {
            socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00000000-2527-eef3-ffff-ffffe3160865"));
            if(socket != null){
                socket.connect();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 发送数据
     */
    private void send(){
        try {
            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter streamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(streamWriter);
            bufferedWriter.write("客户端已连接"+"\r\n");
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 异步线程
     */
    class ClientAsyncTask extends AsyncTask<String,String,String>{

        protected String doInBackground(String... strings) {
            connect();
            send();
            return null;
        }
    }

}
