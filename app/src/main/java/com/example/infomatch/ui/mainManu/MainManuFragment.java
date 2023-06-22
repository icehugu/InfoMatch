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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
import com.example.infomatch.databinding.FragmentMainmanuBinding;
import com.example.infomatch.ui.game.GameViewModel;

public class MainManuFragment extends Fragment {

    public void setTimer(Boolean timer) {

    }


    public void setCardsAmount(int cardsAmount) {
        Toast.makeText(getActivity(), cardsAmount + " is selected", Toast.LENGTH_SHORT).show();
    }
    private int cardsAmountChoice;
    private FragmentMainmanuBinding binding;
    private MainManuViewModel mainMenuViewModel ;
    private GameViewModel gameViewModel;
    private GameSettingsDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainmanuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
//        dialog = new Dialog(getActivity());
        mainMenuViewModel = new ViewModelProvider(this).get(MainManuViewModel.class);
        gameViewModel = new ViewModelProvider(this).get(GameViewModel.class);
        dialog = new GameSettingsDialog(gameViewModel);
        if (mainMenuViewModel.username != null) binding.userName.setText(mainMenuViewModel.username);
        binding.highScoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainManuFragment", "click");
                Bundle bundle = new Bundle();
                String userName = mainMenuViewModel.username;
                bundle.putString("userName", userName);
                Navigation.findNavController(v).navigate(R.id.action_mainManuFragment_to_highScoreFragment, bundle);
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

//    private void showDialog(DialogCallback callback) {
//        dialog.setContentView(R.layout.game_settings);
//        RadioGroup radioGroup = dialog.findViewById(R.id.radiogroup);
//        Spinner spinner = (Spinner) dialog.findViewById(R.id.spinner_num_of_cards);
//        Button cancelBtn = (Button) dialog.findViewById(R.id.cancel);
//        Button startBtn = (Button) dialog.findViewById(R.id.start);
//        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.cards_amount, android.R.layout.simple_spinner_item);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        spinner.setAdapter(adapter);
//        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                cardsAmountChoice = Integer.parseInt(parent.getItemAtPosition(position).toString());
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//
//            }
//        });
//        radioGroup.setOnCheckedChangeListener((radioGroup1, checkedId) -> {
//            RadioButton radioButton = (RadioButton) radioGroup1.findViewById(checkedId);
//        });
//
//        cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.cancel();
//            }
//        });
//
//        startBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int selectedId = radioGroup.getCheckedRadioButtonId();
//                if (selectedId == -1) {
//                    Toast.makeText(getActivity(), "Please select your income type", Toast.LENGTH_SHORT).show();
//                } else {
//                    RadioButton radioButton = (RadioButton) radioGroup.findViewById(selectedId);
//                    //String getIncome = editText.getText().toString();
//                    //callback.setText(getIncome);
//                    if (radioButton.getText() == "yes timer") callback.setTimer(true);
//                    else if (radioButton.getText() == "no timer") callback.setTimer(false);
//                    callback.setCardsAmount(cardsAmountChoice);
//                    Toast.makeText(getActivity(), radioButton.getText() + " is selected & cards amount is " + cardsAmountChoice , Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//
//        dialog.show();
//
//    }
//לבדוק בקשר לMYNAV לתת קישור דרך הקפיצה
}


