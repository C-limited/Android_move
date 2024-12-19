package com.example.xbg.usepublicpath;

import android.content.Context;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.os.storage.StorageVolume;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(!isWritable()){
            Toast.makeText(this,"SD卡不可用",Toast.LENGTH_SHORT).show();
            return;
        }
        File sdcard=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS);
        File mf=new File(sdcard,"myfile.txt");
        try {
            mf.createNewFile();
            Toast.makeText(this,"成功创建文件",Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this,e.getMessage(),Toast.LENGTH_SHORT).show();
        }
    }
    private boolean isWritable(){//检测存储卡是否可写
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }
}
