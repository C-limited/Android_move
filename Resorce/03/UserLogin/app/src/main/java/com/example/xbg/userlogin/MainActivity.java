package com.example.xbg.userlogin;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] UserNames;
    private String[] Passwords;
    EditText etUserName;
    EditText etPassword;
    TextView tvShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UserNames= getResources().getStringArray(R.array.UserNames);
        Passwords= getResources().getStringArray(R.array.Passwords);
        tvShow=(TextView)findViewById(R.id.showMsg);
        etUserName=(EditText)findViewById(R.id.userName);
        etUserName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //文本改变之前调用
            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //文本正在改变时调用
                tvShow.setText("");//在输入新的用户名时，清除TextView显示的提示信息
            }
            @Override
            public void afterTextChanged(Editable s) {
                //文本改变之后调用
            }
        });
        etPassword=(EditText)findViewById(R.id.password);
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tvShow.setText("");//在输入新的用户名时，清除TextView显示的提示信息
            }
            @Override
            public void afterTextChanged(Editable s) {}
        });
    }
    public void checkLog(View view){
        String userName=etUserName.getText().toString();//获得收入的用户名
        String password=etPassword.getText().toString();//获得收入的密码
        //判断用户名是否正确
        int index;
        boolean isLoged=false;
        for(index=0;index<UserNames.length;index++){
            if(userName.equals(UserNames[index])) {
                if (password.equals(Passwords[index])) {
                    isLoged = true;
                    break;//用户名和密码都正确，不再继续验证
                }
            }
        }
        if(isLoged){
            //执行登录成功操作
            tvShow.setText("用户名和密码正确，登录成功！");
        }else {
            //执行登录失败操作
            tvShow.setText("用户名或密码错误，请重新登录！");
        }
        /*使用对话框显示验证信息
        AlertDialog.Builder dialog=new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("系统登录");
        String msg;
        if(isLoged){
            msg="用户名和密码正确，登录成功！";
        }else {
            msg="用户名或密码错误，请重新登录！";
        }
        dialog.setMessage(msg);
        dialog.show();*/
    }
}
