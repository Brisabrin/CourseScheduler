package com.example.myapplication.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.ClassDetailAdapter;
import com.example.myapplication.ClassDetails;
import com.example.myapplication.R;
import com.example.myapplication.Tempstore;
import com.example.myapplication.databinding.FragmentHomeBinding;

import java.io.Serializable;
import java.util.ArrayList;

public class HomeFragment extends Fragment implements ClassDetailAdapter.OnItemClickListener {

    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    Tempstore tempstore = Tempstore.getInstance();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        ArrayList<ClassDetails> classDetailsList = new ArrayList<>();
        classDetailsList = (ArrayList<ClassDetails>) tempstore.getClassList();

        recyclerView = view.findViewById(R.id.idCourse);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        ClassDetailAdapter adapter = new ClassDetailAdapter(getActivity(), classDetailsList, this);
        recyclerView.setAdapter(adapter);

        Button addButton = view.findViewById(R.id.addClassButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavHostFragment.findNavController(HomeFragment.this)
                        .navigate(R.id.action_homeFragment_to_classInputFragment);
            }
        });

        return view;
    }

    @Override
    public void onItemClick(String classId) {
        Bundle bundle = new Bundle();
        bundle.putString("classId", classId);

        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_homeFragment_to_redirectFragment, bundle);
    }

    @Override
    public void onEditClick(String classId) {
        Bundle bundle = new Bundle();
        bundle.putString("classId", classId);

        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_homeFragment_to_editClassFragment, bundle);
    }

    @Override
    public void onDeleteClick(String classId) {
        tempstore.deleteClass(classId);
        refreshRecyclerView();
    }

    private void refreshRecyclerView() {
        ArrayList<ClassDetails> classDetailsList = (ArrayList<ClassDetails>) tempstore.getClassList();
        ClassDetailAdapter adapter = new ClassDetailAdapter(getActivity(), classDetailsList, this);
        recyclerView.setAdapter(adapter);
    }
}