package com.example.xbg.receiverinanotherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.widget.Toast;

public class testReceiver extends BroadcastReceiver {
    public testReceiver() {
    }
    @Override
    public void onReceive(Context context, Intent intent) {
            int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
            if(wifiState==WifiManager.WIFI_STATE_DISABLED) {
                Toast.makeText(context, "WIFI不可用", Toast.LENGTH_LONG).show();
            }else if(wifiState==WifiManager.WIFI_STATE_ENABLED){
                Toast.makeText(context, "WIFI可用", Toast.LENGTH_LONG).show();
            }
    }
}
