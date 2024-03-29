package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class ExamAdapter extends RecyclerView.Adapter<ExamAdapter.ViewHolder> {

    private List<Exams> examList;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onEditClick(String examId);
        void onDeleteClick(String examId);
    }

    public ExamAdapter(Context context, List<Exams> examList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.examList = examList;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exam_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Exams examDetails = examList.get(position);

        holder.examTitle.setText(examDetails.title);

        holder.dateTextView.setText(formatDate(examDetails.datetime));
        holder.locationView.setText(examDetails.location);
        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onEditClick(examDetails.id);
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onDeleteClick(examDetails.id);
                }
            }
        });
    }

    private String formatDate(Calendar calendar) {
        return android.text.format.DateFormat.format("MMM dd, yyyy", calendar).toString();
    }

    @Override
    public int getItemCount() {
        return examList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView examTitle, dateTextView, locationView;
        public View editButton;
        public View deleteButton;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            examTitle = itemView.findViewById(R.id.idExam);
            dateTextView = itemView.findViewById(R.id.idExamTime);
            locationView = itemView.findViewById(R.id.idLocation);
            editButton = itemView.findViewById(R.id.editExamButton);
            deleteButton = itemView.findViewById(R.id.deleteExamButton);
        }
    }
}