package com.example.schooloftools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.schooloftools.view.ClassesActivity;
import com.example.schooloftools.view.StudentsActivity;

public class MainActivity extends AppCompatActivity {

    Button bt_class, bt_about;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bt_class = findViewById(R.id.bt_class);
        bt_about = findViewById(R.id.bt_about);

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
    }
}
