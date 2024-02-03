package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssignmentsAdapter extends RecyclerView.Adapter<AssignmentsAdapter.ViewHolder> {

    private List<Assignments> assignmentsList;
    private final Context context;
    private final OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onItemClick(String assignmentId);
    }

    public AssignmentsAdapter(Context context, List<Assignments> assignmentsList, OnItemClickListener onItemClickListener) {
        this.context = context;
        this.assignmentsList = assignmentsList;
        this.onItemClickListener = onItemClickListener;
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
        holder.dueDateTextView.setText(assignment.datedue);
        holder.descriptionTextView.setText(assignment.description);

//        holder.itemView.setOnClickListener(v -> {
//            if (onItemClickListener != null) {
//                onItemClickListener.onItemClick(assignment.getId());
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return assignmentsList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleTextView;
        TextView dueDateTextView;
        TextView descriptionTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            titleTextView = itemView.findViewById(R.id.titleTextView);
            dueDateTextView = itemView.findViewById(R.id.dueDateTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
        }
    }
}
