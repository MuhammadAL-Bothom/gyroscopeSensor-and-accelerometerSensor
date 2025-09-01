package com.example.sensors;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor gyroscopeSensor, accelerometerSensor;
    private TextView gyroTextView, accelTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // Apply Edge-to-Edge insets to handle system UI padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize TextViews for gyroscope and accelerometer data
        gyroTextView = findViewById(R.id.gyroTextView);
        accelTextView = findViewById(R.id.accelTextView);

        // Initialize SensorManager
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        if (sensorManager != null) {
            gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        }

        // Check if gyroscope sensor is available
        if (gyroscopeSensor == null) {
            Toast.makeText(this, "Gyroscope not available on this device", Toast.LENGTH_SHORT).show();
        }

        // Check if accelerometer sensor is available
        if (accelerometerSensor == null) {
            Toast.makeText(this, "Accelerometer not available on this device", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            // Update gyroscope data
            float rotationX = event.values[0];
            float rotationY = event.values[1];
            float rotationZ = event.values[2];
            String gyroData = "Gyroscope - Rotation\n X: " + rotationX + "\n Y: " + rotationY + "\n Z: " + rotationZ;
            Log.d("Gyroscope", gyroData);
            gyroTextView.setText(gyroData);
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            // Update accelerometer data
            float accelX = event.values[0];
            float accelY = event.values[1];
            float accelZ = event.values[2];
            String accelData = "Accelerometer - Acceleration\n X: " + accelX + "\n Y: " + accelY + "\n Z: " + accelZ;
            Log.d("Accelerometer", accelData);
            accelTextView.setText(accelData);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
        // Required for SensorEventListener but not used here
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (gyroscopeSensor != null) {
            sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }
}
