package com.saviour.listviewdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<String> table;
    private ArrayAdapter<String> arrayAdapter;
    private SeekBar seekBar;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        context = this;
        table = new ArrayList<String>();
        seekBar = findViewById(R.id.seekBar);
        arrayAdapter = new ArrayAdapter<String>(context,android.R.layout.simple_list_item_1,table);
        listView = findViewById(R.id.listView);

        seekBar.setMax(20);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            seekBar.setMin(1);
        }else {
            seekBar.setProgress(1);
        }

        getTimeTableOf(1);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if(progress == 0){
                    seekBar.setProgress(1);
                }
                if(table.size()>0){
                    table.clear();
                }
                getTimeTableOf(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }

    private void getTimeTableOf(int progress) {
        for(int i = 1 ; i <= 10 ; i++){
            table.add(progress+" * "+i+" = "+i*progress);
        }
        listView.setAdapter(arrayAdapter);
    }
}