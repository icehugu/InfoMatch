package com.example.infomatch.ui.mainManu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.example.infomatch.R;
import com.example.infomatch.ui.game.GameViewModel;

public  class GameSettingsDialog extends DialogFragment {


    private GameViewModel gameViewModel;
    private int cardsAmountChoice;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        gameViewModel = new ViewModelProvider(requireActivity()).get(GameViewModel.class);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.game_settings, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_num_of_cards);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.cards_amount, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        RadioGroup radioGroup = (RadioGroup) view.findViewById(R.id.radiogroup);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                cardsAmountChoice = Integer.parseInt(parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        builder.setView(view)
                .setPositiveButton(getResources().getString(R.string.start), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        Bundle bundle = new Bundle();
                        bundle.putString("userName", getArguments().getString("userName"));
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        RadioButton radioButton = (RadioButton) radioGroup.findViewById(selectedId);
                        if (radioButton.getText().toString().equals(getResources().getString(R.string.yes_timer)) ) {
                            gameViewModel.timer = true;
                        }
                        else {
                            gameViewModel.timer = false;
                        }
                        gameViewModel.cardsAmount = cardsAmountChoice;
                        Navigation.findNavController(getParentFragment().getView()).navigate(R.id.action_gameSettingsDialog_to_gameFragment, bundle);
                    }
                })
                .setNegativeButton(getResources().getString(R.string.other_time), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        return builder.create();

    }
}
