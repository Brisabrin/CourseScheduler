package com.example.myapplication.ui;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

//import androidx.recyclerview;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassDetailAdapter;
import com.example.myapplication.ClassDetails;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import


import com.example.myapplication.ui.dashboard.DashboardViewModel;
import java.util.ArrayList;
public class ClassInputFragment extends Fragment {

    // Define your input fields and submit button here
    private EditText titleInput, datetimeInput, instructorInput;

    public ClassInputFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.add_class, container, false);

        // Initialize your input fields and submit button

        Button submitBtn = view.findViewById(R.id.submitBtn);

        titleInput = view.findViewById(R.id.titleInput);
        datetimeInput = view.findViewById(R.id.datetimeInput);
        instructorInput = view.findViewById(R.id.instructorInput);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Handle the submit button click (e.g., retrieve input values and pass them back)
                // You can use a callback or ViewModel to communicate with HomeFragment
                // For simplicity, you can use Navigation Component to navigate back to HomeFragment
                NavHostFragment.findNavController(ClassInputFragment.this).popBackStack();
            }
        });

        return view;
    }
}