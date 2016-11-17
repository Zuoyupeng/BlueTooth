package com.zyp.bluetooth;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class ZnzActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private ImageView iv;
    private TextView tv;
    float currentDegree = 0f;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_znz);
        iv = (ImageView) findViewById(R.id.znz_iv);
        tv = (TextView) findViewById(R.id.znz_tv);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
    }

    @Override
    protected void onResume() {
        sensorManager.registerListener(listener,
                sensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_UI);
        super.onResume();
    }

    @Override
    protected void onStop() {
        sensorManager.unregisterListener(listener);
        super.onStop();
    }

    SensorEventListener listener = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            float degree = sensorEvent.values[0];
            int a = (int) degree;
            tv.setText(a+"°");
            RotateAnimation animation = new RotateAnimation(currentDegree,-degree,
                    Animation.RELATIVE_TO_SELF,0.5f,Animation.RELATIVE_TO_SELF,0.5f);
            animation.setDuration(200);
            iv.startAnimation(animation);
            currentDegree = -degree;
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    };
}
