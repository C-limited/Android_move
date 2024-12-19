package com.example.activitylifecircle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("正在执行Main.onCreate");
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,MainActivity2.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        System.out.println("正在执行Main.onDestroy");
    }
    @Override
    protected void onStart(){
        super.onStart();
        System.out.println("正在执行Main.onStart");
    }
    @Override
    protected void onStop(){
        super.onStop();
        System.out.println("正在执行Main.onStop");
    }
    @Override
    protected void onPause(){
        super.onPause();
        System.out.println("正在执行Main.onPause");
    }
    @Override
    protected void onResume(){
        super.onResume();
        System.out.println("正在执行Main.onResume");

    }
    @Override
    protected void onRestart(){
        super.onRestart();
        System.out.println("正在执行Main.onRestart");
    }

}