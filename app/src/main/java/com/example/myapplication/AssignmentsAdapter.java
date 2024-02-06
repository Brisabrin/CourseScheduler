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

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {

    private List<Assignments> assignmentsList;
    private final Context context;
    private final OnItemClickListener mListener;

    public interface OnItemClickListener {
        void onEditClick(String assignmentId);
        void onDeleteClick(String assignmentId);
    }

    public AssignmentsAdapter(Context context, List<Assignments> assignmentsList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.assignmentsList = assignmentsList;
        this.mListener = onItemClickListener;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.assignment_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Assignments assignment = assignmentsList.get(position);

        holder.titleTextView.setText(assignment.title);
        holder.dueDateTextView.setText(formatDate(assignment.dueDate));
        holder.descriptionTextView.setText(assignment.description);

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onEditClick(assignment.id);
                }
            }
        });

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onDeleteClick(assignment.id);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return assignmentsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View editButton;
        public View deleteButton;
        TextView titleTextView;
        TextView dueDateTextView;
        TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            editButton = itemView.findViewById(R.id.editAssignmentButton);
            deleteButton = itemView.findViewById(R.id.deleteAssignmentButton);
        }
    }
    private String formatDate(Calendar calendar) {
        return android.text.format.DateFormat.format("MMM dd, yyyy", calendar).toString();
    }

}
