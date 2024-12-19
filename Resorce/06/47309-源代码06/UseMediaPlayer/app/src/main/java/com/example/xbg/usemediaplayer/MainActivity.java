package com.example.xbg.usemediaplayer;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private  MediaPlayer mediaPlayer=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mediaPlayer=new MediaPlayer();//创建MediaPlayer对象
        //检查应用是否已经获得授权
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            //如果没有权限，动态申请授权
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else{
            initMediaPlayer();//初始化MediaPlayer
        }
        Button btPlayMp3= (Button) findViewById(R.id.btPlayMp3);
        btPlayMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!mediaPlayer.isPlaying()){
                    mediaPlayer.start();//开始播放
                }
            }
        });
        Button btPauseMp3= (Button) findViewById(R.id.btPauseMp3);
        btPauseMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();//暂停播放
                }
            }
        });
        Button btStopMp3= (Button) findViewById(R.id.btStopMp3);
        btStopMp3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    private void initMediaPlayer(){
        try {//初始化MediaPlayer
            File file=new File(Environment.getExternalStorageDirectory()+"/music","honor.mp3");
            mediaPlayer.setDataSource(file.getPath());//设置音频路径
            mediaPlayer.prepare();//加载音频，完成准备
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==PackageManager.PERMISSION_GRANTED){
                initMediaPlayer();//初始化MediaPlayer
            }else{
                Toast.makeText(this,"未获得SD卡访问权限",Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    @Override
    protected void onDestroy() {
        //关闭应用时释放MediaPlayer对象占用的资源
        if(mediaPlayer!=null){
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer=null;
        }
        super.onDestroy();
    }
}
