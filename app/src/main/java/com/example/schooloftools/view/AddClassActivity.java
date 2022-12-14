package com.example.schooloftools.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.schooloftools.R;
import com.example.schooloftools.adapter.TurmasListAdapter;
import com.example.schooloftools.database.DBHelper;
import com.example.schooloftools.model.Turma;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AddClassActivity extends AppCompatActivity {

    private AddClassActivity.ViewHolder mViewHolder = new AddClassActivity.ViewHolder();
    DBHelper db = new DBHelper(this);
    List<Turma> listTurmas;
    TurmasListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        listTurmas = new ArrayList<>();

        mViewHolder.bt_addNewClass = findViewById(R.id.bt_addNewClass);
        mViewHolder.et_classYear = findViewById(R.id.et_classYear);
        mViewHolder.et_classDesignation = findViewById(R.id.et_classDesignation);

        mViewHolder.bt_addNewClass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               if (TextUtils.isEmpty(mViewHolder.et_classDesignation.getText())){
                    Toast.makeText(AddClassActivity.this, "Dados em falta", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_classDesignation.setError( "Introduza a designação." );

                }else if (TextUtils.isEmpty(mViewHolder.et_classYear.getText())){
                    Toast.makeText(AddClassActivity.this, "Dados em falta", Toast.LENGTH_SHORT).show();
                    mViewHolder.et_classYear.setError( "Introduza o ano." );
                }
               else {
                   long res = db.Insert(Integer.parseInt(mViewHolder.et_classYear.getText().toString()), mViewHolder.et_classDesignation.getText().toString().toUpperCase(Locale.ROOT));
                    listTurmas.add(new Turma(Integer.parseInt(String.valueOf(res)), Integer.parseInt(mViewHolder.et_classYear.getText().toString()), mViewHolder.et_classDesignation.getText().toString()));
                    Toast.makeText(AddClassActivity.this, "Turma inserida ", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(AddClassActivity.this, ClassesActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }

    private static class ViewHolder {
        RecyclerView rv_classes;
        Button bt_addNewClass;
        EditText et_classYear, et_classDesignation;
    }
}