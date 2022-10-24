package com.example.schooloftools.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schooloftools.AddClassActivity;
import com.example.schooloftools.EditClassActivity;

import com.example.schooloftools.R;
import com.example.schooloftools.SelectListenerTurmas;
import com.example.schooloftools.adapter.TurmasListAdapter;
import com.example.schooloftools.database.DBHelper;
import com.example.schooloftools.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class ClassesActivity extends AppCompatActivity implements SelectListenerTurmas {

    private ClassesActivity.ViewHolder mViewHolder = new ClassesActivity.ViewHolder();
    DBHelper db = new DBHelper(this);
    private int posicao = -1;
    List<Turma> listTurmas;
    TurmasListAdapter adapter;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classes);

        mViewHolder.rv_classes = findViewById(R.id.rv_classes);

        mViewHolder.bt_addClass = findViewById(R.id.bt_addClass);

        mViewHolder.bt_delete_class = findViewById(R.id.bt_delete_class);

        mViewHolder.tv_classes = findViewById(R.id.tv_classes);

        mViewHolder.bt_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClassesActivity.this, AddClassActivity.class);
                startActivity(i);
            }
        });

        //VISUALIZAR TURMA
        listTurmas = db.SelectAllList();

        adapter = new TurmasListAdapter(this, listTurmas, this);
        mViewHolder.rv_classes.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mViewHolder.rv_classes.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onItemClicked(Turma turma, String action) {
        if (action == "visualizar") {
            Intent i = new Intent(ClassesActivity.this, StudentsActivity.class);
            i.putExtra("id", turma.getId());
            startActivity(i);
        }
        if (action == "remover") {
            if (turma.getId() > listTurmas.size() - 1) {
                posicao = listTurmas.size();
            } else {
                posicao = turma.getId();
            }
            db.Delete(turma.getId());

            listTurmas.remove(posicao - 1);
            adapter.notifyDataSetChanged();

            Toast.makeText(ClassesActivity.this, "Turma eliminada", Toast.LENGTH_SHORT).show();
        }
        if (action == "editar") {
            Intent i = new Intent(ClassesActivity.this, EditClassActivity.class);
            i.putExtra("id", turma.getId());
            startActivity(i);
        }
    }

    public static class ViewHolder {

        RecyclerView rv_classes;
        Button bt_addClass, bt_delete_class;
        Intent i;
        TextView tv_classes;
    }
}