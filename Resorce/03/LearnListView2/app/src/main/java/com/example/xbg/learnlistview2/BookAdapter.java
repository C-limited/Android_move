package com.example.xbg.learnlistview2;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;
public class BookAdapter extends ArrayAdapter<Book> {
    public int resId;
    public BookAdapter(Context context, int resource, List<Book> objects) {
        super(context, resource, objects);
        resId=resource;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Book book=getItem(position);//获得当前列表项Book对象
        View view= LayoutInflater.from(getContext()).inflate(resId,parent,false);
        TextView bookname=(TextView)view.findViewById(R.id.bookname);
        bookname.setText(book.getName());
        ImageView bookpic=(ImageView)view.findViewById(R.id.bookpic);
        bookpic.setImageResource(book.getPicId());
        return view;
    }
}