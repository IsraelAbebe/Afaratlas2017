package com.example.icog.mapsdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageSwitcher;
import android.widget.TextView;

public class DataViewActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_view);

        Intent intent = getIntent();
        String message = intent.getStringExtra("Title");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(message);

        TextView textView2 = (TextView) findViewById(R.id.textView2);
        if(message.equals("Zone1")) {
            textView2.setText(getText(R.string.Zone1));
        }else if(message.equals("Zone2")) {
            textView2.setText(getText(R.string.Zone1));
        }else if(message.equals("Zone3")) {
            textView2.setText(getText(R.string.Zone1));
        }else if(message.equals("Zone4")) {
            textView2.setText(getText(R.string.Zone1));
        }else if(message.equals("Zone5")) {
            textView2.setText(getText(R.string.Zone1));
        }
    }
}
