package com.example.myapplication.ui;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.ArrayAdapter;

import com.example.myapplication.Assignments;
import com.example.myapplication.AssignmentsAdapter;
import com.example.myapplication.ClassDetailAdapter;
import com.example.myapplication.ClassDetails;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;
import com.example.myapplication.ui.home.HomeFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.widget.Spinner;

public class AssignmentsFragment extends Fragment implements AssignmentsAdapter.OnItemClickListener{

    private RecyclerView recyclerView;
    private AssignmentsAdapter assignmentsAdapter;
    private String classId;
    Tempstore tempstore = Tempstore.getInstance();
    private Spinner spinnerSortBy;


    @SuppressLint("MissingInflatedId")
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);


        recyclerView = view.findViewById(R.id.idAssignments);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle args = getArguments();
        if (args != null && args.containsKey("classId")) {
            classId = args.getString("classId");
        }


        if (classId != null) {
            List<Assignments> assignmentsList = getAssignmentsDataFromTempstore();
            assignmentsAdapter = new AssignmentsAdapter(getActivity(), assignmentsList, this);
            recyclerView.setAdapter(assignmentsAdapter);
        }
        Button addAssignmentButton = view.findViewById(R.id.addAssignmentButton);
        addAssignmentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddAssignmentClicked();
            }
        });

        spinnerSortBy = view.findViewById(R.id.spinnerSortBy);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.sort_options,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerSortBy.setAdapter(adapter);

        spinnerSortBy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedItem = parentView.getItemAtPosition(position).toString();
                if ("Sort by Date".equals(selectedItem)) {
                    Tempstore.sortByDueDate(classId);
                    refreshRecyclerView();

                } else if ("Sort by Title".equals(selectedItem)) {
                    Tempstore.sortByTitle(classId);
                    refreshRecyclerView();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        return view;
    }

    public void onAddAssignmentClicked() {
        if (classId != null) {
            Bundle bundle = new Bundle();
            bundle.putString("classId", classId);
            NavHostFragment.findNavController(this).navigate(R.id.action_assignmentsFragment_to_addAssignmentsFragment, bundle);
        }
    }

    private List<Assignments> getAssignmentsDataFromTempstore() {
        List<Assignments> assignmentsList = new ArrayList<>();

        if (classId != null) {
            HashMap<String, Object> classData = Tempstore.coursedata.get(classId);
            if (classData.containsKey("Assignments")) {
                ArrayList<Assignments> assignments = (ArrayList<Assignments>) classData.get("Assignments");
                assignmentsList.addAll(assignments);
            }
        }

        return assignmentsList;
    }

    @Override
    public void onEditClick(String assignmentId) {
        Log.d("MyClass", "edit button clicked");

        Bundle bundle = new Bundle();
        bundle.putString("assignmentId", assignmentId);
        bundle.putString("classId", classId);

        NavHostFragment.findNavController(this)
                .navigate(R.id.action_assignmentsFragment_to_editAssignmentsFragment, bundle);
    }
    public void onDeleteClick(String assignmentId) {
        Log.d("MyClass", "delete button clicked");
        tempstore.deleteAssignment(assignmentId, classId);
        refreshRecyclerView();
    }

    private void refreshRecyclerView() {
        ArrayList<Assignments> assignmentsList = (ArrayList<Assignments>) tempstore.getAssignmentList(classId);
        AssignmentsAdapter adapter = new AssignmentsAdapter(getActivity(), assignmentsList, this);
        recyclerView.setAdapter(adapter);
    }
}
