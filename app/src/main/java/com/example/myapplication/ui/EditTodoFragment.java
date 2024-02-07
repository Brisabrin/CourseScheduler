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

public class EditTodoFragment extends Fragment {
    private EditText editToDoTitle, editToDoDescription;
    private TextView confirmEditToDoDeadline;
    private Button editToDoDeadline, buttonEditInput;
    private int taskPosition;
    private Task task;
    private Calendar selectedDeadline = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_todo, container, false);

        if (getArguments() != null) {
            taskPosition = getArguments().getInt("taskPosition");
            // Assuming you have a method in Tempstore to get a task by position
            task = Tempstore.getInstance().getTasks().get(taskPosition);
        }

        initViews(view);
        populateData();

        editToDoDeadline.setOnClickListener(v -> showDatePicker());
        buttonEditInput.setOnClickListener(v -> saveEdits());

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
        confirmEditToDoDeadline.setText(formattedDate);
    }

    private void initViews(View view) {
        editToDoTitle = view.findViewById(R.id.editToDoTitle);
        confirmEditToDoDeadline = view.findViewById(R.id.confirmEditToDoDeadline);
        editToDoDescription = view.findViewById(R.id.editToDoDescription);
        editToDoDeadline = view.findViewById(R.id.editToDoDeadline);
        buttonEditInput = view.findViewById(R.id.buttonEditInput);
    }

    private void populateData() {
        editToDoTitle.setText(task.getTitle());
        editToDoDescription.setText(task.getDescription());
        confirmEditToDoDeadline.setText(task.getDeadline());
    }

    private void saveEdits() {
        String title = editToDoTitle.getText().toString();
        String deadline = android.text.format.DateFormat.format("MM/dd/yyyy", selectedDeadline).toString();
        String description = editToDoDescription.getText().toString();
        task.setTitle(title);
        task.setDeadline(deadline);
        task.setDescription(description);

        Tempstore.getInstance().updateTask(taskPosition, task);

        NavHostFragment.findNavController(EditTodoFragment.this).popBackStack();
    }
}
