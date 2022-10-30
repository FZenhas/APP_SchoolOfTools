package com.example.schooloftools.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberUtils;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schooloftools.R;
import com.example.schooloftools.adapter.StudentsListAdapter;
import com.example.schooloftools.database.DBHelperStudents;
import com.example.schooloftools.model.Student;

import java.util.ArrayList;
import java.util.List;

public class AddStudentActivity extends AppCompatActivity {

    private AddStudentActivity.ViewHolder mViewHolder = new AddStudentActivity.ViewHolder();
    DBHelperStudents db = new DBHelperStudents(this);
    List<Student> listStudents;
    StudentsListAdapter adapter;
    Intent i;
    Bundle extras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_student);

        i = getIntent();
        extras = i.getExtras();

        listStudents = new ArrayList<>();

        mViewHolder.bt_save_student = findViewById(R.id.bt_save_student);
        mViewHolder.et_name = findViewById(R.id.et_name);
        mViewHolder.et_address = findViewById(R.id.et_address);
        mViewHolder.et_email = findViewById(R.id.et_email);
        mViewHolder.et_phone = findViewById(R.id.et_phone);
        mViewHolder.et_latitude = findViewById(R.id.et_latitude);
        mViewHolder.et_longitude = findViewById(R.id.et_longitude);

        mViewHolder.bt_save_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(mViewHolder.et_name.getText())){
                    Toast.makeText(AddStudentActivity.this, "Dados em falta", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_name.setError("Introduza o nome");
                }else if(TextUtils.isEmpty(mViewHolder.et_address.getText())){
                    Toast.makeText(AddStudentActivity.this, "Dados em falta.", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_address.setError("Introduza a morada.");
                }else if(TextUtils.isEmpty(mViewHolder.et_phone.getText())){
                    Toast.makeText(AddStudentActivity.this, "Dados em falta.", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_phone.setError("Introduza o telefone.");
                }else if(TextUtils.isEmpty(mViewHolder.et_email.getText())) {
                    Toast.makeText(AddStudentActivity.this, "Dados em falta.", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_email.setError("Introduza o email.");
                }else if(TextUtils.isEmpty(mViewHolder.et_latitude.getText())) {
                    Toast.makeText(AddStudentActivity.this, "Dados em falta.", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_latitude.setError("Introduza a latitude.");
                }else if(TextUtils.isEmpty(mViewHolder.et_longitude.getText())) {
                    Toast.makeText(AddStudentActivity.this, "Dados em falta.", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_longitude.setError("Introduza a longitude.");
                }
                else {
                    long res = db.Insert(mViewHolder.et_name.getText().toString(), mViewHolder.et_address.getText().toString(), Integer.parseInt(mViewHolder.et_phone.getText().toString()), mViewHolder.et_email.getText().toString(), Double.parseDouble(mViewHolder.et_latitude.getText().toString()), Double.parseDouble(mViewHolder.et_longitude.getText().toString()), extras.getInt("turma_id"));
                    listStudents.add(new Student(Integer.parseInt(String.valueOf(res)), mViewHolder.et_name.getText().toString(), mViewHolder.et_address.getText().toString(), Integer.parseInt(mViewHolder.et_phone.getText().toString()), mViewHolder.et_email.getText().toString(), Double.parseDouble(mViewHolder.et_latitude.getText().toString()), Double.parseDouble(mViewHolder.et_longitude.getText().toString()), extras.getInt("turma_id")));
                    Toast.makeText(AddStudentActivity.this, "Aluno inserido ", Toast.LENGTH_SHORT).show();
                     i = new Intent(AddStudentActivity.this, StudentsActivity.class);
                    i.putExtra("turma_id", extras.getInt("turma_id"));
                    startActivity(i);
                    finish();
                }
            }
        });


    }

    private static class ViewHolder {
        RecyclerView rv_students;
        Button bt_save_student;
        EditText et_name, et_address, et_phone, et_email, et_latitude, et_longitude, et_classId;
    }
}