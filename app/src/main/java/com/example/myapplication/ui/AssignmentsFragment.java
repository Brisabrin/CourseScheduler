package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.Assignments;
import com.example.myapplication.AssignmentsAdapter;
import com.example.myapplication.R;
import java.util.ArrayList;
import java.util.List;

public class AssignmentsFragment extends Fragment {

    private RecyclerView recyclerView;
    private AssignmentsAdapter assignmentsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);

        recyclerView = view.findViewById(R.id.idAssignments);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        List<Assignments> assignmentsList = getDummyAssignmentsData();
        assignmentsAdapter = new AssignmentsAdapter(getActivity(), assignmentsList, null);
        recyclerView.setAdapter(assignmentsAdapter);

        return view;
    }

    private List<Assignments> getDummyAssignmentsData() {
        List<Assignments> dummyAssignments = new ArrayList<>();
        dummyAssignments.add(new Assignments("Assignment 1", "Due Date 1", "Description 1"));
        dummyAssignments.add(new Assignments("Assignment 2", "Due Date 2", "Description 2"));
        // Add more dummy data as needed
        return dummyAssignments;
    }
}
