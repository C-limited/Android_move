package com.example.xbg.activitylifecircle;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("正在执行MainActivity.onCreate()");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Main2Activity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("正在执行MainActivity.onDestroy()");
    }
    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("正在执行MainActivity.onStart()");
    }
    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("正在执行MainActivity.onStop()");
    }
    @Override
    protected void onPause() {
        super.onPause();
        System.out.println("正在执行MainActivity.onPause()");
    }
    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("正在执行MainActivity.onResume()");
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        System.out.println("正在执行MainActivity.onRestart()");
    }
}
