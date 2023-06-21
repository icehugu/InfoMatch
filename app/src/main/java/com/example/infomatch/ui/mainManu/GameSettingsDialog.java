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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.infomatch.R;
import com.example.infomatch.ui.game.GameViewModel;

public  class GameSettingsDialog extends DialogFragment {


    private GameViewModel gameViewModel;
    private int cardsAmountChoice;
    public GameSettingsDialog(GameViewModel gameViewModel) {
        this.gameViewModel = gameViewModel;
    }



    public interface DialogCallback {
        public void setTimer(Boolean timer);
        public void setCardsAmount(int cardsAmount);
    }

   DialogCallback listener;

//    @Override
//    public void onAttachFragment(@NonNull Fragment childFragment) {
//        super.onAttachFragment(childFragment);
//        listener = (DialogCallback) childFragment;
//     }
//    @Override
//    public void onAttach(@NonNull Context context) {
//        super.onAttach(context);
//        try {
//            // Instantiate the NoticeDialogListener so we can send events to the host
//            listener = (DialogCallback) context;
//        } catch (ClassCastException e) {
//            // The activity doesn't implement the interface, throw exception
//            throw new ClassCastException(getActivity().toString()
//                    + " must implement NoticeDialogListener");
//        }
//
//    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.game_settings, null);
        Spinner spinner = (Spinner) view.findViewById(R.id.spinner_num_of_cards);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.cards_amount, android.R.layout.simple_spinner_item);

        Boolean timerChoice;
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
                .setPositiveButton("Start", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        int selectedId = radioGroup.getCheckedRadioButtonId();
                        gameViewModel.cardsAmount = cardsAmountChoice;
                        RadioButton radioButton = (RadioButton) radioGroup.findViewById(selectedId);
                        if (radioButton.getText() == "yes timer") gameViewModel.timer = true;
                        else if (radioButton.getText() == "no timer") gameViewModel.timer = false;
                        Navigation.findNavController(getParentFragment().getView()).navigate(R.id.action_mainManuFragment_to_gameFragment);
                        //listener.setTimer();
                    }
                })
                .setNegativeButton("Other time!", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //listener.onDialogNegativeClick(GameSettingsDialog.this);
                    }
                });
        return builder.create();

    }
}
