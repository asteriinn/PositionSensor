package com.example.positionsensor;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity implements SensorEventListener {

    SensorManager sensorManager;
    Sensor accelerometerSensor;
    Sensor gyroscopeSensor;
    Sensor magnetometerSensor;

    //textview for accelerometer axis
    TextView xAxisAccelerometerTV;
    TextView yAxisAccelerometerTV;
    TextView zAxisAccelerometerTV;

    //textview for gyroscope axis
    TextView xAxisGyroscopeTV;
    TextView yAxisGyroscopeTV;
    TextView zAxisGyroscopeTV;

    //textview for magnetometer axis
    TextView xAxisMagnetometerTV;
    TextView yAxisMagnetometerTV;
    TextView zAxisMagnetometerTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize
        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        magnetometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

        xAxisAccelerometerTV = findViewById(R.id.xAxisAccelerometerTextView);
        yAxisAccelerometerTV = findViewById(R.id.yAxisAccelerometerTextView);
        zAxisAccelerometerTV = findViewById(R.id.zAxisAccelerometerTextView);

        xAxisGyroscopeTV = findViewById(R.id.xAxisGyroscopeTextView);
        yAxisGyroscopeTV = findViewById(R.id.yAxisGyroscopeTextView);
        zAxisGyroscopeTV = findViewById(R.id.zAxisGyroscopeTextView);

        xAxisMagnetometerTV = findViewById(R.id.xAxisMagnetometerTextView);
        yAxisMagnetometerTV = findViewById(R.id.yAxisMagnetometerTextView);
        zAxisMagnetometerTV = findViewById(R.id.zAxisMagnetometerTextView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Register sensor listeners
        sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, gyroscopeSensor, SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, magnetometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        switch (event.sensor.getType()) {
            case Sensor.TYPE_ACCELEROMETER:
                displayAccelerometerValues(event.values);
            case Sensor.TYPE_GYROSCOPE:
                displayGyroscopeValues(event.values);
            case Sensor.TYPE_MAGNETIC_FIELD:
                displayMagnetometerValues(event.values);
                break;

            default:
                break;
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    private void displayAccelerometerValues(float[] values) {
        xAxisAccelerometerTV.setText("Accelerometer X-axis: " + values[0]);
        yAxisAccelerometerTV.setText("Accelerometer Y-axis: " + values[1]);
        zAxisAccelerometerTV.setText("Accelerometer Z-axis: " + values[2]);
    }

    private void displayGyroscopeValues(float[] values) {
        xAxisGyroscopeTV.setText("Gyroscope X-axis: " + values[0]);
        yAxisGyroscopeTV.setText("Gyroscope Y-axis: " + values[1]);
        zAxisGyroscopeTV.setText("Gyroscope Z-axis: " + values[2]);
    }

    private void displayMagnetometerValues(float[] values) {
        xAxisMagnetometerTV.setText("Magnetometer X-axis: " + values[0]);
        yAxisMagnetometerTV.setText("Magnetometer Y-axis: " + values[1]);
        zAxisMagnetometerTV.setText("Magnetometer Z-axis: " + values[2]);
    }
}