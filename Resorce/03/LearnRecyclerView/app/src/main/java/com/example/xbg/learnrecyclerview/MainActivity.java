package com.example.xbg.learnrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private String[] bookMames;//用于引用字符串资源文件中的字符串数组
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //获得字符串资源文件中的字符串数组
        bookMames = getResources().getStringArray(R.array.LearnListViewData);
        //获得布局中的RecyclerView控件
        RecyclerView recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        //设置RecyclerView控件布局管理器类型
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        //定义RecyclerView适配器
        recyclerView.setAdapter(new RecyclerView.Adapter() {
            //定义用于处理每个列表项的ViewHolder，每个ViewHolder包含了列表项的各个控件
            class ItemViewHolder extends RecyclerView.ViewHolder{
                private TextView itemTextView;
                public ItemViewHolder(TextView itemView) {
                    super(itemView);
                    itemTextView=itemView;
                }
                public TextView getItemTextView() {
                    return itemTextView;
                }
            }
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return new ItemViewHolder(new TextView(parent.getContext()));//生成列表项ViewHolder
            }
            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                //为列表项设置数据
                ItemViewHolder itemViewHolder=(ItemViewHolder)holder;
                itemViewHolder.getItemTextView().setText(position+":"+bookMames[position%3]);
            }
            @Override
            public int getItemCount() {
                return 50;//指定RecyclerView列表项的数目
            }
        });
    }
}
