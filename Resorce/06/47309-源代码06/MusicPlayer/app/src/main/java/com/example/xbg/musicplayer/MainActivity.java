package com.example.xbg.musicplayer;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mediaPlayer=null;
    private static int SELECT_MUSIC=100;
    private TextView tvMusicName;
    private SeekBar sbSeek;
    private TextView tvLen;
    private Uri musicUri;
    private List<Song> listSong;
    private Timer mTimer;
    private TimerTask mTimerTask;
    private boolean seekBarChange=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //检查应用是否已经获得授权
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED){
            //如果没有权限，动态申请授权
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }
        initAudioList();//初始化歌曲列表
        tvMusicName= (TextView) findViewById(R.id.tvName);
        tvLen= (TextView) findViewById(R.id.tvLen);
        sbSeek = (SeekBar) findViewById(R.id.sbSeek);
        sbSeek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {}
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                seekBarChange=true;
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarChange=false;
                mediaPlayer.seekTo(seekBar.getProgress());//拖动SeekBar进度时，改变歌曲播放进度
            }
        });

        Button btPlay= (Button) findViewById(R.id.btPlay);
        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null)  mediaPlayer.start();//开始播放
            }
        });
        Button btPause= (Button) findViewById(R.id.btPause);
        btPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.pause();//暂停播放
                }
            }
        });
        Button btStop= (Button) findViewById(R.id.btStop);
        btStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//停止播放
                if(mediaPlayer!=null && mediaPlayer.isPlaying()){
                    mediaPlayer.stop();
                    try {
                        mediaPlayer.prepare();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        Button btRefresh= (Button) findViewById(R.id.btRefresh);
        btRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//刷新歌曲列表
                initAudioList();
            }
        });

        ListView lsAudios= (ListView) findViewById(R.id.lsAudios);
        lsAudios.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Song song=listSong.get(position);
                initMediaPlayer(song);//在歌曲列表中点击时，播放该歌曲
            }
        });
    }
    private void initAudioList() {//从媒体库获取MP3信息，填充到歌曲列表中
        //刷新媒体库
        MediaScannerConnection.scanFile(this, new String[] { Environment
                .getExternalStorageDirectory().getAbsolutePath() }, null, null);
        //查询媒体库，用获得的乐曲信息填充ListView
        listSong=new ArrayList<Song>();
        //从媒体库查询MP3类型的乐曲信息
        Cursor musics = getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                new String[] { MediaStore.Audio.Media._ID,
                        MediaStore.Audio.Media.DISPLAY_NAME,
                        MediaStore.Audio.Media.TITLE,
                        MediaStore.Audio.Media.DURATION,
                        MediaStore.Audio.Media.ARTIST,
                        MediaStore.Audio.Media.ALBUM,
                        MediaStore.Audio.Media.SIZE,
                        MediaStore.Audio.Media.DATA },
                MediaStore.Audio.Media.MIME_TYPE + "=?",
                new String[] { "audio/mpeg"},null);
        String fileName,title,singer,album,year,size,filePath="";
        int duration,m,s;
        Song song;
        if(musics.moveToFirst())
            do{
                fileName=musics.getString(1);
                title=musics.getString(2);
                duration=musics.getInt(3);
                singer=musics.getString(4);
                album=musics.getString(5);
                size=(musics.getString(6)==null)?"未知":musics.getInt(6)/1024/1024+"MB";
                if(musics.getString(7)!=null)  filePath=musics.getString(7);
                song=new Song(fileName,title,duration,singer,album,size,filePath);
                listSong.add(song);
            }while(musics.moveToNext());
        musics.close();
        SongAdapter adapter=new SongAdapter(MainActivity.this,R.layout.songitem,listSong);
        ListView listView=(ListView)findViewById(R.id.lsAudios);
        listView.setAdapter(adapter);
    }

    private void initMediaPlayer(Song song){
        try {//初始化MediaPlayer
            if(mediaPlayer==null)
                mediaPlayer=new MediaPlayer();//创建MediaPlayer对象
            mediaPlayer.reset();
            mediaPlayer.setDataSource(song.getFilePath());//设置音频路径
            mediaPlayer.prepare();//加载音频，完成准备
            mediaPlayer.start();
            int m=song.getDuration()/60000;
            int s=(song.getDuration()-m*60000)/1000;
            tvLen.setText("时长："+ m+"分"+s+"秒");
            tvMusicName.setText(song.getFileName());
            sbSeek.setMax(song.getDuration());
            //使用定时器记录播放进度，并实时更新SeekBar进度
            mTimer = new Timer();
            mTimerTask = new TimerTask() {
                @Override
                public void run() {
                    if(seekBarChange) return;
                    sbSeek.setProgress(mediaPlayer.getCurrentPosition());

                }
            };
            mTimer.schedule(mTimerTask, 0, 10);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        @Override
    public void onRequestPermissionsResult(int requestCode,
                               @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode==1){
            if(!(grantResults.length>0 && grantResults[0]==
                    PackageManager.PERMISSION_GRANTED)){
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
