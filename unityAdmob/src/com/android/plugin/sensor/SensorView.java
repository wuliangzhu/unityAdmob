package com.android.plugin.sensor;

import java.util.List;

import com.android.plugin.R;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class SensorView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.setContentView(R.layout.activity_main);
		
		TextView tv = (TextView)this.findViewById(R.id.sensorDesc);
		
		StringBuffer sb = new StringBuffer();
		SensorManager mSensorMgr = (SensorManager) getSystemService(SENSOR_SERVICE);  
		
		List<Sensor> sensorList = mSensorMgr.getSensorList(Sensor.TYPE_ALL);
		for (Sensor sensor : sensorList) {
			sb.append(sensor.getName() + "-" + sensor.getMinDelay() + "-" + sensor.getResolution() + "\n");
		}
		
		tv.setText(sb.toString());
	}

}
