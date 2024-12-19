package com.example.xbg.learnuicomponent;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button bt2=(Button) findViewById(R.id.button2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv1= (TextView) findViewById(R.id.textView);
                tv1.setText("单击按钮Button2");
            }
        });
        AutoCompleteTextView act=(AutoCompleteTextView)findViewById(R.id.autoCompleteTextView);
        String[] selects = getResources().getStringArray(R.array.select_array);
        ArrayAdapter<String> adapter =new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, selects);
        act.setAdapter(adapter);
        ToggleButton toggle = (ToggleButton) findViewById(R.id.toggleButton);
        toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                LinearLayout layout=(LinearLayout)findViewById(R.id.activity_main);
                if (isChecked) {
                    layout.setBackgroundResource(R.drawable.back2);
                } else {
                    layout.setBackgroundResource(0);
                }
            }
        });
    }

    public void ClickButton1(View view){
        TextView tv1= (TextView) findViewById(R.id.textView);
        tv1.setText("单击按钮Button1");
    }
    private boolean checked1;
    public void ClickCheckBox1(View view){
        checked1 = ((CheckBox) view).isChecked();
        ChangeTextViewStyle();
    }
    private boolean checked2;
    public void ClickCheckBox2(View view){
        checked2 = ((CheckBox) view).isChecked();
        ChangeTextViewStyle();
    }
    public void ChangeTextViewStyle(){
        TextView tv1= (TextView) findViewById(R.id.textView);
        Typeface tf= tv1.getTypeface();
        int style=0;
        if(checked1){
            style=1;
            if(checked2){style=3;}
        }
        else
            if(checked2){style=2;}
        tv1.setTypeface(tf,style);
    }
    public void  ClickRadio(View view){
        TextView tv1= (TextView) findViewById(R.id.textView);

        switch(view.getId()) {
            case R.id.radioButton1:
                tv1.setTextColor(Color.rgb(0,0,255));
                break;
            case R.id.radioButton2:
                tv1.setTextColor(Color.rgb(255,0,0));
                break;
            case R.id.radioButton3:
                tv1.setTextColor(Color.rgb(0,255,0));
                break;
        }
    }
    public void ClickToggleButton(View view){
        TextView tv1= (TextView) findViewById(R.id.textView);
        tv1.setText("ClickToggleButton");


    }
}
