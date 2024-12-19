package com.example.xbg.usesystemcameraapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private File picFile = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检查应用是否已经获得授权
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            //如果没有权限，动态申请授权
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 2);
        }
        //使用StrictMode.VmPolicy.Builder检测应用中的FileUriExposure事件
        StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
        StrictMode.setVmPolicy(builder.build());
        builder.detectFileUriExposure();
        Button btTakePhoto = (Button) findViewById(R.id.btTakePhoto);
        btTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    //创建用于保存所拍照片的文件
                    File sdcard = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                    picFile = new File(sdcard, System.currentTimeMillis() + ".jpg");
                    picFile.createNewFile();
                    Log.e("UseSystemCameraApp", picFile.getName() + "创建成功！");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //使用Intent调用系统摄像头拍照程序
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(picFile));
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            //处理ActivityResult调用返回，将所拍照片显示在ImageView中
            ImageView iv = (ImageView) findViewById(R.id.ivShow);
            iv.setImageURI(Uri.fromFile(picFile));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1) {
            if (!(grantResults.length > 0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED)) {
                Toast.makeText(this, "未获得SD卡访问权限", Toast.LENGTH_LONG).show();
                finish();
            }


        }
    }
}
