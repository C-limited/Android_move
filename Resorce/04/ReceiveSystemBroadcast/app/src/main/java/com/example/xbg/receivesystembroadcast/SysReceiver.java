package com.example.xbg.receivesystembroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.provider.Settings;
import android.widget.Toast;

public class SysReceiver extends BroadcastReceiver {
    public SysReceiver() { }
    @Override
    public void onReceive(Context context, Intent intent) {
        int wifiState = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, 0);
        if(wifiState==WifiManager.WIFI_STATE_DISABLED){
            Toast.makeText(context,"WIFI连接已关闭！",Toast.LENGTH_SHORT).show();
        }else if(wifiState==WifiManager.WIFI_STATE_ENABLED){
            Toast.makeText(context,"WIFI已连接！",Toast.LENGTH_SHORT).show();
        }
    }
}
