package com.android.plugin.gps;

import com.android.plugin.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.widget.TextView;
import android.widget.Toast;
import android.location.LocationListener;;

public class GpsApi {
	private Activity act;
	
	public GpsApi(Activity act){
		this.act = act;
	}
	public void openGPSSettings() {
        LocationManager alm = (LocationManager) act
                .getSystemService(Context.LOCATION_SERVICE);
        if (alm
                .isProviderEnabled(android.location.LocationManager.GPS_PROVIDER)) {
            Toast.makeText(act, "GPSģ������", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Toast.makeText(act, "�뿪��GPS��", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        act.startActivityForResult(intent,0); //��Ϊ������ɺ󷵻ص���ȡ����

    }
	
	public void getLocation()
    {
        // ��ȡλ�ù������
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) act.getSystemService(serviceName);
        // ���ҵ�������Ϣ
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // �߾���
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // �͹���

        String provider = locationManager.getBestProvider(criteria, true); // ��ȡGPS��Ϣ
        Location location = locationManager.getLastKnownLocation(provider); // ͨ��GPS��ȡλ��
        updateToNewLocation(location);
        // ���ü��������Զ����µ���Сʱ��Ϊ���N��(1��Ϊ1*1000������д��ҪΪ�˷���)����Сλ�Ʊ仯����N��
        locationManager.requestLocationUpdates(provider, 100 * 1000, 500, new GpsListener());
    }
	
	private void updateToNewLocation(Location location) {

        TextView tv1;
        tv1 = null;//(TextView) this.findViewById(R.id.);
        if (location != null) {
            double  latitude = location.getLatitude();
            double longitude= location.getLongitude();
            tv1.setText("ά�ȣ�" +  latitude+ "\n����" + longitude);
        } else {
            tv1.setText("�޷���ȡ������Ϣ");
        }

    }
}
