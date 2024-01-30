package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

//import androidx.recyclerview;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassDetailAdapter;
import com.example.myapplication.ClassDetails;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.databinding.FragmentDashboardBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.ui.dashboard.DashboardViewModel;
import java.util.ArrayList;


public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<ClassDetails> classDetailsList = new ArrayList<>();
        classDetailsList.add(new ClassDetails("1", "Java Basics", "2024-01-25 10:00 AM", "John Doe"));
        classDetailsList.add(new ClassDetails("2", "Data Structures", "2024-02-01 11:30 AM", "Alice Smith"));
        classDetailsList.add(new ClassDetails("3", "Advanced Java", "2024-02-15 02:00 PM", "Bob Johnson"));
        classDetailsList.add(new ClassDetails("4", "Database Design", "2024-03-05 09:15 AM", "Eva Brown"));
        classDetailsList.add(new ClassDetails("5", "Web Development", "2024-03-20 03:45 PM", "Charlie Wilson"));

        recyclerView = view.findViewById(R.id.idCourse);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ClassDetailAdapter(getActivity(), classDetailsList));
        Button addButton = view.findViewById(R.id.addClassButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToInputFragment();
            }
        });

        return view;
    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}