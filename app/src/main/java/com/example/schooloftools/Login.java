package com.example.schooloftools;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText et_email, et_password;
    Button bt_login;

    String email="", password="";
    SharedPreferences sp;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_email = findViewById(R.id.et_email);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);

        sp = getSharedPreferences(getString(R.string.user), Context.MODE_PRIVATE);
        email=sp.getString(getString(R.string.user), "");
        et_email.setText(email);

        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = et_email.getText().toString();
                String password = et_password.getText().toString();

                email = et_email.getText().toString();
                password = et_password.getText().toString();

                editor = sp.edit();
                editor.putString(getString(R.string.user), email);
                editor.apply();

                if((email.trim().equals("teacher@email.com") && password.equals("pass")) || (email.trim().equals("teacher2@email.com") && password.equals("pass2")) || (email.trim().equals("teacher3@email.com") && password.equals("pass3"))) { // colocar os 3 logins possiveis
                    Intent i = new Intent(Login.this, MainActivity.class);
                    Toast.makeText(Login.this, "Login válido. Obrigada!", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }else{
                    Toast.makeText(Login.this, "Login inválido. Tente novamente.", Toast.LENGTH_SHORT).show();
                }

                et_email.setText("");
                et_password.setText("");
            }
        });
    }
}
