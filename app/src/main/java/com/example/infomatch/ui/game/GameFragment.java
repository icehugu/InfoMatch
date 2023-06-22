package com.example.infomatch.ui.game;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.infomatch.databinding.FragmentGameBinding;
import com.example.infomatch.databinding.FragmentMainmanuBinding;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;
    private GameViewModel gameViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        gameViewModel = new ViewModelProvider(getActivity()).get(GameViewModel.class);
        if (gameViewModel.timer == true) binding.timer.setText("100");
        binding.score.setText(String.valueOf(gameViewModel.cardsAmount));

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
