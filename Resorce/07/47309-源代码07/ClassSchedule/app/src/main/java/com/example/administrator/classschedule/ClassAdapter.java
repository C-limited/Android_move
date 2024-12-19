package com.example.administrator.classschedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ClassAdapter extends ArrayAdapter<MyClass> {
    public int resId;
    public ClassAdapter(Context context, int resource, List<MyClass> objects) {
        super(context, resource, objects);
        resId=resource;
    }
    public View getView(int position, View convertView, ViewGroup parent) {
        MyClass one=getItem(position);//获得当前列表项MyClass对象
        View view= LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView tvNo=(TextView)view.findViewById(R.id.tvNo);
        tvNo.setText(one.getNo());
        TextView tvTime=(TextView)view.findViewById(R.id.tvTime);
        tvTime.setText(one.getTime());
        TextView tvName=(TextView)view.findViewById(R.id.tvName);
        tvName.setText(one.getName());
        return view;
    }

}
