package com.example.xbg.learnuicomponent2;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private int imgno=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> spadapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_array, android.R.layout.simple_spinner_item);
        spadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spadapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                TextView tv1= (TextView) findViewById(R.id.textView);
                tv1.setText(parent.getSelectedItem().toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        SeekBar sb=(SeekBar)findViewById(R.id.seekBar);
        sb.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //拖动滑块时调用
                TextView tv= (TextView) findViewById(R.id.textView2);
                tv.setText("当前拖动条值："+progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //开始拖动滑块时调用
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                //结束拖动滑块时调用
            }
        });

    }
    public void changePic(View view){
        ImageView im= (ImageView) findViewById(R.id.imageView);
        imgno++;
        if(imgno%2==0){
            im.setImageResource(R.drawable.run);
        }else{
            im.setImageResource(R.drawable.munt);
        }

    }
}
