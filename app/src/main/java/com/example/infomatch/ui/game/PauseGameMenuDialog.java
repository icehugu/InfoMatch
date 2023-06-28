package com.example.infomatch.ui.game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.navigation.Navigation;

import com.example.infomatch.R;

import java.util.List;

public class PauseGameMenuDialog extends DialogFragment {


    private GameViewModel gameViewModel;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.pause_game, null);

        Button buttonReturn = (Button) view.findViewById(R.id.return_btn);
        Button buttonRestart = (Button) view.findViewById(R.id.restart_btn);
        Button buttonMainMenu = (Button) view.findViewById(R.id.main_manu_btn);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameViewModel.timerResume();
                //Navigation.findNavController(getParentFragment().getView()).popBackStack();
                dismiss();
            }
        });

        buttonMainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameViewModel.timerStop();
                Navigation.findNavController(getParentFragment().getView()).popBackStack(R.id.mainManuFragment, false);
            }
        });

        buttonRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //gameViewModel.timerCancel();
                //gameViewModel.score = 0;
//                Fragment currentFragment = requireActivity().getSupportFragmentManager().findFragmentById(R.id.gameFragment);
//                Log.d("currentFragment", currentFragment.toString());
//                Navigation.findNavController(getParentFragment().getView()).popBackStack();
                //getParentFragment().getView().postInvalidate()
                gameViewModel.setUpGame();
                dismiss();

            }
        });


        builder.setView(view);


        return builder.create();
    }
}
