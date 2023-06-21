package com.example.infomatch.ui.mainManu;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
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
        binding.highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainManuFragment", "click");
                Navigation.findNavController(v).navigate(R.id.action_mainManuFragment_to_highScoreFragment);
            }
        });

        binding.startGameBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                showDialog(new DialogCallback() {
//
//                    @Override
//                    public void setTimer(Boolean timer) {
//                         gameViewModel.timer= timer;
//                    }
//
//                    @Override
//                    public void setCardsAmount(int cardsAmount) {
//                        gameViewModel.cardsAmount = cardsAmount;
//                    }
//                });

               dialog.show(getParentFragmentManager(), "settings");

            }
        });


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
