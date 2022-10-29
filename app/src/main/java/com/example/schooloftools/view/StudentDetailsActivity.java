package com.example.schooloftools.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schooloftools.R;
import com.example.schooloftools.adapter.StudentsListAdapter;
import com.example.schooloftools.database.DBHelperStudents;
import com.example.schooloftools.model.Student;

public class StudentDetailsActivity extends AppCompatActivity {

    DBHelperStudents db = new DBHelperStudents(this);
    Intent i;
    Bundle extras;
    TextView tv_view_name, tv_view_address, tv_view_phone, tv_view_email;
    Student student;
    ImageButton ibt_call_student, ibt_email_student, ibt_view_location;
    StudentsListAdapter adapter;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        tv_view_name = findViewById(R.id.tv_view_name);
        tv_view_address = findViewById(R.id.tv_view_address);
        tv_view_phone = findViewById(R.id.tv_view_phone);
        tv_view_email = findViewById(R.id.tv_view_email);
        ibt_view_location = findViewById(R.id.ibt_view_location);
        ibt_call_student = findViewById(R.id.ibt_call_student);
        ibt_email_student = findViewById(R.id.ibt_email_student);

        i = getIntent();
        extras = i.getExtras();

        student = db.SelectStudentbyID(extras.getInt("id"));
        tv_view_name.setText(student.getName());
        tv_view_address.setText(student.getAddress());
        tv_view_phone.setText(String.valueOf(student.getPhone()));
        tv_view_email.setText(student.getEmail());

        ibt_view_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                i = new Intent(StudentDetailsActivity.this, MapsActivity.class);
                i.putExtra("id", student.getId());
                startActivity(i);
            }
        });

        ibt_call_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = String.valueOf(student.getPhone());
                Uri call = Uri.parse("tel:" + number);
                Intent i = new Intent(Intent.ACTION_DIAL, call);
                startActivity(i);
            }
        });

        ibt_email_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent selectorIntent = new Intent(Intent.ACTION_SENDTO);
                selectorIntent.setData(Uri.parse("mailto:"));

                final Intent emailIntent = new Intent(Intent.ACTION_SEND);
                emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{student.getEmail()});
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Início do ano escolar");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Bem-vindo(a), caro(a) " + student.getName() + "!");
                emailIntent.setSelector(selectorIntent);

                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }

        });

    }
}
