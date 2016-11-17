package com.zyp.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.UUID;

public class ServerActivity extends AppCompatActivity {

    BluetoothAdapter adapter;
    BluetoothServerSocket serverSocket;
    BluetoothSocket socket;
    TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_server);
        init();
        new ServerAsyncTask().execute();
    }

    private  void  init(){
        tv = (TextView) findViewById(R.id.server_tv);
        adapter = BluetoothAdapter.getDefaultAdapter();
        try {
            serverSocket = adapter.listenUsingRfcommWithServiceRecord("zyp",
                    UUID.fromString("00000000-2527-eef3-ffff-ffffe3160865"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 用来开启蓝牙服务端
     */
    class ServerAsyncTask extends AsyncTask<String,String,String>{

        protected String doInBackground(String... strings) {
            publishProgress("开启蓝牙服务器");
            try {
                socket = serverSocket.accept();
                publishProgress("已有设备连接");
                InputStream inputStream = socket.getInputStream();
                InputStreamReader streamReader= new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                while ((line = reader.readLine()) != null){
                    publishProgress(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        /*
         * 用来更新显示客户端传过来的数据
         */
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            tv.append(values[0]+"\r\n");
        }
    }

}
