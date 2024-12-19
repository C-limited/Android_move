package com.example.pad.sendsimpledata;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ReceiveDataActivity.class);
                //传递简单数据
                //intent.putExtra("name","极客学院");
                //intent.putExtra("age",5);

                //传递Bundle对象
                Bundle bd=new Bundle();
                bd.putString("name","极客学院");
                bd.putInt("age",5);
                intent.putExtras(bd);
                startActivity(intent);
            }
        });
    }
}
