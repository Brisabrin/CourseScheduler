package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.myapplication.R;

public class RedirectFragment extends Fragment {

    private String classId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.redirect_home, container, false);

        // Retrieve classId from arguments
        Bundle args = getArguments();
        if (args != null && args.containsKey("classId")) {
            classId = args.getString("classId");
        }

        // Example: Handle clicks on assignments and exams
        TextView textViewAssignments = view.findViewById(R.id.textViewAssignments);
        TextView textViewExams = view.findViewById(R.id.textViewExams);

        textViewAssignments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToAssignments();
            }
        });

        textViewExams.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToExams();
            }
        });

        return view;
    }

    // Handle click events
    private void redirectToAssignments() {
        // Add code to navigate to the assignments page with classId
        // You can use Intent or Navigation component to navigate
        navigateToPage("Assignments");
    }

    private void redirectToExams() {
        // Add code to navigate to the exams page with classId
        // You can use Intent or Navigation component to navigate
        navigateToPage("Exams");
    }

    private void navigateToPage(String page) {
        // Add navigation logic here, e.g., using Navigation component
        Bundle bundle = new Bundle();
        bundle.putString("classId", classId);
        bundle.putString("page", page);

        if ("Exams".equals(page)) {
            NavHostFragment.findNavController(RedirectFragment.this)
                    .navigate(R.id.action_redirectFragment_to_examsFragment, bundle);

        }
        else if ("Assignments".equals(page)) {
            NavHostFragment.findNavController(RedirectFragment.this)
                    .navigate(R.id.action_redirectFragment_to_assignmentsFragment, bundle);
        }
    }



}
