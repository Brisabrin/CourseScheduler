
package com.example.myapplication.ui;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.Assignments;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class AddAssignmentsFragment extends Fragment {

    private EditText assignmentTitleInput, assignmentDescriptionInput;
    private Button pickDueDateButton, submitAssignmentBtn;
    private Calendar selectedDueDate = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_assignment, container, false);

        pickDueDateButton = view.findViewById(R.id.buttonPickAssignmentDueDate);
        submitAssignmentBtn = view.findViewById(R.id.buttonSaveAssignment);
        assignmentTitleInput = view.findViewById(R.id.editTextAssignmentTitle);
        assignmentDescriptionInput = view.findViewById(R.id.editTextAssignmentDescription);

        if (pickDueDateButton != null && submitAssignmentBtn != null
                && assignmentTitleInput != null && assignmentDescriptionInput != null) {

            pickDueDateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showDatePicker();
                }
            });

            submitAssignmentBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    saveAssignment();
                }
            });
        } else {
            // Log an error or handle the situation where one of the views is null
        }

        return view;
    }

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedDueDate.set(Calendar.YEAR, year);
                        selectedDueDate.set(Calendar.MONTH, month);
                        selectedDueDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateDueDateButton();
                    }
                },
                selectedDueDate.get(Calendar.YEAR),
                selectedDueDate.get(Calendar.MONTH),
                selectedDueDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateDueDateButton() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        String formattedDate = dateFormat.format(selectedDueDate.getTime());
        pickDueDateButton.setText(formattedDate);
    }

    private void saveAssignment() {
        String title = assignmentTitleInput.getText().toString();
        String description = assignmentDescriptionInput.getText().toString();

        if (title.isEmpty() || description.isEmpty()) {
            return;
        }

        Assignments newAssignment = new Assignments(title, selectedDueDate, description);

        Tempstore tempstore = Tempstore.getInstance();
        Bundle args = getArguments();
        String classId = null;
        if (args != null && args.containsKey("classId")) {
            classId = args.getString("classId");
        }

        if (classId != null) {
            tempstore.addAssignments(newAssignment, classId);
            Toast.makeText(requireContext(), "Assignment saved!", Toast.LENGTH_SHORT).show();
            NavHostFragment.findNavController(this).popBackStack();
        } else {

        }
    }
}
