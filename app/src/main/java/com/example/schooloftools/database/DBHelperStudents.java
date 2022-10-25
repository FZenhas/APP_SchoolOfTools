package com.example.schooloftools.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.schooloftools.model.Student;
import com.example.schooloftools.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class DBHelperStudents extends SQLiteOpenHelper {

    private static String nameDB = "schooloftools.db";
    private static int version = 1;

    private String[] sql = {
            "CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT);",
            "INSERT INTO users VALUES ('teacher@email.com', 'pass'), ('teacher2@email.com','pass2'), ('teacher3@email.com','pass3');",
            "CREATE TABLE classes (id INTEGER PRIMARY KEY AUTOINCREMENT, year INTEGER, designation TEXT);",
            "INSERT INTO classes (year, designation) VALUES ('10', 'A'), ('10','B'), ('11','A'),('11','B');",
            "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, phone INTEGER, email TEXT, latitude real, longitude real, class_id integer, FOREIGN KEY('id') REFERENCES classes('id'));",
            "INSERT INTO students (name, address, phone, email, latitude, longitude, class_id) VALUES ('Rodrigo Freitas','Rua do Prado, Porto','919999999','rf@aluno.pt','41.154672','-8.623624','2'), ('José Sá','Rua de Baixo, Rio Tinto','934444444','js@aluno.pt','41.152863','-8.672752','1'),('António Lopes','Rua da Serra, Porto','910000000','al@aluno.pt','41.163332','-8.642176','3'),('Andreia Tavares','Rua de Baixo, Ermesinde','968989899','at@aluno.pt','41.148339','-8.590472','4');"

    };


    public DBHelperStudents(@Nullable Context context) {
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

    public Cursor SelectAll() {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * from students", null);
    }

    public List<Student> SelectAllList(int turma_id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT s.id, s.name, s.address, s.phone, s.email, s.latitude, s.longitude, s.class_id from students s Join classes c on s.class_id = c.id WHERE class_id=?", new String[]{String.valueOf(turma_id)});
        List<Student> listStudents = new ArrayList<>();
        c.moveToFirst();
        if (c != null && c.moveToFirst()) {
            do {
                int posId = c.getColumnIndex("id");
                int posName = c.getColumnIndex("name");
                int posAddress = c.getColumnIndex("address");
                int posPhone = c.getColumnIndex("phone");
                int posEmail = c.getColumnIndex("email");
                int posLatitude = c.getColumnIndex("latitude");
                int posLongitude = c.getColumnIndex("longitude");
                int posClass_id = c.getColumnIndex("class_id");

                int id = c.getInt(posId);
                String name = c.getString(posName);
                String address = c.getString(posAddress);
                int phone = c.getInt(posPhone);
                String email = c.getString(posEmail);
                double latitude = c.getDouble(posLatitude);
                double longitude = c.getDouble(posLongitude);
                int class_id = c.getInt(posClass_id);

                listStudents.add(new Student(id, name, address, phone, email, latitude, longitude, class_id));
            } while (c.moveToNext());
            c.close();
        }
        return listStudents;
    }

    public Cursor SelectByID(int id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM students WHERE id=?", new String[]{String.valueOf(id)});
    }

    public Student SelectStudentbyID(int turma_id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT s.id, s.name, s.address, s.phone, s.email, s.latitude, s.longitude, s.class_id from students s Join classes c on s.class_id = c.id WHERE s.id=?", new String[]{String.valueOf(turma_id)});
        Student student;
        c.moveToFirst();
        do {
            int posId = c.getColumnIndex("id");
            int posName = c.getColumnIndex("name");
            int posAddress = c.getColumnIndex("address");
            int posPhone = c.getColumnIndex("phone");
            int posEmail = c.getColumnIndex("email");
            int posLatitude = c.getColumnIndex("latitude");
            int posLongitude = c.getColumnIndex("longitude");
            int posClass_id = c.getColumnIndex("class_id");

            int id = c.getInt(posId);
            String name = c.getString(posName);
            String address = c.getString(posAddress);
            int phone = c.getInt(posPhone);
            String email = c.getString(posEmail);
            double latitude = c.getDouble(posLatitude);
            double longitude = c.getDouble(posLongitude);
            int class_id = c.getInt(posClass_id);

            student = new Student(id, name, address, phone, email, latitude, longitude, class_id);
        } while (c.moveToNext());

        return student;

    }

    public long Insert(String name, String address, int phone, String email, double latitude, double longitude, int class_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("address", address);
        cv.put("phone", phone);
        cv.put("email", email);
        cv.put("latitude", latitude);
        cv.put("longitude", longitude);
        cv.put("class_id", class_id);
        return db.insert("students", "", cv);
    }

    public long Update(int id, String name, String address, int phone, String email, double latitude, double longitude, int class_id) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("address", address);
        cv.put("phone", phone);
        cv.put("email", email);
        cv.put("latitude", latitude);
        cv.put("longitude", longitude);
        cv.put("class_id", class_id);
        return db.update("students", cv, "id=?", new String[]{String.valueOf(id)});
    }

    public long Delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("students", "id=?", new String[]{String.valueOf(id)});
    }
}


