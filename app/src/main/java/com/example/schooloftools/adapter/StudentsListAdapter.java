package com.example.schooloftools.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.schooloftools.R;
import com.example.schooloftools.SelectListenerStudents;
import com.example.schooloftools.SelectListenerTurmas;
import com.example.schooloftools.model.Student;

import java.util.List;

public class StudentsListAdapter extends RecyclerView.Adapter<StudentsListAdapter.StudentViewHolder> {

    private final Context context;
   // private OnItemClickListener listener;
    private int contadorOnCreate = 0, contadorOnBind = 0;
    private List<Student> listStudents;
    private SelectListenerStudents listener;

    public StudentsListAdapter(Context context, List<Student> listStudents, SelectListenerStudents listener) {
        this.context = context;
        this.listStudents = listStudents;
        this.listener = listener;
    }

    @NonNull
    @Override
    public StudentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        contadorOnCreate++;
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View studentView = inflater.inflate(R.layout.row_students_list, parent, false);
        return new StudentViewHolder(studentView);
    }

    @Override
    public void onBindViewHolder(@NonNull StudentsListAdapter.StudentViewHolder holder, int position) {
        contadorOnBind++;
        Student student = listStudents.get(holder.getAdapterPosition());
        holder.bindData(student);


        holder.bt_delete_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listStudents.get(holder.getAdapterPosition()), "remover");
            }
        });

        holder.bt_edit_student.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClicked(listStudents.get(holder.getAdapterPosition()), "editar");
            }
        });

    }

    @Override
    public int getItemCount() {
        return listStudents.size();
    }

    class StudentViewHolder extends RecyclerView.ViewHolder {

        TextView tv_students;
        Button bt_delete_student, bt_addStudent, bt_edit_student;

        public StudentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_students = itemView.findViewById(R.id.tv_students);
            bt_delete_student = itemView.findViewById(R.id.bt_delete_student);
            bt_addStudent = itemView.findViewById(R.id.bt_addStudent);
            bt_edit_student = itemView.findViewById(R.id.bt_edit_student);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION && listener != null) {
                      //  listener.onItemClick(pos);
                    }
                }
            });*/
        }
        public void bindData(Student student) {tv_students.setText("Aluno: " + student.getId() + " - " + student.getName());
        }
    }
}