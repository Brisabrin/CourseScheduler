package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Task;
import com.example.myapplication.Tempstore;
import com.example.myapplication.TodoRecViewAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class ToDoFragment extends Fragment {
    private RecyclerView todoRecView;
    private FloatingActionButton addNewTaskButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);

        todoRecView = view.findViewById(R.id.todoRecView);

        Tempstore tempstore = Tempstore.getInstance();
        if (tempstore.getTasks().size() == 0) {
            tempstore.addTask(new Task("Study CS 2340", "02/04/2024", "Study for Mini-Asssessment 1", false));
            tempstore.addTask(new Task("Read CS 2110 Lecture Slides", "02/08/2024", "Catch up with lectures", false));
        }
        TodoRecViewAdapter adapter = new TodoRecViewAdapter();
        todoRecView.setAdapter(adapter);
        todoRecView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        addNewTaskButton = view.findViewById(R.id.addNewTaskButton);
        addNewTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_toDoFragment_to_addTodoFragment);
            }
        });

        return view;
    }
}
