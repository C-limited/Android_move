package com.example.xbg.usesqlite;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

public class MySQLiteHelper extends SQLiteOpenHelper {
    private static String CREATE_TABLE_USER="create table users("+
            "id integer primary key autoincrement,"+
            "userid text,password text)";
    private static String CREATE_TABLE_TYPE="create table types("+
            "id integer primary key autoincrement,"+
            "type_code,describe text)";
    private Context sContext;
    public MySQLiteHelper(Context context, String name,
                          SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        sContext=context;
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        //执行数据库初始化操作
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_TYPE);
        Toast.makeText(sContext,"成功创建数据表",Toast.LENGTH_LONG).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //执行数据库升级操作
        db.execSQL("drop table if exists users");
        db.execSQL("drop table if exists types");
        onCreate(db);
    }
}
