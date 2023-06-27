package com.example.infomatch.ui.game;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.renderscript.ScriptGroup;
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
import com.example.infomatch.databinding.FragmentGameBinding;
import com.example.infomatch.databinding.FragmentMainmanuBinding;

import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GameFragment extends Fragment {

    private FragmentGameBinding binding;
    private GameViewModel gameViewModel;

    CountDownTimer timer;
    long milliLeft;
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
        if (gameViewModel.timer == true) binding.timer.setText("100");
        binding.score.setText(String.valueOf(gameViewModel.cardsAmount));
        gameViewModel.setCardGame();
        gameViewModel.startTimer(gameViewModel.cardsAmount*5000);
        binding.pauseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameViewModel.timerPause();
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
                                    if(gameViewModel.pairsFound==6) {
                                        gameViewModel.timerCancel();
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
        Integer[] cardsPositionsArray = gameViewModel.cardsPositionsArray;
        Collections.shuffle(Arrays.asList(cardsPositionsArray));
        for (int i = 0; i < 6; i++) {
            gridButtons[cardsPositionsArray[i*2]].setText(gameViewModel.cardGame.getQaPair().keySet().toArray()[i].toString());
            gridButtons[cardsPositionsArray[(i*2)+1]].setText(gameViewModel.cardGame.getQaPair().get(gameViewModel.cardGame.getQaPair().keySet().toArray()[i].toString()));
        }

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gameViewModel.getTimerLiveData().observe(getViewLifecycleOwner(), timeLeft -> binding.timer.setText(timeLeft.toString()));
    }
//    public void startTimer(long timeLengthMilli) {
//        timer = new CountDownTimer(timeLengthMilli, 1000) {
//
//            public void onTick(long millisUntilFinished) {
//                binding.timer.setText("" + millisUntilFinished / 1000);
//                milliLeft = millisUntilFinished;
//            }
//
//            public void onFinish() {
//                //mTextField.setText("done!");
//            }
//        }.start();
//
//    }

//    public void timerPause() {
//        timer.cancel();
//    }

//    private void timerResume() {
//        startTimer(milliLeft);
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
