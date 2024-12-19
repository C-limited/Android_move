package com.example.xbg.usesqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {
    private MySQLiteHelper sqLiteHelper;
    private SQLiteDatabase myDb;
    TextView tvPath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvPath= (TextView) findViewById(R.id.txtPath);
        Button btCreateDb=(Button)findViewById(R.id.btCreateDb);
        btCreateDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper=new MySQLiteHelper(MainActivity.this,"usersdb.db",null,1);
                myDb=sqLiteHelper.getWritableDatabase();//完成创建数据库
                String path=myDb.getPath();
                tvPath.setText("数据库："+path);//显示数据库文件及其路径
            }
        });
        Button btDeleteDb=(Button)findViewById(R.id.btDeleteDb);
        btDeleteDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb.isOpen()){
                    myDb.close();//若数据库以打开，则先将其关闭
                }
                String path=myDb.getPath();//获得数据库文件名（含路径）
                File db=new File(path);
                SQLiteDatabase.deleteDatabase(db);//删除数据库
                tvPath.setText("数据库已删除");//显示数据库文件及其路径
            }
        });

        Button btAdd=(Button)findViewById(R.id.btAdd);
        btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb==null){
                    return;//在没有创建数据库时，不执行添加数据操作
                }
                ContentValues cv=new ContentValues();
                EditText etID= (EditText) findViewById(R.id.etName);
                EditText etPwd= (EditText) findViewById(R.id.etPassword);
                String name=etID.getText().toString();
                String password=etPwd.getText().toString();
                cv.put("userid",name);
                cv.put("password",password);
                myDb.insert("users",null,cv);//将数据添加到数据表
                //myDb.execSQL("insert into users(userid,password) values(?,?)",
                //        new String[]{name,password});
                Toast.makeText(MainActivity.this,"成功添加记录",Toast.LENGTH_LONG).show();
                refreshList();
            }
        });
        Button btUpgradeDb=(Button)findViewById(R.id.btUpgradeDb);
        btUpgradeDb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sqLiteHelper=new MySQLiteHelper(MainActivity.this,"usersdb.db",null,2);
                myDb=sqLiteHelper.getWritableDatabase();//完成数据库升级
            }
        });
        Button btDelete=(Button)findViewById(R.id.btDelete);
        btDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb==null){
                    return;//在没有创建数据库时，不执行后继操作
                }
                EditText etID= (EditText) findViewById(R.id.etName);
                String name=etID.getText().toString();
                myDb.delete("users","userid=?",new String[]{name});
                //myDb.execSQL("delete from users where userid=?",new String[]{name});
                Toast.makeText(MainActivity.this,"成功删除记录",Toast.LENGTH_LONG).show();
                refreshList();
            }
        });
        Button btUpdate=(Button)findViewById(R.id.btUpdate);
        btUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb==null){
                    return;//在没有创建数据库时，不执行后继操作
                }
                EditText etID= (EditText) findViewById(R.id.etName);
                EditText etPwd= (EditText) findViewById(R.id.etPassword);
                String name=etID.getText().toString();
                String password=etPwd.getText().toString();
                ContentValues cv=new ContentValues();
                cv.put("password",password);
                myDb.update("users",cv,"userid=?",new String[]{name});
                //myDb.execSQL("update users set password=? where userid=?",new String[]{password,name});
                Toast.makeText(MainActivity.this,"成功修改记录",Toast.LENGTH_LONG).show();
                refreshList();
            }
        });
        Button btGetAll=(Button)findViewById(R.id.btGetAll);
        btGetAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myDb==null){
                    return;//在没有创建数据库时，不执行后继操作
                }
                Cursor c=myDb.query("users",null,null,null,null,null,null);
                String msg="当前记录如下：\n";
                if(c.moveToFirst()){
                    do{
                        msg=msg+"userid:"+c.getString(c.getColumnIndex("userid"))+
                        "  password="+c.getString(c.getColumnIndex("password"))+"\n";
                    }while(c.moveToNext());
                }
                Toast.makeText(MainActivity.this,msg,Toast.LENGTH_LONG).show();
            }
        });
    }
    private void refreshList(){
        Cursor c=myDb.query("users",new String[]{"id as _id","userid","password"},
                null,null,null,null,null);
        //Cursor c=myDb.rawQuery("select id as _id,userid,password from users",null);
        SimpleCursorAdapter adapter=new SimpleCursorAdapter(MainActivity.this,
                R.layout.recordlist,
                c,new String[]{"userid","password"},
                new int[]{R.id.textID,R.id.textPwd});
        ListView lv= (ListView) findViewById(R.id.lvRecords);
        lv.setAdapter(adapter);
    }
}
