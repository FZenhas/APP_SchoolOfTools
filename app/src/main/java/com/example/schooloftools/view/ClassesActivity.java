package com.example.schooloftools.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.schooloftools.R;
import com.example.schooloftools.SelectListenerTurmas;
import com.example.schooloftools.adapter.TurmasListAdapter;
import com.example.schooloftools.database.DBHelper;
import com.example.schooloftools.model.Turma;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClassesActivity extends AppCompatActivity implements SelectListenerTurmas {

    private ClassesActivity.ViewHolder mViewHolder = new ClassesActivity.ViewHolder();
    DBHelper db = new DBHelper(this);
    private int posicao = -1;
    List<Turma> listTurmas;
    Intent i;
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
        mViewHolder.ibt_asc_sort = findViewById(R.id.ibt_asc_sort);
        mViewHolder.ibt_desc_sort = findViewById(R.id.ibt_desc_sort);

        mViewHolder.builder = new AlertDialog.Builder(this);

        mViewHolder.bt_addClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ClassesActivity.this, AddClassActivity.class);
                startActivity(i);
            }
        });

        //VISUALIZAR TURMA
        listTurmas = db.SelectAllList();


        Collections.sort(listTurmas, new Comparator<Turma>() {
            @Override
            public int compare(Turma turma, Turma t1) {
                return Integer.valueOf(turma.getYear()).compareTo(t1.getYear());
            }
        });

        mViewHolder.ibt_asc_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Collections.sort(listTurmas, new Comparator<Turma>() {
                    @Override
                    public int compare(Turma turma, Turma t1) {
                        return Integer.valueOf(turma.getYear()).compareTo(t1.getYear());
                    }
                });
                adapter.notifyDataSetChanged();
            }
        });

        mViewHolder.ibt_desc_sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Collections.sort(listTurmas, new Comparator<Turma>() {
                    @Override
                    public int compare(Turma turma, Turma t1) {
                        return Integer.valueOf(t1.getYear()).compareTo(turma.getYear());
                    }
                });
                adapter.notifyDataSetChanged();

            }
        });

        adapter = new TurmasListAdapter(this, listTurmas, this);
        mViewHolder.rv_classes.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mViewHolder.rv_classes.setLayoutManager(linearLayoutManager);
    }


    @Override
    public void onItemClicked(Turma turma, String action) {
        if (action == "visualizar") {
            Intent i = new Intent(ClassesActivity.this, StudentsActivity.class);
            i.putExtra("turma_id", turma.getId());
            startActivity(i);
        }
        if (action == "remover") {

            mViewHolder.builder.setTitle("Eliminar turma").setMessage("Tem a certeza que quer eliminar esta turma?").setCancelable(true).setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            for (i = 0; i < listTurmas.size(); i++) {
                                if (listTurmas.get(i).getId() == turma.getId()) {
                                    posicao = i;
                                    break;
                                }
                            }
                            db.Delete(turma.getId());

                            listTurmas.remove(posicao);
                            adapter.notifyDataSetChanged();
                            Toast.makeText(ClassesActivity.this, "Turma eliminada", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    })
                    .show();
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
        ImageButton ibt_asc_sort, ibt_desc_sort;
        AlertDialog.Builder builder;
    }
}