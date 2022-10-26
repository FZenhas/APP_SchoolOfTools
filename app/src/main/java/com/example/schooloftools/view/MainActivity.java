package com.example.schooloftools.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.schooloftools.R;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button bt_class, bt_about;
    TextView tv_dateTime;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_class = findViewById(R.id.bt_class);
        bt_about = findViewById(R.id.bt_about);
        tv_dateTime = findViewById(R.id.tv_dateTime);

        bt_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ClassesActivity.class);
                startActivity(i);
            }
        });

        bt_about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(i);
            }
        });

        Date date = Calendar.getInstance().getTime();
        tv_dateTime.setText(DateFormat.getDateInstance().format(date));

    }
}
