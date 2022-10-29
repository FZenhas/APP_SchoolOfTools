package com.example.schooloftools.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.schooloftools.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper {

    private static String nameDB = "schooloftools.db";
    private static int version = 1;

    private String[] sql = {
            "CREATE TABLE users (email TEXT PRIMARY KEY, password TEXT);",
            "INSERT INTO users VALUES ('teacher@email.com', 'pass'), ('teacher2@email.com','pass2'), ('teacher3@email.com','pass3');",
            "CREATE TABLE classes (id INTEGER PRIMARY KEY AUTOINCREMENT, year INTEGER, designation TEXT);",
            "INSERT INTO classes (year, designation) VALUES ('10', 'A'), ('10','B'), ('11','A'),('11','B');",
            "CREATE TABLE students (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, address TEXT, phone INTEGER, email TEXT, latitude real, longitude real, class_id integer, FOREIGN KEY('id') REFERENCES classes('id'));",
            "INSERT INTO students (name, address, phone, email, latitude, longitude, class_id) VALUES ('Rodrigo Freitas','Rua do Prado, Porto','919999999','rf@aluno.pt','41.154672','-8.623624','2'), ('José Sá','Rua de Baixo, Porto','934444444','js@aluno.pt','41.152863','-8.672752','1'),('António Lopes','Rua da Serra, Porto','910000000','al@aluno.pt','41.163332','-8.642176','3'),('Andreia Tavares','Rua da Lomba, Porto','968989899','at@aluno.pt','41.148339','-8.590472','4'),('Osvaldo Pinheiro','Rua da Piedade, Porto','910090090','op@aluno.pt','41.148454','-8.589472','4'),('Carina Silva','Rua da Igreja, Vila Nova de Gaia','930000004','cs@aluno.pt','41.132234','-8.626087','3'),('José Afonso','Rua de Algures, Ermesinde','930045004','ja@aluno.pt','41.210872','-8.548795','2'),('Marta Sá Santos','Rua da Alegria, Porto','967045004','mss@aluno.pt','41.155548','-8.602640','1');"

    };


    public DBHelper(@Nullable Context context) {
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

    public List<Turma> SelectAllList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM classes order by designation ASC", null);
        List<Turma> listTurma = new ArrayList<>();
        c.moveToFirst();
        if( c != null && c.moveToFirst() ){
        do {
            int posId = c.getColumnIndex("id");
            int posYear = c.getColumnIndex("year");
            int posDesignation = c.getColumnIndex("designation");

            int id = c.getInt(posId);
            int year = c.getInt(posYear);
            String designation = c.getString(posDesignation);

            listTurma.add(new Turma(id, year, designation));
        } while (c.moveToNext());
        c.close();
    }
        return listTurma;
    }

    public Cursor SelectByID(int id) {
        SQLiteDatabase db = getReadableDatabase();
        return db.rawQuery("SELECT * FROM classes WHERE id=?", new String[]{String.valueOf(id)});
    }

    public Turma SelectTurmaById(int turma_id) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM classes WHERE id=?", new String[]{String.valueOf(turma_id)});
        Turma turma;
        c.moveToFirst();

        do {
            int posId = c.getColumnIndex("id");
            int posYear = c.getColumnIndex("year");
            int posDesignation = c.getColumnIndex("designation");

            int id = c.getInt(posId);
            int year = c.getInt(posYear);
            String designation = c.getString(posDesignation);

            turma = new Turma(id, year, designation);
        } while (c.moveToNext());
        return turma;
    }

    public long Insert(int year, String designation) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("year", year);
        cv.put("designation", designation);
        return db.insert("classes", "", cv);
    }

    public long Update(int id, int year, String designation) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("year", year);
        cv.put("designation", designation);
        return db.update("classes", cv, "id=?", new String[]{String.valueOf(id)});
    }

    public long Delete(int id) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("classes", "id=?", new String[]{String.valueOf(id)});
    }
}

