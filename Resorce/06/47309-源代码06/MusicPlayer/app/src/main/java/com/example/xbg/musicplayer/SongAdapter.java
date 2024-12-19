package com.example.xbg.musicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class SongAdapter extends ArrayAdapter<Song> {
    public int resId;
    public SongAdapter(Context context, int resource, List<Song> objects) {
        super(context, resource, objects);
        resId=resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Song song=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView tvTile= (TextView) view.findViewById(R.id.tvTitle);
        TextView tvSinger= (TextView) view.findViewById(R.id.tvSinger);
        TextView tvAlbum= (TextView) view.findViewById(R.id.tvAlbum);
        TextView tvDuration= (TextView) view.findViewById(R.id.tvDuration);
        TextView tvSize= (TextView) view.findViewById(R.id.tvSize);
        tvTile.setText("歌曲："+song.getTitle());
        tvSinger.setText("歌手："+song.getSinger());
        tvAlbum.setText("专辑："+song.getAlbum());
        int m=song.getDuration()/60000;
        int s=(song.getDuration()-m*60000)/1000;
        tvDuration.setText("时长："+ m+"分"+s+"秒");
        tvSize.setText("大小："+song.getSize());
        return view;
    }
}
