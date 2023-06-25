package com.example.infomatch.ui.game;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.infomatch.databinding.FragmentGameBinding;
import com.example.infomatch.databinding.FragmentMainmanuBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;
    private GameViewModel gameViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        Button[] gridButtons = new Button[12];
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        binding.userName.setText(getArguments().getString("userName"));
        gameViewModel.userName = getArguments().getString("userName");
        if (gameViewModel.timer == true) binding.timer.setText("100");
        binding.score.setText(String.valueOf(gameViewModel.cardsAmount));
        gameViewModel.setCardGame();

        GridLayout gridLayout = binding.cardsGrid;
        gridLayout.setColumnCount(3);
        gridLayout.setRowCount(4);
        for (int i = 0; i < 12; i++) {
            Button button = new Button(requireActivity());
            button.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            ));
            button.setTag("button"+ i);

            button.setText("text");
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });
            gridButtons[i] = button;
            gridLayout.addView(button);
        }
        String[]cardsPositionsArray = gameViewModel.cardsPositionsArray;
        Collections.shuffle(Arrays.asList(cardsPositionsArray));
        //Random random = new Random();
        for (int i = 0; i < 6; i++) {
            gridButtons[cardsPositionsArray[i*2]].setText(gameViewModel.cardGame.getQaPair().keySet().toArray()[i].toString());
            gridButtons[cardsPositionsArray[(i*2)+1]].setText(gameViewModel.cardGame.getQaPair().get(gameViewModel.cardGame.getQaPair().keySet().toArray()[i].toString()));
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
