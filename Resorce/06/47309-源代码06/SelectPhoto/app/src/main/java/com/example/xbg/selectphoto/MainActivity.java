package com.example.xbg.selectphoto;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
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

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检查应用是否已经获得授权
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            //如果没有权限，动态申请授权
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }
        Button btSelectPhoto= (Button) findViewById(R.id.btSelectPhoto);
        btSelectPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {w
                //使用Intent对象来打开相册
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 1);
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1){
            if(!(grantResults.length>0 && grantResults[0]==
                    PackageManager.PERMISSION_GRANTED)){
                Toast.makeText(this,"未获得访问权限",Toast.LENGTH_LONG).show();
                finish();
            }
        }

    }w
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1){//处理从相册选取相片返回结果
            if(resultCode==RESULT_OK){//若用户正确完成相片选择操作返回，进一步处理选择的相片
                Uri uri = data.getData();
                Log.e("图片URI：", uri.toString());
                ContentResolver cr = this.getContentResolver();
                try {
                    Bitmap bitmap = BitmapFactory.decodeStream(cr.openInputStream(uri));
                    ImageView iv=(ImageView)findViewById(R.id.ivShow);
                    /* 将Bitmap设定到ImageView */
                    iv.setImageBitmap(bitmap);
                } catch (FileNotFoundException e) {
                    Log.e("出错了：", e.getMessage(),e);
                }
            }
        }
    }
}
