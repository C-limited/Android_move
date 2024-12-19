package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void showAlertDialog(View view){
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("这是一个AlertDialog");
        dialog.setMessage("对话框详细消息：请选择“取消”还是“确认”？");
        dialog.setCancelable(false);//不能取消对话框
        dialog.setPositiveButton("确认", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv1= (TextView) findViewById(R.id.textView);
                tv1.setText("你选择了“确认”！");
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                TextView tv1= (TextView) findViewById(R.id.textView);
                tv1.setText("你选择了“取消”！");
            }
        });
        dialog.show();
    }
    public void  showProgressDialog(View view){
        ProgressDialog progressDialog=new ProgressDialog(MainActivity.this);//创建对话框
        progressDialog.setTitle("这是一个进度条对话框");//设置标题
        progressDialog.setMessage("请耐心等待，正在处理数据……");//设置消息
        progressDialog.setCancelable(true);//设置可取消
        progressDialog.show();//显示对话框
    }
    public void  showDateDialog(View view){
        //定义监听器
        DatePickerDialog.OnDateSetListener onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                TextView tv1= (TextView) findViewById(R.id.textView);
                String theDate=String.format("你选择的日期：%d年%d月#d日",year,month,dayOfMonth);
                tv1.setText(theDate);
            }
        };
        //定义DatePickerDialog对象
        DatePickerDialog datePickerDialog=new DatePickerDialog(MainActivity.this,onDateSetListener,2017,5,1);
        datePickerDialog.show();//显示日期选取对话框
    }
    public void  showTimeDialog(View view){
        //定义监听器
        TimePickerDialog.OnTimeSetListener onTimeSetListener=new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                TextView tv1= (TextView) findViewById(R.id.textView);
                String theTime=String.format("你选择的时间：%d:%d",hourOfDay,minute);
                tv1.setText(theTime);
            }
        };
        //定义TimePickerDialog对话框
        TimePickerDialog timePickerDialog=new TimePickerDialog(MainActivity.this,onTimeSetListener,0,0,true);
        timePickerDialog.show();//显示对话框
    }
}