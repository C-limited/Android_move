package com.example.toast;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(MainActivity.this,
                        "这是一个较短时间的Toast",Toast.LENGTH_SHORT);
                toast.show();//显示Toast
            }
        });
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast=Toast.makeText(MainActivity.this,
                        "这是一个较长时间的Toast",Toast.LENGTH_SHORT);
                toast.show();//显示Toast
            }
        });
        findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast= new Toast(MainActivity.this);                        //生成Toast对象
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.toastlayout,
                        (ViewGroup) findViewById(R.id.toast_layout));   //将布局文件转换为视图对象
                TextView textView= (TextView) layout.findViewById(R.id.textView);
                textView.setText("这是一个自定义视图的Toast");    //设置布局中文本视图控件显示的文本
                toast.setView(layout);                              //设置Toast显示的视图
                toast.setDuration(Toast.LENGTH_LONG);           //设置视图显示时间
                toast.show();                                   //显示Toast
            }
        });
    }
}
