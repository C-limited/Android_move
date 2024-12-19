package com.example.xbg.learnrecyclerview2;

import android.content.Context;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ItemViewHolder> {
    private List<Book> bookList;
    static class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView bookName;
        private ImageView bookPic;
        public ItemViewHolder(View itemView) {
            super(itemView);
            bookName=(TextView) itemView.findViewById(R.id.bookname);
            bookPic=(ImageView) itemView.findViewById(R.id.bookpic);
        }
    }
    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext())
                .inflate(R.layout.book_item,parent,false);
        final ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        itemViewHolder.bookPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index=itemViewHolder.getAdapterPosition();
                Book book=bookList.get(index);
                Toast.makeText(v.getContext(),
                        "你点击了："+book.getName()+" 的图片",Toast.LENGTH_LONG).show();
            }
        });
        itemViewHolder.bookName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int index=itemViewHolder.getAdapterPosition();
                Book book=bookList.get(index);
                Toast.makeText(v.getContext(),
                        "你点击了："+book.getName()+" 的书名",Toast.LENGTH_LONG).show();
            }
        });
        return itemViewHolder;
    }
    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Book book=bookList.get(position);
        holder.bookName.setText(book.getName());
        holder.bookPic.setImageResource(book.getPicId());
    }
    @Override
    public int getItemCount() {
        return bookList.size();
    }
}
