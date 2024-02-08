package com.example.myapplication.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.Assignments;
import com.example.myapplication.Assignments;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class EditAssignmentsFragment extends Fragment {

    private EditText editAssignmentTitle, editAssignmentDueDate, editAssignmentDescription;
    private Assignments currentAssignment;
    private Assignments assignment = new Assignments();
    private Tempstore tempstore = Tempstore.getInstance();
    private String assignmentId;
    private String classId;
    private Calendar selectedDueDate = Calendar.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_assignment, container, false);

        editAssignmentTitle = view.findViewById(R.id.editAssignmentTitle);
        editAssignmentDescription = view.findViewById(R.id.editAssignmentDescription);
        editAssignmentDueDate = view.findViewById(R.id.editAssignmentDueDate);

        Bundle args = getArguments();
        if (args != null) {
            assignmentId = args.getString("assignmentId");
            classId = args.getString("classId");
            currentAssignment = tempstore.getAssignment(classId, assignmentId);
            populateFields(currentAssignment);
        }

        Button pickDueDateButton = view.findViewById(R.id.buttonPickAssignmentDueDate);
        pickDueDateButton.setOnClickListener(v -> showDatePicker());

        view.findViewById(R.id.saveAssignmentButton).setOnClickListener(v -> saveAssignment());

        return view;
    }

    private void populateFields(Assignments assignment) {
        if (assignment != null) {
            editAssignmentTitle.setText(assignment.title);
            editAssignmentDescription.setText(assignment.description);

            SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
            if (assignment.dueDate != null) {
                editAssignmentDueDate.setText(dateFormat.format(assignment.dueDate.getTime()));
                selectedDueDate.setTime(assignment.dueDate.getTime());
            }
        }
    }

    private void showDatePicker() {
        Calendar currentDueDate = assignment.dueDate != null ? assignment.dueDate : Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDueDate.set(Calendar.YEAR, year);
                        selectedDueDate.set(Calendar.MONTH, month);
                        selectedDueDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateDueDateField();
                    }
                },
                currentDueDate.get(Calendar.YEAR),
                currentDueDate.get(Calendar.MONTH),
                currentDueDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateDueDateField() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(selectedDueDate.getTime());
        editAssignmentDueDate.setText(formattedDate);
    }

    private void saveAssignment() {
        Assignments editedAssignment = new Assignments(assignment);

        editedAssignment.title = editAssignmentTitle.getText().toString();
        editedAssignment.id = assignmentId;

        String dueDateString = editAssignmentDueDate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

        try {
            Date date = dateFormat.parse(dueDateString);
            if (date != null) {
                editedAssignment.dueDate = Calendar.getInstance();
                editedAssignment.dueDate.setTime(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editedAssignment.description = editAssignmentDescription.getText().toString();
        tempstore.editAssignment(assignmentId, classId, editedAssignment);

        NavHostFragment.findNavController(EditAssignmentsFragment.this).popBackStack();
    }

}
