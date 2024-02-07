package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TodoRecViewAdapter extends RecyclerView.Adapter<TodoRecViewAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_todo_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Tempstore tempstore = Tempstore.getInstance();
        holder.todoTitle.setText(tempstore.getTasks().get(position).getTitle());
        holder.todoDeadline.setText(tempstore.getTasks().get(position).getDeadline());
        holder.todoDescription.setText(tempstore.getTasks().get(position).getDescription());

        holder.todoCompleteCheckBox.setOnClickListener(v -> {
            tempstore.removeTask(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, tempstore.getTasksSize());
        });

        holder.todoEditIcon.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putInt("taskPosition", position);
            Navigation.findNavController(v).navigate(R.id.action_toDoFragment_to_editTodoFragment, bundle);
        });
    }

    @Override
    public int getItemCount() {
        Tempstore tempstore = Tempstore.getInstance();
        return tempstore.getTasksSize();
    }

    public void setTasks(ArrayList<Task> tasks) {
        Tempstore tempstore = Tempstore.getInstance();
        tempstore.setTasks(tasks);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private CardView taskTodoItem;
        private TextView todoTitle, todoDeadline, todoDescription;
        private CheckBox todoCompleteCheckBox;
        private ImageButton todoEditIcon;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            taskTodoItem = itemView.findViewById(R.id.taskTodoItem);
            todoTitle = itemView.findViewById(R.id.todoTitle);
            todoDeadline = itemView.findViewById(R.id.todoDeadline);
            todoDescription = itemView.findViewById(R.id.todoDescription);
            todoCompleteCheckBox = itemView.findViewById(R.id.todoCompleteCheckBox);
            todoEditIcon = itemView.findViewById(R.id.todoEditIcon);
        }
    }
}
