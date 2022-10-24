package com.example.schooloftools.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.schooloftools.AddClassActivity;
import com.example.schooloftools.AddStudentActivity;
import com.example.schooloftools.EditClassActivity;
import com.example.schooloftools.EditStudentActivity;
import com.example.schooloftools.R;
import com.example.schooloftools.SelectListenerStudents;
import com.example.schooloftools.StudentDetailsActivity;
import com.example.schooloftools.adapter.StudentsListAdapter;

import com.example.schooloftools.database.DBHelperStudents;
import com.example.schooloftools.model.Student;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class StudentsActivity extends AppCompatActivity implements SelectListenerStudents {

    private StudentsActivity.ViewHolder mViewHolder = new StudentsActivity.ViewHolder();
    DBHelperStudents db = new DBHelperStudents(this);
    private int posicao = -1;
    List<Student> listStudents;
    StudentsListAdapter adapter;
    Intent i;
    Bundle extras;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_students);

        i = getIntent();
        extras = i.getExtras();

        mViewHolder.rv_students = findViewById(R.id.rv_students);
        mViewHolder.bt_delete_student = findViewById(R.id.bt_delete_student);
        mViewHolder.bt_addStudent = findViewById(R.id.bt_addStudent);

        // Adicionar turma
        mViewHolder.bt_addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(StudentsActivity.this, AddStudentActivity.class);
                i.putExtra("id", extras.getInt("id"));
                startActivity(i);
            }
        });

        //VISUALIZAR ALUNOS POR TURMA
        listStudents = db.SelectAllList(extras.getInt("id"));


        adapter = new StudentsListAdapter(this, listStudents, this);
        mViewHolder.rv_students.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mViewHolder.rv_students.setLayoutManager(linearLayoutManager);

    }
            @Override
            public void onItemClicked(Student student, String action) {
                if (action == "visualizar") {
                    Intent i = new Intent(StudentsActivity.this, StudentDetailsActivity.class);
                    i.putExtra("id", student.getId());
                    startActivity(i);
                }
                if (action == "remover") {
                    if (student.getId() > listStudents.size() - 1) {
                        posicao = listStudents.size();
                    } else {
                        posicao = student.getId();
                    }
                    db.Delete(student.getId());

                    listStudents.remove(posicao - 1);
                    adapter.notifyDataSetChanged();

                    Toast.makeText(StudentsActivity.this, "Aluno eliminado", Toast.LENGTH_SHORT).show();
                }
                if (action == "editar") {
                    Intent i = new Intent(StudentsActivity.this, EditStudentActivity.class);
                    i.putExtra("id", student.getId());
                    startActivity(i);
                }

    }

        private static class ViewHolder {

            RecyclerView rv_students;
            Intent i;
            Button bt_delete_student, bt_addStudent;

        }
    }