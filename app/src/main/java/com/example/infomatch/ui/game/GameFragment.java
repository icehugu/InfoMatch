package com.example.infomatch.ui.game;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.media.MediaPlayer;
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
import android.widget.GridLayout;
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

    private MediaPlayer mediaPlayer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentGameBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.card_flip);
        Button[] gridButtons = new Button[12];
        binding.timer.setInputType(InputType.TYPE_NULL);
        binding.score.setInputType(InputType.TYPE_NULL);
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        binding.userName.setText(getArguments().getString("userName"));
        gameViewModel.userName = getArguments().getString("userName");
        gameViewModel.setMediaPlayer(mediaPlayer);
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

        //GridLayout gridLayout = binding.cardsGrid;
        //gridLayout.setColumnCount(3);
        //gridLayout.setRowCount(4);
        for (int i = 0; i < 12; i++) {
            int id = getResources().getIdentifier("button_"+i, "id", requireActivity().getPackageName());
            gridButtons[i] = (Button) view.findViewById(id);

//            button.setLayoutParams(new ViewGroup.LayoutParams(
//                    ViewGroup.LayoutParams.MATCH_PARENT,
//                    ViewGroup.LayoutParams.MATCH_PARENT
//            ));
//
//            GridLayout.LayoutParams params = new GridLayout.LayoutParams(GridLayout.spec(GridLayout.UNDEFINED, 1f),      GridLayout.spec(GridLayout.UNDEFINED, 1f));
//            button.setLayoutParams(params);
//            button.setTag("button"+ i);

            Button button = gridButtons[i];
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    mediaPlayer.start();
                    if (gameViewModel.button1 != null && gameViewModel.button2 != null) {
                        gameViewModel.button1.setTextScaleX(0);
                        gameViewModel.button2.setTextScaleX(0);
                        gameViewModel.button1 = null;
                        gameViewModel.button2 = null;
                        gameViewModel.curCombo = 0;
                    }
                    if(gameViewModel.button1 == null){
                        button.setTextScaleX(0.8f);
                        gameViewModel.button1 = button;
                    }
                    else {

                            if (gameViewModel.button1 != button) {
                                button.setTextScaleX(0.8f);
                                gameViewModel.button2 = button;
                                if (gameViewModel.checkPairOfCards(gameViewModel.button1, gameViewModel.button2)) {
                                    gameViewModel.button1.setClickable(false);
                                    gameViewModel.button2.setClickable(false);
                                    gameViewModel.button1.setBackgroundColor(Color.GREEN);
                                    gameViewModel.button2.setBackgroundColor(Color.GREEN);
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
            gridButtons[i].setTextScaleX(0);
            //gridLayout.addView(button);
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

                    builder.setMessage(gameViewModel.userName + " " + getResources().getString(R.string.you_have_won) + "\n" +
                            getResources().getString(R.string.your_score_is) + " " + binding.score.getText().toString() + "\n" +
                                    getResources().getString(R.string.your_time_left_is) + " " + binding.timer.getText().toString() + " " + getResources().getString(R.string.seconds) +"\n" +
                                    getResources().getString(R.string.you_have_found) + " " + gameViewModel.pairsFound + " " + getResources().getString(R.string.pairs))
                            .setTitle(getResources().getString(R.string.you_won));

                    builder.setPositiveButton(getResources().getString(R.string.restart), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameViewModel.setUpGame();
                            binding.score.setText("0");
                        }
                    });

                    builder.setNegativeButton(getResources().getString(R.string.main_manu), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mediaPlayer.release();
                            Navigation.findNavController(getView()).popBackStack(R.id.mainManuFragment, false);
                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();

                    dialog.show();

                }
                else{
                    builder.setMessage(gameViewModel.userName +" " +getResources().getString(R.string.you_have_lost) +"\n" +
                                    getResources().getString(R.string.your_score_is) + " " + binding.score.getText().toString() + "\n" +
                                    getResources().getString(R.string.you_have_found) + " " + gameViewModel.pairsFound + " " + getResources().getString(R.string.pairs))
                            .setTitle(getResources().getString(R.string.you_lost));

                    builder.setPositiveButton(getResources().getString(R.string.restart), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            gameViewModel.setUpGame();
                        }
                    });

                    builder.setNegativeButton(getResources().getString(R.string.main_manu), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            mediaPlayer.release();
                            Navigation.findNavController(getView()).popBackStack(R.id.mainManuFragment, false);
                        }
                    });
                    builder.setCancelable(false);
                    AlertDialog dialog = builder.create();

                    dialog.show();
                }
            }
        });


        gameViewModel.getScoreLiveData().observe(getViewLifecycleOwner(), score -> {
            binding.score.setText(String.valueOf(score));
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }




}
