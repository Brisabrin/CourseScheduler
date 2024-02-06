package com.example.myapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.ViewHolder> {

    private List<TodoItem> todoList;
    private OnItemClickListener listener;

    public TodoListAdapter(List<TodoItem> todoList) {
        this.todoList = todoList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.todo_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TodoItem todoItem = todoList.get(position);
        holder.bind(todoItem);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private EditText editTextTitle;
        private EditText editTextDescription;
        private CheckBox completedCheckBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            editTextTitle = itemView.findViewById(R.id.editTextTitle);
            editTextDescription = itemView.findViewById(R.id.editTextDescription);
            completedCheckBox = itemView.findViewById(R.id.completedCheckBox);
        }

        public void bind(TodoItem todoItem) {
            editTextTitle.setText(todoItem.title);
            editTextDescription.setText(todoItem.description);
            completedCheckBox.setChecked(todoItem.isCompleted);
        }
    }
}

