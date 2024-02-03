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
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.ClassDetailAdapter;
import com.example.myapplication.ClassDetails;
import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.myapplication.Tempstore;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.ui.dashboard.DashboardViewModel;
import java.util.ArrayList;
public class HomeFragment extends Fragment implements ClassDetailAdapter.OnItemClickListener{
    private FragmentHomeBinding binding;
    private RecyclerView recyclerView;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<ClassDetails> classDetailsList = new ArrayList<>();
        Tempstore tempstore = Tempstore.getInstance();
        classDetailsList = (ArrayList<ClassDetails>) tempstore.getClassList();
        recyclerView = view.findViewById(R.id.idCourse);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new ClassDetailAdapter(getActivity(), classDetailsList, this));
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
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override
    public void onItemClick(String classId) {
        // Handle item click, navigate to "assignments" page with the classId parameter
        Bundle bundle = new Bundle();
        bundle.putString("classId", classId);

        NavHostFragment.findNavController(HomeFragment.this)
                .navigate(R.id.action_homeFragment_to_redirectFragment, bundle);
    }
}