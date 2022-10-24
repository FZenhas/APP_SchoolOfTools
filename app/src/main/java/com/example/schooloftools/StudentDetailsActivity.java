package com.example.schooloftools;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.schooloftools.adapter.StudentsListAdapter;
import com.example.schooloftools.database.DBHelperStudents;
import com.example.schooloftools.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {

    DBHelperStudents db = new DBHelperStudents(this);
    Intent i;
    Bundle extras;
    TextView tv_view_name, tv_view_address, tv_view_phone, tv_view_email, tv_view_latitude, tv_view_longitude, tv_view_classId;
    Student student;
    Button bt_view_location;
    StudentsListAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        tv_view_name = findViewById(R.id.tv_view_name);
        tv_view_address = findViewById(R.id.tv_view_address);
        tv_view_phone = findViewById(R.id.tv_view_phone);
        tv_view_email = findViewById(R.id.tv_view_email);
        tv_view_latitude = findViewById(R.id.tv_view_latitude);
        tv_view_longitude = findViewById(R.id.tv_view_longitude);
        tv_view_classId = findViewById(R.id.tv_view_classId);
        bt_view_location = findViewById(R.id.bt_view_location);

        i = getIntent();
        extras = i.getExtras();

        student = db.SelectStudentbyID(extras.getInt("id"));
        tv_view_name.setText("Nome: " + student.getName());
        tv_view_address.setText("Morada: " + student.getAddress());
        tv_view_phone.setText("Telefone: " + String.valueOf(student.getPhone()));
        tv_view_email.setText("Email: " + student.getEmail());
        tv_view_latitude.setText("Latitude: " + String.valueOf(student.getLatitude()));
        tv_view_longitude.setText("Longitude: " + String.valueOf(student.getLongitude()));
        tv_view_classId.setText("ID turma: " + String.valueOf(student.getClass_id()));

        bt_view_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(StudentDetailsActivity.this, MapsActivity.class);
                i.putExtras(extras);
                startActivity(i);
            }
        });
    }
}