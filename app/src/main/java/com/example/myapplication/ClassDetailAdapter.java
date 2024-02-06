package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClassDetailAdapter extends RecyclerView.Adapter<ClassDetailAdapter.ViewHolder> {

    private ArrayList<ClassDetails> classStuff;
    private final Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String classId);
        void onEditClick(String classId);
        void onDeleteClick(String classId);
    }

    public ClassDetailAdapter(Context context, ArrayList<ClassDetails> classStuff, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.classStuff = classStuff;
        this.onItemClickListener = onItemClickListener;
    }

    @Override
    public ClassDetailAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ClassDetailAdapter.ViewHolder holder, int position) {
        ClassDetails classDetails = classStuff.get(position);

        holder.titleTextView.setText(classDetails.title);
        holder.dateTextView.setText(classDetails.datetime);
        holder.instructorTextView.setText(classDetails.instructor);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onEditClick(classDetails.getId());
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onDeleteClick(classDetails.getId());
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(classDetails.getId());
                }
            }
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView titleTextView, dateTextView, instructorTextView;
        public Button editButton, deleteButton;

        public ViewHolder(View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.idCourseName);
            dateTextView = itemView.findViewById(R.id.idCourseTime);
            instructorTextView = itemView.findViewById(R.id.idInstructor);
            editButton = itemView.findViewById(R.id.editButton);
            deleteButton = itemView.findViewById(R.id.deleteButton);
        }
    }

    @Override
    public int getItemCount() {
        return classStuff.size();
    }
}



