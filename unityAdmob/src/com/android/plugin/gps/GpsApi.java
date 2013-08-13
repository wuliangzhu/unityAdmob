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
            Toast.makeText(act, "GPS模块正常", Toast.LENGTH_SHORT)
                    .show();
            return;
        }

        Toast.makeText(act, "请开启GPS！", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(Settings.ACTION_SECURITY_SETTINGS);
        act.startActivityForResult(intent,0); //此为设置完成后返回到获取界面

    }
	
	public void getLocation()
    {
        // 获取位置管理服务
        LocationManager locationManager;
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) act.getSystemService(serviceName);
        // 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

        String provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        Location location = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        updateToNewLocation(location);
        // 设置监听器，自动更新的最小时间为间隔N秒(1秒为1*1000，这样写主要为了方便)或最小位移变化超过N米
        locationManager.requestLocationUpdates(provider, 100 * 1000, 500, new GpsListener());
    }
	
	private void updateToNewLocation(Location location) {

        TextView tv1;
        tv1 = null;//(TextView) this.findViewById(R.id.);
        if (location != null) {
            double  latitude = location.getLatitude();
            double longitude= location.getLongitude();
            tv1.setText("维度：" +  latitude+ "\n经度" + longitude);
        } else {
            tv1.setText("无法获取地理信息");
        }

    }
}
