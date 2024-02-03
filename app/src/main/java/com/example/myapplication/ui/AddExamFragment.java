package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.Exams;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;

public class AddExamFragment extends Fragment {
    private EditText examTitleInput, examDateInput, examDescriptionInput;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_exam, container, false);

        Button submitExamBtn = view.findViewById(R.id.buttonSaveExam);

        examTitleInput = view.findViewById(R.id.editTextExamTitle);
        examDateInput = view.findViewById(R.id.editTextExamDate);
        examDescriptionInput = view.findViewById(R.id.editTextExamDescription);


        submitExamBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Create a new exam object with input data
                Exams newExam = new Exams(
                        examTitleInput.getText().toString(),
                        examDateInput.getText().toString(),
                        examDescriptionInput.getText().toString());

                // Add the new exam to Tempstore
                Tempstore tempstore = Tempstore.getInstance();
                Bundle args = getArguments();
                String classId = null;
                if (args != null && args.containsKey("classId")) {
                    classId = args.getString("classId");

                }

                tempstore.addExams(newExam,classId);

                // Navigate back to the previous fragment (optional)
                NavHostFragment.findNavController(AddExamFragment.this).popBackStack();
            }
        });



        return view;
    }
}
