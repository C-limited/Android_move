package com.example.pad.usemediaplayer2;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer=null;
    private SurfaceView sv;
    private SurfaceHolder holder;
    private SurfaceHolder.Callback surfaceHolderCallback=new SurfaceHolder.Callback() {
        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            mediaPlayer.setDisplay(holder);//将MediaPlayer关联到SurfaceView
        }
        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        }
        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
        }
    };
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
        sv= (SurfaceView) findViewById(R.id.surfaceView);
        holder=sv.getHolder();
        holder.addCallback(surfaceHolderCallback);
        Button btPlayVideo= (Button) findViewById(R.id.btPlayVideo);
        btPlayVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//开始播放
                mediaPlayer.start();
            }
        });
        Button btPauseVideo= (Button) findViewById(R.id.btPauseVideo);
        btPauseVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//暂停播放
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                }
            }
        });
        Button btStopVideo= (Button) findViewById(R.id.btStopVideo);
        btStopVideo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                mediaPlayer.stop();
                try {
                    mediaPlayer.prepare();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    private void initMediaPlayer(){
        try {//初始化MediaPlayer
            File path = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_MOVIES);
            File file = new File(path, "广播体操.mp4");
            mediaPlayer.setDataSource(file.getPath());//设置视频路径
            mediaPlayer.prepare();//加载视频，完成准备
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
