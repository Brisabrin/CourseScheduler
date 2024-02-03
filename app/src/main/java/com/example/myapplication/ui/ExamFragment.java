package com.example.myapplication.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ExamAdapter;
import com.example.myapplication.Exams;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExamFragment extends Fragment {

    private RecyclerView recyclerView;
    private ExamAdapter examAdapter;
    private String classId;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exam, container, false);

        recyclerView = view.findViewById(R.id.idExam);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));

        Bundle args = getArguments();
        if (args != null && args.containsKey("classId")) {
            classId = args.getString("classId");
        } else {
            // Log a message if classId is null
//            Log.d("ExamFragment", "classId is null");
        }

        // Proceed only if classId is not null
        if (classId != null) {
            List<Exams> examList = getExamDataFromTempstore();
            examAdapter = new ExamAdapter(getActivity(), examList, null);
            recyclerView.setAdapter(examAdapter);
        }

        Button addExamButton = view.findViewById(R.id.addExamButton);
        addExamButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddExamClicked();
            }
        });

        return view;
    }


    public void onAddExamClicked() {

        if (classId != null) {
            Bundle bundle = new Bundle();
            bundle.putString("classId", classId);

            NavHostFragment.findNavController(this).navigate(R.id.action_examsFragment_to_addExamFragment, bundle);
        }

    }
    private List<Exams> getExamDataFromTempstore() {
        List<Exams> examList = new ArrayList<>();

        if (classId!= null) {
            HashMap<String, Object> classData = Tempstore.coursedata.get(classId);
            if (classData.containsKey("Exams")) {
                ArrayList<Exams> exams = (ArrayList<Exams>) classData.get("Exams");
                examList.addAll(exams);
            }
        }

        return examList;
    }
}
