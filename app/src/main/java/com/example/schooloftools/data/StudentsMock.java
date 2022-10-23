package com.example.schooloftools.data;

import com.example.schooloftools.model.Student;

import java.util.ArrayList;
import java.util.List;

public class StudentsMock {
    
    private List<Student> listStudents;

    /*public StudentsMock() {
        listStudents = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listStudents.add(new Student(i, String.valueOf(i)));
        }
    } */
        public List<Student> getListStudents() {
            return listStudents;
        }

        public void setListStudents(List<Student> listStudents) {
            this.listStudents = listStudents;
        }

        public Student getStudentByPosition(int pos) { return listStudents.get(pos); }

        public Student getStudentById(int id) {
            for (int i = 0; i < listStudents.size(); i++) {
                if (listStudents.get(i).getId() == id)
                    return listStudents.get(i);
            }
            return null;

    }
}
