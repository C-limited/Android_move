package com.example.xbg.usemediarecorder;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer=null;
    private MediaRecorder mediaRecorder=null;
    private  String mFileName = null;
    private static final String LOG_TAG = "UseMediaRecorder";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFileName = getExternalCacheDir().getAbsolutePath();
        mFileName += "/audiorecord.3gp";
        //检查应用是否已经获得授权
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
                != PackageManager.PERMISSION_GRANTED){
            //如果没有权限，动态申请授权
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECORD_AUDIO},1);
        }else {
            initMediaRecorder();
        }
        Button btStartRecord= (Button) findViewById(R.id.btStartRecord);
        btStartRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//开始录音
                try {
                    mediaRecorder.prepare();//准备MediaRecorder
                } catch (IOException e) {
                    Log.e(LOG_TAG, "准备MediaRecorder出错啦！");
                }
                mediaRecorder.start();//开始采集音频
            }
        });
        Button btStopRecord= (Button) findViewById(R.id.btStopRecord);
        btStopRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止录音
                mediaRecorder.stop();//停止MediaRecorder
                mediaRecorder.release();//释放MediaRecorder所占资源
                mediaRecorder = null;
            }
        });
        Button btStartPlay= (Button) findViewById(R.id.btPlay);
        btStartPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//开始播放
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(mFileName);//设置要播放的音频文件
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                } catch (IOException e) {
                    Log.e(LOG_TAG, "MediaPlayer方法prepare()执行失败！");
                }
            }
        });
        Button btStop= (Button) findViewById(R.id.btStop);
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                mediaPlayer.release();
                mediaPlayer = null;
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(grantResults.length>0 && grantResults[0]==
                    PackageManager.PERMISSION_GRANTED){
                initMediaRecorder();//初始化
            }else{
                Toast.makeText(this,"未获得麦克风访问权限", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }
    private void initMediaRecorder() {//初始化MediaRecorder
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);//设置音频来源，使用麦克风
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);//设置音频输出格式
        mediaRecorder.setOutputFile(mFileName);//设置音频输出文件
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);//设置音频编码方式
    }
    @Override
    protected void onDestroy() {//应用停止时，释放资源
        if (mediaRecorder != null) {
            mediaRecorder.release();
            mediaRecorder = null;
        }
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        super.onDestroy();
    }
}
