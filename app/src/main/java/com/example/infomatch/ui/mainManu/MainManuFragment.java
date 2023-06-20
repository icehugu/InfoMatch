package com.example.infomatch.ui.mainManu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.infomatch.databinding.FragmentMainmanuBinding;

public class MainManuFragment extends Fragment {

    private FragmentMainmanuBinding binding;
    private MainManuViewModel mainMenuViewModel ;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainmanuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mainMenuViewModel = new ViewModelProvider(this).get(MainManuViewModel.class);
        if (mainMenuViewModel.username != null) binding.userName.setText(mainMenuViewModel.username);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    public MainManuViewModel getViewModel() {
        return this.mainMenuViewModel;
    }
}
