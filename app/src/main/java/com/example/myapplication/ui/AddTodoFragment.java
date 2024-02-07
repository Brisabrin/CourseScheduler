package com.example.myapplication.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;
import com.example.myapplication.Task;
import com.example.myapplication.Tempstore;

import java.util.Calendar;

public class AddTodoFragment extends Fragment {

    private EditText todoTitleInput, todoDescriptionInput;
    private TextView todoDeadlineTextView;
    private Button pickDeadlineButton, submitTodoBtn;

    private Calendar selectedDeadline = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_todo, container, false);

        todoTitleInput = view.findViewById(R.id.editToDoTitle);
        todoDeadlineTextView = view.findViewById(R.id.confirmEditToDoDeadline);
        pickDeadlineButton = view.findViewById(R.id.editToDoDeadline);
        todoDescriptionInput = view.findViewById(R.id.editToDoDescription);
        submitTodoBtn = view.findViewById(R.id.buttonEditInput);

        pickDeadlineButton.setOnClickListener(v -> showDatePicker());

        submitTodoBtn.setOnClickListener(v -> saveTodo());

        return view;
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                (view, year, month, dayOfMonth) -> {
                    selectedDeadline.set(Calendar.YEAR, year);
                    selectedDeadline.set(Calendar.MONTH, month);
                    selectedDeadline.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                    updateDeadlineTextView();
                },
                selectedDeadline.get(Calendar.YEAR),
                selectedDeadline.get(Calendar.MONTH),
                selectedDeadline.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateDeadlineTextView() {
        String formattedDate = android.text.format.DateFormat.format("MMM dd, yyyy", selectedDeadline).toString();
        todoDeadlineTextView.setText(formattedDate);
    }

    private void saveTodo() {
        String title = todoTitleInput.getText().toString();
        String deadline = android.text.format.DateFormat.format("MM/dd/yyyy", selectedDeadline).toString();
        String description = todoDescriptionInput.getText().toString();

        Task newTask = new Task(title, deadline, description, false);

        Tempstore tempstore = Tempstore.getInstance();
        tempstore.addTask(newTask);

        NavHostFragment.findNavController(AddTodoFragment.this).popBackStack();
    }
}
