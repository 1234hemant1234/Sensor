package hm.project.sensor;

import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.hardware.SensorManager;
import android.content.Context;
import android.hardware.Sensor;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView sensorTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sensorTextView = findViewById(R.id.sensor_info);

        SensorManager sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        String sensorName = getString(R.string.sensor_name);
        sensorName = sensorName.concat(sensor.getName());
        sensorTextView.setText(sensorName);
        String sensorVersion = getString(R.string.sensor_version);
        sensorVersion = sensorVersion.concat(sensor.getVersion()+"");

        sensorTextView.append("/n" + sensorVersion);
        String sensorResolution = getString(R.string.sensor_resolution);
        sensorResolution = sensorResolution.concat(sensor.getResolution()+ "");
        sensorTextView.append("\n" + sensorResolution);

        sensorManager.registerListener((SensorEventListener) this,sensor,sensorManager.SENSOR_DELAY_NORMAL);

        /*
         * Attributes for sensors in Android
         * 1. Name of the sensor
         * 2. Power consumption in mA
         * 3. Resolution
         * 4. Maximum Range
         * 5. Vendor
         * 6. Version
         * */

    }


    @Override
    public void onSensorChanged(SensorEvent event)
    {
       try{
           float x = event.values[0];
           float y = event.values[1];
           float z = event.values[2];

           sensorTextView.setText(event.timestamp + ": ( " + x + ","+ y + "," + z );
       }
       catch (Exception e){
           sensorTextView.setText(e.getMessage());
       }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}