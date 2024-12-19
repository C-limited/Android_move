package com.example.pad.loginfromdatabase;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.preference.PreferenceManager;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etName;
    private  EditText etPwd;
    private CheckBox cbRemember;
    private Button btLogin;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private MySQLiteHelper sqLiteHelper;
    private SQLiteDatabase myDb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etName= (EditText) findViewById(R.id.etName);
        etPwd= (EditText) findViewById(R.id.etPwd);
        cbRemember= (CheckBox) findViewById(R.id.cbRemember);

        sqLiteHelper=new MySQLiteHelper(MainActivity.this,"usersdb.db",null,1);
        myDb=sqLiteHelper.getWritableDatabase();//完成创建或打开数据库
        //判断是否记住密码来初始化登录界面
        pref= PreferenceManager.getDefaultSharedPreferences(this);
        editor=pref.edit();
        boolean isRemembered=pref.getBoolean("remembered",false);
        if(isRemembered){
            //执行查询获得当前记住密码的用户登录信息
            Cursor c=myDb.rawQuery("select * from users where remembered=1",null);
            if(c.moveToFirst()){
                //用查询结果初始化界面
                etName.setText(c.getString(c.getColumnIndex("userid")));
                etPwd.setText(c.getString(c.getColumnIndex("password")));
                cbRemember.setChecked(true);
            }
        }
        btLogin= (Button) findViewById(R.id.btLogin);
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(CheckLog()){
                    //登录信息正确，判断是否需要保存当前登录信息
                    if(cbRemember.isChecked()){
                        String id=etName.getText().toString();
                        //更新数据库中记住密码标志
                        myDb.execSQL("update users set remembered=1 where userid=?", new String[]{id});
                        myDb.execSQL("update users set remembered=0 where userid<>?", new String[]{id});
                        editor.putBoolean("remembered",true);
                    }else{
                        //更新数据库中记住密码标志
                        myDb.execSQL("update users set remembered=0");
                        editor.clear();//清除SharedPreferences数据
                    }
                    editor.apply();//使SharedPreferences修改生效
                    Toast.makeText(MainActivity.this,"登录成功！",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"用户名或密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private  boolean CheckLog(){
        //判断用户登录信息是否正确
        String id=etName.getText().toString();
        String pwd=etPwd.getText().toString();
        //Cursor c=myDb.rawQuery("select * from users where userid=? and password=?",new String[]{id,pwd});
        Cursor c=myDb.query("users",new String[]{"userid"},
                "userid=? and password=?",new String[]{id,pwd},null,null,null);
        if(c.moveToFirst()){
            return  true;
        }else{
            return false;
        }
    }

}
