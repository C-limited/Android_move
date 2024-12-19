package com.example.xbg.usesharedpreference;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  EditText etName;
    private  EditText etPwd;
    private   CheckBox cbRemember;
    private  Button btLogin;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //SharedPreferences pref=getSharedPreferences("myPreferences",MODE_PRIVATE);
        //SharedPreferences pref=getPreferences(MODE_PRIVATE);
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        boolean isRemembered=pref.getBoolean("remembered",false);
        etName= (EditText) findViewById(R.id.etName);
        etPwd= (EditText) findViewById(R.id.etPwd);
        cbRemember= (CheckBox) findViewById(R.id.cbRemember);
        if(isRemembered){//根据存储的数据初始化界面
            etName.setText(pref.getString("username",""));
            etPwd.setText(pref.getString("password",""));
            cbRemember.setChecked(true);
        }
        btLogin= (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckLog()){
                    //登录信息正确，判断是否需要保存当前登录信息
                    if(cbRemember.isChecked()){
                        //保存登录信息
                        String id=etName.getText().toString();
                        String pwd=etPwd.getText().toString();
                        editor=pref.edit();
                        editor.putString("username",id);
                        editor.putString("password",pwd);
                        editor.putBoolean("remembered",true);
                        Toast.makeText(MainActivity.this,"登录信息已保存！",
                                Toast.LENGTH_SHORT).show();
                    }else{
                        editor.clear();//清除SharedPreferences数据
                    }
                    editor.apply();//使SharedPreferences修改生效
                    Toast.makeText(MainActivity.this,"登录成功！",
                            Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"用户名或密码错误！",
                                     Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  boolean CheckLog(){
        //判断用户登录信息是否正确
        String id=etName.getText().toString();
        String pwd=etPwd.getText().toString();
        //如果用户名是admin，密码是123，则认为登录信息正确
        if(id.equals("admin") && pwd.equals("123")){
            return  true;
        }else{
            return false;
        }
    }
}
