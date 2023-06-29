package com.example.infomatch.ui.game;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.CountDownTimer;

import android.text.InputType;
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
import androidx.navigation.Navigation;

import com.example.infomatch.R;
import com.example.infomatch.data.GameDataDao;
import com.example.infomatch.data.GameResult;
import com.example.infomatch.databinding.FragmentGameBinding;
import com.example.infomatch.databinding.FragmentMainmanuBinding;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
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
        binding.timer.setInputType(InputType.TYPE_NULL);
        binding.score.setInputType(InputType.TYPE_NULL);
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        binding.userName.setText(getArguments().getString("userName"));
        gameViewModel.userName = getArguments().getString("userName");
        if (!gameViewModel.timer) binding.timer.setVisibility(View.INVISIBLE);
        gameViewModel.setCardGame();
        binding.pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(gameViewModel.timer) {
                    gameViewModel.timerStop();
                }
                Navigation.findNavController(v).navigate(R.id.action_gameFragment_to_pauseGameMenuDialog);
            }
        });

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


            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (gameViewModel.button1 != null && gameViewModel.button2 != null) {
                        gameViewModel.button1.setTextScaleX(0);
                        gameViewModel.button2.setTextScaleX(0);
                        gameViewModel.button1 = null;
                        gameViewModel.button2 = null;
                        gameViewModel.curCombo = 0;
                    }
                    if(gameViewModel.button1 == null){
                        button.setTextScaleX(1);
                        gameViewModel.button1 = button;
                    }
                    else {

                            if (gameViewModel.button1 != button) {
                                button.setTextScaleX(1);
                                gameViewModel.button2 = button;
                                if (gameViewModel.checkPairOfCards(gameViewModel.button1, gameViewModel.button2)) {
                                    gameViewModel.button1.setClickable(false);
                                    gameViewModel.button2.setClickable(false);
                                    gameViewModel.button1 = null;
                                    gameViewModel.button2 = null;
                                    gameViewModel.pairsFound++;
                                    gameViewModel.curCombo++;
                                    gameViewModel.curScore += gameViewModel.curCombo;
                                    gameViewModel.updateCurScore();
                                    binding.score.setText(String.valueOf(gameViewModel.curScore));
                                    if(gameViewModel.pairsFound==gameViewModel.cardsAmount/2) {
                                        gameViewModel.endGame();
                                    }
                                    if(gameViewModel.pairsFound % 6 == 0){
                                        gameViewModel.nextCards();
                                    }
                                }
                            }
                        }
                    }
                }
            );
            button.setTextScaleX(0);
            gridButtons[i] = button;
            gridLayout.addView(button);
        }
        gameViewModel.gridButtons = gridButtons;
        gameViewModel.setUpGame();
        return view;



    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        gameViewModel.getTimerLiveData().observe(getViewLifecycleOwner(), timeLeft -> binding.timer.setText(timeLeft.toString()));
        gameViewModel.getGameEndLiveData().observe(getViewLifecycleOwner(), endGame -> {
            if (endGame){
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                if(gameViewModel.pairsFound==gameViewModel.cardsAmount/2) {
                    Calendar calendarInstance = Calendar.getInstance();
                    String currentDateAndTime = calendarInstance.get(Calendar.DAY_OF_MONTH) + "." +
                            (calendarInstance.get(Calendar.MONTH) + 1) +  "." + calendarInstance.get(Calendar.YEAR) +
                             "   "  + calendarInstance.get(Calendar.HOUR_OF_DAY) + ":" + calendarInstance.get(Calendar.MINUTE);
                    GameResult gr = new GameResult(binding.userName.getText().toString(),
                            gameViewModel.curScore,
                            gameViewModel.cardsAmount * 2.5 - Double.parseDouble(binding.timer.getText().toString()),
                            gameViewModel.pairsFound,
                            currentDateAndTime);
                    
                    gameViewModel.addItem(gr);

                    builder.setMessage(gameViewModel.userName + " you have won! \n" +
                                    "Your score is " + binding.score.getText().toString() + "\n" +
                                    "Your time left is " + binding.timer.getText().toString() + "seconds \n" +
                                    "You have found " + gameViewModel.pairsFound + " pairs")
                            .setTitle("YOU WON!");

                    builder.setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameViewModel.setUpGame();
                            binding.score.setText("0");
                        }
                    });

                    builder.setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Navigation.findNavController(getView()).popBackStack(R.id.mainManuFragment, false);
                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();

                    dialog.show();

                }
                else{
                    builder.setMessage(gameViewModel.userName +" you have lost! \n" +
                                    "Your score is " + binding.score.getText().toString() + "\n" +
                                    "You have found " + gameViewModel.pairsFound + " pairs")
                            .setTitle("YOU LOST!");

                    builder.setPositiveButton("Replay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameViewModel.setUpGame();
                        }
                    });

                    builder.setNegativeButton("Main Menu", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Navigation.findNavController(getView()).popBackStack(R.id.mainManuFragment, false);
                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();

                    dialog.show();
                }
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
