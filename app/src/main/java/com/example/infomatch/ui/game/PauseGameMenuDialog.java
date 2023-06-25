package com.example.infomatch.ui.game;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.navigation.Navigation;

import com.example.infomatch.R;

public class PauseGameMenuDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.pause_game, null);

        Button buttonReturn = (Button) view.findViewById(R.id.return_btn);
        Button buttonRestart = (Button) view.findViewById(R.id.restart_btn);
        Button buttonMainMenu = (Button) view.findViewById(R.id.main_manu_btn);

        buttonReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        builder.setView(view);


        return builder.create();
    }
}
