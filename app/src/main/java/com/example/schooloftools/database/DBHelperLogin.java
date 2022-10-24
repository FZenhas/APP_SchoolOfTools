package com.example.schooloftools.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.schooloftools.model.Student;
import com.example.schooloftools.model.User;

public class DBHelperLogin extends SQLiteOpenHelper {

    private static String nameDB = "schooloftools.db";
    private static int version = 1;

    private String[] sql = {
            "CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT);",
            "INSERT INTO users VALUES ('teacher@email.com', 'pass'), ('teacher2@email.com','pass2'), ('teacher3@email.com','pass3');",
            "CREATE TABLE classes (id INTEGER PRIMARY KEY AUTOINCREMENT, year INTEGER, designation TEXT);",
            "INSERT INTO classes (year, designation) VALUES ('10', 'turma A'), ('10','turma B'), ('11','turma A'),('11','turma B');",
            "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, phone INTEGER, email TEXT, latitude real, longitude real, class_id integer, FOREIGN KEY('adid') REFERENCES classes('id'));",
            "INSERT INTO students (name, address, phone, email, latitude, longitude, class_id) VALUES ('Rodrigo Freitas','Rua do Prado, Porto','919999999','rf@aluno.pt','41.154672','-8.623624','2'), ('José Sá','Rua de Baixo, Rio Tinto','934444444','js@aluno.pt','41.152863','-8.672752','1'),('António Lopes','Rua da Serra, Porto','910000000','al@aluno.pt','41.163332','-8.642176','3'),('Andreia Tavares','Rua de Baixo, Ermesinde','968989899','at@aluno.pt','41.148339','-8.590472','4');"

    };

    public DBHelperLogin(@Nullable Context context) {
        super(context, nameDB, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        for (int i = 0; i < sql.length; i++) {
            sqLiteDatabase.execSQL(sql[i]);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public User LoginCredencials(String email, String password) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM users WHERE email=? and password=?", new String[]{String.valueOf(email), String.valueOf(password)});
        User user = new User();
        c.moveToFirst();
        if (c != null && c.moveToFirst()) {
            do {
                int posEmail = c.getColumnIndex("email");
                int posPassword = c.getColumnIndex("password");

                email = c.getString(posEmail);
                password = c.getString(posPassword);

                user = new User(email, password);
            } while (c.moveToNext());
            c.close();
        }
        return user;
    }
}
