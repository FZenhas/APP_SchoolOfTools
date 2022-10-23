package com.example.schooloftools.data;

import com.example.schooloftools.model.Turma;

import java.util.ArrayList;
import java.util.List;

public class TurmasMock {
    private List<Turma> listTurmas;

    public TurmasMock() {
        listTurmas = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            listTurmas.add(new Turma(i, String.valueOf(i)));
        }
    }
    public List<Turma> getListTurmas() {
        return listTurmas;
    }

    public void setListTurmas(List<Turma> listTurmas) {
        this.listTurmas = listTurmas;
    }

    public Turma getTurmaByPosition(int pos) { return listTurmas.get(pos); }

    public Turma getTurmaById(int id) {
        for (int i = 0; i < listTurmas.size(); i++) {
            if (listTurmas.get(i).getId() == id)
                return listTurmas.get(i);
        }
        return null;

    }
}
