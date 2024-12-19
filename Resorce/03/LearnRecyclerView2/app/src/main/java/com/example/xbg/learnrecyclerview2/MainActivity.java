package com.example.xbg.learnrecyclerview2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private String[] bookMames;//用于引用字符串资源文件中的字符串数组
    private List<Book> bookList=new ArrayList<Book>();//用于保存图书信息
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得字符串资源文件中的字符串数组
        bookMames = getResources().getStringArray(R.array.LearnListViewData);
        //初始化图书信息,这里只是示例，所以使用了重复的数据
        for (int i=0;i<20;i++){
            Book book1=new Book(R.drawable.pic1,bookMames[0]);
            bookList.add(book1);
            Book book2=new Book(R.drawable.pic2,bookMames[1]);
            bookList.add(book2);
            Book book3=new Book(R.drawable.pic3,bookMames[2]);
            bookList.add(book3);
        }
        //获得布局中的RecyclerView控件
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        //设置RecyclerView控件布局管理器类型
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        //使用瀑布流布局
        StaggeredGridLayoutManager layoutManager=
                new StaggeredGridLayoutManager(3,StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        //定义RecyclerView适配器
        recyclerView.setAdapter(new BookAdapter(bookList) );
    }
}
