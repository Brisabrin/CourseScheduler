package com.example.myapplication.ui;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.Exams;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;

import java.util.Calendar;

public class AddExamFragment extends Fragment {

    private EditText examTitleInput, examDescriptionInput;
    private TextView examDateTextView;
    private Button pickExamDateButton, submitExamBtn;

    private Calendar selectedExamDate = Calendar.getInstance();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_exam, container, false);

        examTitleInput = view.findViewById(R.id.editTextExamTitle);
        examDateTextView = view.findViewById(R.id.textViewExamDate);
        pickExamDateButton = view.findViewById(R.id.buttonPickExamDate);
        examDescriptionInput = view.findViewById(R.id.editTextExamDescription);
        submitExamBtn = view.findViewById(R.id.buttonSaveExam);

        pickExamDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });

        submitExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveExam();
            }
        });

        return view;
    }
// ...

    private void showDatePicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(),
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        selectedExamDate.set(Calendar.YEAR, year);
                        selectedExamDate.set(Calendar.MONTH, month);
                        selectedExamDate.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateExamDateTextView();
                    }
                },
                selectedExamDate.get(Calendar.YEAR),
                selectedExamDate.get(Calendar.MONTH),
                selectedExamDate.get(Calendar.DAY_OF_MONTH)
        );
        datePickerDialog.show();
    }

    private void updateExamDateTextView() {
        String formattedDate = android.text.format.DateFormat.format("MMM dd, yyyy", selectedExamDate).toString();
        examDateTextView.setText(formattedDate);
    }

    private void saveExam() {
        String title = examTitleInput.getText().toString();
        String description = examDescriptionInput.getText().toString();

        Exams newExam = new Exams(title, selectedExamDate, description);

        Tempstore tempstore = Tempstore.getInstance();
        Bundle args = getArguments();
        String classId = null;
        if (args != null && args.containsKey("classId")) {
            classId = args.getString("classId");
        }
        tempstore.addExams(newExam, classId);

        NavHostFragment.findNavController(this).popBackStack();
    }





}
