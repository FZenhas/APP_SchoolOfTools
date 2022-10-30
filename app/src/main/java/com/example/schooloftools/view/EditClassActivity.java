package com.example.schooloftools.view;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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

import java.util.Locale;

public class EditClassActivity extends AppCompatActivity {

    DBHelper db = new DBHelper(this);
    Intent i;
    Bundle extras;
    Button bt_update_class;
    EditText et_edit_year, et_edit_designation;
    Turma turma;
    TurmasListAdapter adapter;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_class);

        bt_update_class = findViewById(R.id.bt_update_class);
        et_edit_year = findViewById(R.id.et_edit_year);
        et_edit_designation = findViewById(R.id.et_edit_designation);

        i = getIntent();
        extras = i.getExtras();
        turma = db.SelectTurmaById(extras.getInt("id"));
        et_edit_year.setText(String.valueOf(turma.getYear()));
        et_edit_designation.setText(turma.getDesignation());

        bt_update_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (TextUtils.isEmpty(et_edit_designation.getText())) {
                    Toast.makeText(EditClassActivity.this, "Dados em falta", Toast.LENGTH_SHORT).show();
                    et_edit_designation.setError("Introduza a designação.");

                } else if (TextUtils.isEmpty(et_edit_year.getText())) {
                    Toast.makeText(EditClassActivity.this, "Dados em falta", Toast.LENGTH_SHORT).show();
                    et_edit_year.setError("Introduza o ano.");
                } else {
                    db.Update(turma.getId(), Integer.parseInt(et_edit_year.getText().toString()), et_edit_designation.getText().toString().toUpperCase(Locale.ROOT));
                    Toast.makeText(EditClassActivity.this, "Turma atualizada", Toast.LENGTH_SHORT).show();
                    i = new Intent(EditClassActivity.this, ClassesActivity.class);
                    startActivity(i);
                    finish();
                }
            }
        });
    }
}