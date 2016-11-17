package com.zyp.bluetooth;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SensorActivity extends AppCompatActivity {
    //传感器管理类对象
    private SensorManager sensorManager;
    private TextView tv;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        tv = (TextView) findViewById(R.id.sensor_tv);
        //通过Context.SENSOR_SERVICE获取到传感器的管理类实例
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        //获取到加速度传感器的实例对象
        //Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
    }

    /*
     * 界面可见并且能获取焦点，调用
     */
    protected void onResume() {
        super.onResume();
        /*
        注册传感器监听器
         */
        //加速度
        sensorManager.registerListener(listener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        //方向
        sensorManager.registerListener(listener1,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_NORMAL);
        //陀螺仪
        sensorManager.registerListener(listener2,
                sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE), SensorManager.SENSOR_DELAY_NORMAL);
        //重力
        sensorManager.registerListener(listener3,
                sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY), SensorManager.SENSOR_DELAY_NORMAL);
        //线性加速度
        sensorManager.registerListener(listener4,
                sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION), SensorManager.SENSOR_DELAY_NORMAL);
        //光传感器
        sensorManager.registerListener(listener5,
                sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT), SensorManager.SENSOR_DELAY_NORMAL);
    }

    /*
     * 界面消失
     */
    protected void onStop() {
        super.onStop();
        //取消传感器的监听
        sensorManager.unregisterListener(listener);
        sensorManager.unregisterListener(listener1);
        sensorManager.unregisterListener(listener2);
        sensorManager.unregisterListener(listener3);
        sensorManager.unregisterListener(listener4);
        sensorManager.unregisterListener(listener5);
    }

    /**
     * 创建一个加速度的监听器对象
     */
    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            /*
             * 通过SensorEvent对象的values数组可以获取到x,y,z方向上的加速度，
             * 一旦有加速度的变化，就调用该方法
             */
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];
            tv.setText("========加速度传感器返回的值========" + "\r\n");
            tv.append("X轴加速度：" + x + "\r\n");
            tv.append("\r\n"+"Y轴加速度：" + y + "\r\n");
            tv.append("\r\n"+"Z轴加速度：" + z + "\r\n");
        }
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
    /**
     * 创建一个方向传感器
     */
    SensorEventListener listener1 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x1 = sensorEvent.values[0];
            float y2 = sensorEvent.values[1];
            float z3 = sensorEvent.values[2];
            tv.append("========方向传感器返回的值========" + "\r\n");
            tv.append("X轴方向角度" + x1 + "\r\n");
            tv.append("\r\n"+"Y轴方向角度" + y2 + "\r\n");
            tv.append("\r\n"+"Z轴方向角度" + z3 + "\r\n");
        }
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /**
     * 创建一个陀螺仪传感器
     */
    SensorEventListener listener2 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x11 = sensorEvent.values[0];
            float y22 = sensorEvent.values[1];
            float z33 = sensorEvent.values[2];
            tv.append("========陀螺仪传感器返回的值========" + "\r\n");
            tv.append("绕X轴旋转的角速度" + x11 + "\r\n");
            tv.append("\r\n"+"绕Y轴旋转的角速度" + y22 + "\r\n");
            tv.append("\r\n"+"绕Z轴旋转的角速度" + z33 + "\r\n");
        }
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /**
     * 创建一个重力传感器
     */
    SensorEventListener listener3 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x111 = sensorEvent.values[0];
            float y222 = sensorEvent.values[1];
            float z333 = sensorEvent.values[2];
            tv.append("========重力传感器返回的值========" + "\r\n");
            tv.append("X轴方向上的重力" + x111 + "\r\n");
            tv.append("\r\n"+"Y轴方向上的重力" + y222 + "\r\n");
            tv.append("\r\n"+"Z轴方向上的重力" + z333 + "\r\n");
        }
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /**
     * 创建一个线性加速度传感器
     */
    SensorEventListener listener4 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x1111 = sensorEvent.values[0];
            float y2222 = sensorEvent.values[1];
            float z3333 = sensorEvent.values[2];
            tv.append("========线性加速度传感器返回的值========" + "\r\n");
            tv.append("X轴方向上的线性加速度" + x1111 + "\r\n");
            tv.append("\r\n"+"Y轴方向上的线性加速度" + y2222 + "\r\n");
            tv.append("\r\n"+"Z轴方向上的线性加速度" + z3333 + "\r\n");
        }
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };

    /**
     * 创建一个光传感器
     */
    SensorEventListener listener5 = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float x11111 = sensorEvent.values[0];
            tv.append("========光传感器返回的值========" + "\r\n");
            tv.append("当前光的强度为：");
            tv.append("\r\n"+x11111 + "\r\n");
        }
        public void onAccuracyChanged(Sensor sensor, int i) {
        }
    };
}
