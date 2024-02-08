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

import com.example.myapplication.ClassDetails;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;

public class EditClassFragment extends Fragment {

    private EditText editClassName, editClassTime, editInstructor;
    private ClassDetails currentClassDetails;
    Tempstore tempstore = Tempstore.getInstance();
    private String classId;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.edit_class, container, false);

        editClassName = view.findViewById(R.id.editClassName);
        editClassTime = view.findViewById(R.id.editClassTime);
        editInstructor = view.findViewById(R.id.editInstructor);

        Bundle args = getArguments();
        if (args != null && args.containsKey("classId")) {
            classId = args.getString("classId");
            // Assuming a method to fetch ClassDetails by ID
            currentClassDetails = fetchClassDetails(classId);
            if (currentClassDetails != null) {
                populateFields(currentClassDetails);
            }
        }

        view.findViewById(R.id.saveButton).setOnClickListener(v -> {
            if (currentClassDetails == null) {
                currentClassDetails = new ClassDetails();
            }
            currentClassDetails.id = classId;
            currentClassDetails.title = editClassName.getText().toString();
            currentClassDetails.datetime = editClassTime.getText().toString();
            currentClassDetails.instructor = editInstructor.getText().toString();
            tempstore.editClass(classId, currentClassDetails);

            NavHostFragment.findNavController(EditClassFragment.this).popBackStack();
        });

        return view;
    }

    private void populateFields(ClassDetails classDetails) {
        editClassName.setText(classDetails.title);
        editClassTime.setText(classDetails.datetime);
        editInstructor.setText(classDetails.instructor);
    }

    private ClassDetails fetchClassDetails(String classId) {
        for (ClassDetails classDetails : tempstore.getClassList()) {
            if (classDetails.id.equals(classId)) {
                return classDetails;
            }
        }
        return null;
    }
}
