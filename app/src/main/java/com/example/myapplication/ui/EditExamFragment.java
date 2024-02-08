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

import com.example.myapplication.Exams;
import com.example.myapplication.Exams;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class EditExamFragment extends Fragment {

    private EditText editExamTitle, editExamDueDate, editExamLocation;
    private Exams exam = new Exams();
    private Exams currentExam;
    private Tempstore tempstore = Tempstore.getInstance();
    private String examId;
    private String classId;
    private Calendar selectedDueDate = Calendar.getInstance(); // Declare selectedDueDate

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_exam, container, false);

        editExamTitle = view.findViewById(R.id.editExamTitle);
        editExamDueDate = view.findViewById(R.id.editExamDueDate);
        editExamLocation = view.findViewById(R.id.editExamLocation);

        Bundle args = getArguments();
        if (args != null) {
            examId = args.getString("examId");
            classId = args.getString("classId");
            // Retrieve a list of exams for the given class ID
            List<Exams> exams = tempstore.getExamList(classId);
            // Find the current exam in the list using the exam ID
            for (Exams exam : exams) {
                if (exam.id.equals(examId)) {
                    currentExam = exam;
                    break;
                }
            }
            // Populate the fields with the current exam's data
            if (currentExam != null) {
                populateFields(currentExam);
            } else {
                // Handle case where exam is not found
                // This could involve showing an error message or logging the error
            }
        }

        Button pickDueDateButton = view.findViewById(R.id.buttonPickExamDate);
        pickDueDateButton.setOnClickListener(v -> showDatePicker());

        view.findViewById(R.id.saveExamButton).setOnClickListener(v -> saveExam());

        return view;
    }

    private void populateFields(Exams exam) {
        editExamTitle.setText(exam.title);
        editExamLocation.setText(exam.location);

        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());
        if (exam.datetime != null) {
            editExamDueDate.setText(dateFormat.format(exam.datetime.getTime()));
            selectedDueDate.setTime(exam.datetime.getTime());
        }
    }



    private void showDatePicker() {
        Calendar currentDueDate = exam.datetime != null ? exam.datetime : Calendar.getInstance();
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
        editExamDueDate.setText(formattedDate);
    }

    private void saveExam() {
        Exams editedExam = new Exams(exam);

        editedExam.title = editExamTitle.getText().toString();
        editedExam.id = examId;

        String dueDateString = editExamDueDate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault());

        try {
            Date date = dateFormat.parse(dueDateString);
            if (date != null) {
                editedExam.datetime= Calendar.getInstance();
                editedExam.datetime.setTime(date);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        editedExam.location = editExamLocation.getText().toString();
        tempstore.editExam(examId, classId, editedExam);

        NavHostFragment.findNavController(EditExamFragment.this).popBackStack();
    }
}
