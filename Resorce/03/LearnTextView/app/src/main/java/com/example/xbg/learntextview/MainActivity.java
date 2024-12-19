package com.example.xbg.learntextview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt2=(Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv1= (TextView) findViewById(R.id.textView);
                tv1.setText("单击按钮Button2");
            }
        });
    }
    public void ClickButton1(View view){
        TextView tv1= (TextView) findViewById(R.id.textView);
        tv1.setText("单击按钮Button1");
    }
}
