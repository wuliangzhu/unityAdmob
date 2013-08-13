package com.android.plugin;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest;
import com.google.ads.AdSize;
import com.google.ads.AdView;
import com.google.ads.AdRequest.ErrorCode;
import com.unity3d.player.UnityPlayer;
import com.unity3d.player.UnityPlayerActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
public class AriAndroidPluginActivity extends UnityPlayerActivity {
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    private static void setupAds() {
        UnityPlayer.currentActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                LinearLayout layout = new LinearLayout(
                        UnityPlayer.currentActivity.getApplicationContext());
                layout.setOrientation(LinearLayout.VERTICAL);
                layout.setGravity(Gravity.BOTTOM);
                UnityPlayer.currentActivity.addContentView(layout,
                        new LayoutParams(LayoutParams.FILL_PARENT,
                                LayoutParams.FILL_PARENT));
                AdView adView = new AdView(UnityPlayer.currentActivity,
                        AdSize.BANNER, "a1510c9070b657b");
                layout.addView(adView, new LayoutParams(
                        LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT));
                adView.setBackgroundColor(0xff000000);
                adView.setAdListener(new AdListener() {
                    @Override
                    public void onReceiveAd(Ad arg0) {
                        // TODO Auto-generated method stub
                        Log.i("admob ari", "onReceiveAd");
                    }
                    @Override
                    public void onPresentScreen(Ad arg0) {
                        // TODO Auto-generated method stub
                        Log.i("admob ari", "onPresentScreen");
                    }
                    @Override
                    public void onLeaveApplication(Ad arg0) {
                        // TODO Auto-generated method stub
                        Log.i("admob ari", "onLeaveApplication");
                    }
                    @Override
                    public void onFailedToReceiveAd(Ad arg0, ErrorCode arg1) {
                        // TODO Auto-generated method stub
                        Log.i("admob ari", "onFailedToReceiveAd+" + arg1);
                    }
                    @Override
                    public void onDismissScreen(Ad arg0) {
                        // TODO Auto-generated method stub
                        Log.i("admob ari", "onDismissScreen");
                    }
                });
                Log.i("admob ari", "adView.loadAd(new AdRequest())");
                AdRequest adRequest = new AdRequest();
                adRequest.addTestDevice("000000000000000");
                adView.loadAd(adRequest);
            }
        });
    }
}
