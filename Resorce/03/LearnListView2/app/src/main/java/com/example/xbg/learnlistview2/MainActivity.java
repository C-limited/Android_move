package com.example.xbg.learnlistview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建图书列表
        final List<Book> bookList=new ArrayList<Book>();
        String[] names = getResources().getStringArray(R.array.LearnListViewData);
        Book book1=new Book(R.drawable.pic1,names[0]);
        bookList.add(book1);
        Book book2=new Book(R.drawable.pic2,names[1]);
        bookList.add(book2);
        Book book3=new Book(R.drawable.pic3,names[2]);
        bookList.add(book3);
        BookAdapter adapter=new BookAdapter(MainActivity.this,R.layout.book_item,bookList);
        ListView listView=(ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Book book=bookList.get(position);
                Toast.makeText(MainActivity.this,book.getName(),Toast.LENGTH_LONG).show();
            }
        });
    }
}
