package com.example.xbg.getuserinput;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
public class MainActivity extends AppCompatActivity {
    private  static int REQUEST_CODE=1000;//设置一个请求码
    private  int currentLayout;//用于记录当前布局ID
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currentLayout=R.layout.activity_main;
        initButton();
    }
    private void initButton(){//初始化布局按钮，添加监听器
        findViewById(R.id.btnSet).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SettingActivity.class);
                startActivityForResult(intent,REQUEST_CODE);//启动可返回结果的活动
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //处理返回结果
        if(requestCode==REQUEST_CODE){//返回的请求码与当前活动请求码一致时，才执行后继操作
            String msg;
            if(resultCode==RESULT_OK){//RESULT_OK表示返回的活动已成功处理请求
                String userinput=data.getStringExtra("userinput");
                msg="用户输入不是1或2，未改变布局";
                if(userinput.equals("1") & (currentLayout!=R.layout.activity_main)) {
                    setContentView(R.layout.activity_main);
                    currentLayout=R.layout.activity_main;
                    initButton();
                    msg="用户输入是1，设置为默认布局！";
                }else if(userinput.equals("2")  & (currentLayout==R.layout.activity_main)){
                    setContentView(R.layout.anotherlayout);
                    currentLayout=R.layout.anotherlayout;
                    initButton();
                    msg="用户输入是2，设置为另一个布局！";
                }else{
                    msg="用户输入是"+userinput+"，无需改变布局！";
                }
            }
            else{
                msg="用户取消了布局选择操作！";
            }
            TextView tv= (TextView) findViewById(R.id.textShowMsg);
            tv.setText(msg);
        }
    }
}
