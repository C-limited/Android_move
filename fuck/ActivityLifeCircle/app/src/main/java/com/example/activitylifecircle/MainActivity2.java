package com.example.activitylifecircle;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        System.out.println("正在执行Main2.oncreate");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("正在执行Main2.onDestroy");
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("正在执行Main2.onStart");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("正在执行Main2.onStop");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("正在执行Main2.onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("正在执行Main2.onResume");

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("正在执行Main2.onRestart");
    }
}