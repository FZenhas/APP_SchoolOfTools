package com.example.schooloftools.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooloftools.R;
import com.example.schooloftools.SelectListenerTurmas;
import com.example.schooloftools.model.Turma;

import java.util.List;

public class TurmasListAdapter extends RecyclerView.Adapter<TurmasListAdapter.TurmaViewHolder> {

    private final Context context;
    //private OnItemClickListener listener;
    private int contadorOnCreate = 0, contadorOnBind = 0;
    private List<Turma> listTurmas;
    private SelectListenerTurmas listener;

    public TurmasListAdapter(Context context, List<Turma> listTurmas, SelectListenerTurmas listener) {
        this.context = context;
        this.listTurmas = listTurmas;
        this.listener = listener;
    }


    @NonNull
    @Override
    public TurmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contadorOnCreate++;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View turmaView = inflater.inflate(R.layout.row_classes_list, parent, false);
        return new TurmaViewHolder(turmaView);
    }

    @Override
    public void onBindViewHolder(@NonNull TurmasListAdapter.TurmaViewHolder holder, int position) {
        contadorOnBind++;
        Turma turma = listTurmas.get(holder.getAdapterPosition());
        holder.bindData(turma);
        holder.bt_class_students.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listTurmas.get(holder.getAdapterPosition()), "visualizar");
            }
        });
        holder.bt_delete_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listTurmas.get(holder.getAdapterPosition()), "remover");

            }
        });
        holder.bt_edit_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listTurmas.get(holder.getAdapterPosition()), "editar");
            }
        });
    }


    @Override
    public int getItemCount() {
        return listTurmas.size();
    }

    class TurmaViewHolder extends RecyclerView.ViewHolder {

        TextView tv_classes;
        Button bt_class_students, bt_delete_class, bt_edit_class;

        public TurmaViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_classes = itemView.findViewById(R.id.tv_classes);
            bt_class_students = itemView.findViewById(R.id.bt_class_students);
            bt_delete_class = itemView.findViewById(R.id.bt_delete_class);
            bt_edit_class = itemView.findViewById(R.id.bt_edit_class);

            /*itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(pos);
                    }
                }
            }); */

        }

        public void bindData(Turma turma) {
            tv_classes.setText("Turma: " + turma.getYear() + "º - " + turma.getDesignation());
        }
    }

}