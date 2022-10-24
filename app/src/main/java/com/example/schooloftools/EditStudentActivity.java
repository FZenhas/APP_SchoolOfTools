package com.example.schooloftools;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schooloftools.adapter.StudentsListAdapter;
import com.example.schooloftools.database.DBHelperStudents;
import com.example.schooloftools.model.Student;
import com.example.schooloftools.view.StudentsActivity;

public class EditStudentActivity extends AppCompatActivity {

    DBHelperStudents db = new DBHelperStudents(this);
    Intent i;
    Bundle extras;
    Button bt_update_student;
    EditText et_edit_name, et_edit_address, et_edit_phone, et_edit_email, et_edit_latitude, et_edit_longitude, et_edit_classId;
    Student student;
    StudentsListAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_student);

        bt_update_student = findViewById(R.id.bt_update_student);
        et_edit_name = findViewById(R.id.et_edit_name);
        et_edit_address = findViewById(R.id.et_edit_address);
        et_edit_phone = findViewById(R.id.et_edit_phone);
        et_edit_email = findViewById(R.id.et_edit_email);
        et_edit_latitude = findViewById(R.id.et_edit_latitude);
        et_edit_longitude = findViewById(R.id.et_edit_longitude);
        et_edit_classId = findViewById(R.id.et_edit_classId);

        i = getIntent();
        extras = i.getExtras();

        student = db.SelectStudentbyID(extras.getInt("id"));
        et_edit_name.setText(student.getName());
        et_edit_address.setText(student.getAddress());
        et_edit_phone.setText(String.valueOf(student.getPhone()));
        et_edit_email.setText(student.getEmail());
        et_edit_latitude.setText(String.valueOf(student.getLatitude()));
        et_edit_longitude.setText(String.valueOf(student.getLongitude()));
        et_edit_classId.setText(String.valueOf(student.getClass_id()));

        bt_update_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.Update(student.getId(), et_edit_name.getText().toString(), et_edit_address.getText().toString(), Integer.parseInt(et_edit_phone.getText().toString()), et_edit_email.getText().toString(), Double.parseDouble(et_edit_latitude.getText().toString()), Double.parseDouble(et_edit_longitude.getText().toString()), Integer.parseInt(et_edit_classId.getText().toString()));

                Toast.makeText(EditStudentActivity.this, "Aluno atualizado", Toast.LENGTH_SHORT).show();
                i = new Intent(EditStudentActivity.this, StudentsActivity.class);
                startActivity(i);

            }
        });
    }
}