package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.R;
import com.example.myapplication.Tempstore;
import com.example.myapplication.TodoItem;
import com.example.myapplication.TodoListAdapter;

import java.util.List;

public class TodoListFragment extends Fragment {

    private RecyclerView recyclerView;
    private TodoListAdapter todoListAdapter;
    private CardView inputCard;
    private EditText editTextInputTitle;
    private EditText editTextInputDescription;
    private Button buttonSubmitInput;
    private Button buttonAddTodo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.todolist_fragment, container, false);

        recyclerView = view.findViewById(R.id.idToDo);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        editTextInputTitle = view.findViewById(R.id.editTextInputTitle);
        editTextInputDescription = view.findViewById(R.id.editTextInputDescription);
        buttonSubmitInput = view.findViewById(R.id.buttonSubmitInput);
        buttonAddTodo = view.findViewById(R.id.buttonAddTodo);

        List<TodoItem> todoList = Tempstore.getTodoList();

        todoListAdapter = new TodoListAdapter(todoList);
        recyclerView.setAdapter(todoListAdapter);

        inputCard = view.findViewById(R.id.inputCard);
        inputCard.setVisibility(View.GONE);
        buttonAddTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputCard.setVisibility(View.VISIBLE);
            }
        });

        buttonSubmitInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newTitle = editTextInputTitle.getText().toString();
                String newDescription = editTextInputDescription.getText().toString();

                TodoItem newItem = new TodoItem(newTitle, newDescription, false);
                Tempstore.addTodoItem(newItem);

                inputCard.setVisibility(View.GONE);


                todoListAdapter.notifyDataSetChanged();

                editTextInputTitle.setText("");
                editTextInputDescription.setText("");
            }
        });

        todoListAdapter.setOnItemClickListener(new TodoListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

                TodoItem clickedItem = todoList.get(position);

            }
        });

        return view;
    }
}

