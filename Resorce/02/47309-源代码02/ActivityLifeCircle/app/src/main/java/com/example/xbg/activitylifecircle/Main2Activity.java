package com.example.xbg.activitylifecircle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        System.out.println("正在执行Main2Activity.onCreate()");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("正在执行Main2Activity.onDestroy()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("正在执行Main2Activity.onStart()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("正在执行Main2Activity.onStop()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("正在执行Main2Activity.onPause()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("正在执行Main2Activity.onResume()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("正在执行Main2Activity.onRestart()");
    }
}
