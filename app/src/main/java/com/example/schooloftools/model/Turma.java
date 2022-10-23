package com.example.schooloftools.model;

public class Turma {
    private int id;
    private int year;
    private String designation;

    public Turma(int id, int year, String designation) {
        this.id = id;
        this.year = year;
        this.designation = designation;
    }

    public Turma(int year, String designation) {
        this.year = year;
        this.designation = designation;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
}
