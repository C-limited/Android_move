package com.example.xbg.receivedatafromactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class SendDataBack extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_data_back);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent();
                EditText editText= (EditText) findViewById(R.id.editText);
                intent.putExtra("data",editText.getText().toString());//将输入装入Intent
                setResult(RESULT_OK,intent);//设置返回结果
                finish();//结束当前活动
            }
        });
    }
}
