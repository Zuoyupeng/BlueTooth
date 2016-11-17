package com.zyp.bluetooth;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothServerSocket;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv;
    private Button open,close,server,client,sensor,znz;
    //声明一个BluetoothAdapter的对象
    BluetoothAdapter bluetoothAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView(){
        //得到一个bluetoothAdapter实例
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        tv = (TextView) findViewById(R.id.main_tv);
        //得到蓝牙的物理地址
        tv.setText(bluetoothAdapter.getName()+"\n"+bluetoothAdapter.getAddress());
        open = (Button) findViewById(R.id.main_btn_open);
        close = (Button) findViewById(R.id.main_btn_close);
        server = (Button) findViewById(R.id.main_btn_server);
        client = (Button) findViewById(R.id.main_btn_client);
        sensor = (Button) findViewById(R.id.main_btn_sensor);
        znz = (Button) findViewById(R.id.main_btn_znz);
        open.setOnClickListener(this);
        close.setOnClickListener(this);
        server.setOnClickListener(this);
        client.setOnClickListener(this);
        sensor.setOnClickListener(this);
        znz.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.main_btn_open:
                //隐式Intent的方式启动蓝牙Activity
                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
                //在蓝牙开启后指定时间内无设备连接就不能再连接了
                intent.putExtra(BluetoothAdapter.EXTRA_DISCOVERABLE_DURATION,120);
                //开启蓝牙
                startActivity(intent);
                break;
            case R.id.main_btn_close:
                //关闭蓝牙
                bluetoothAdapter.disable();
                break;
            case R.id.main_btn_server:
                Intent intent1 = new Intent(this,ServerActivity.class);
                startActivity(intent1);
                break;
            case R.id.main_btn_client:
                Intent intent2 = new Intent(this,ClientActivity.class);
                startActivity(intent2);
                break;
            case R.id.main_btn_sensor:
                Intent intent3 = new Intent(this,SensorActivity.class);
                startActivity(intent3);
                break;
            case R.id.main_btn_znz:
                Intent intent4 = new Intent(this,ZnzActivity.class);
                startActivity(intent4);
                break;
        }
    }
}
