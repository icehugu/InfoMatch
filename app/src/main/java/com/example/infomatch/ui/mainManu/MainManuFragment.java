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

    private FragmentMainmanuBinding binding;
    private MainManuViewModel mainMenuViewModel ;
    private GameSettingsDialog dialog;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentMainmanuBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        mainMenuViewModel = new ViewModelProvider(this).get(MainManuViewModel.class);
        dialog = new GameSettingsDialog();
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
                Bundle bundle = new Bundle();
                String userName = mainMenuViewModel.username;
                bundle.putString("userName", userName);
                Navigation.findNavController(v).navigate(R.id.action_mainManuFragment_to_gameSettingsDialog, bundle);
            }
        });


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}


