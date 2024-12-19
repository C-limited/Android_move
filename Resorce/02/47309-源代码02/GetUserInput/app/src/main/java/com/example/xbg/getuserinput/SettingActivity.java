package com.example.xbg.getuserinput;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class SettingActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.btnOk).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//执行确认操作，返回输入数据
                Intent intent=new Intent();
                EditText editText= (EditText) findViewById(R.id.editText);
                intent.putExtra("userinput",editText.getText().toString());//将输入装入Intent
                setResult(RESULT_OK,intent);//设置返回结果
                finish();//结束当前活动
            }
        });
        findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//执行取消操作
                Intent intent=new Intent();
                setResult(RESULT_CANCELED,intent);//设置返回结果
                finish();//结束当前活动
            }
        });
    }
}
