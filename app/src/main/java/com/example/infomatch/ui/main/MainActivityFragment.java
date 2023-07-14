package com.example.infomatch.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import com.example.infomatch.R;
import com.example.infomatch.databinding.FragmentActivityMainBinding;

public class MainActivityFragment extends Fragment {

    private FragmentActivityMainBinding binding;

    private MainActivityViewModel mainActivityViewModel ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentActivityMainBinding.inflate(inflater, container, false);
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding.enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.userName.getText().toString().equals("")) {
                    Bundle bundle = new Bundle();
                    mainActivityViewModel.username = binding.userName.getText().toString();
                    String userName = mainActivityViewModel.username;
                    bundle.putString("userName", userName);
                    Navigation.findNavController(v).navigate(R.id.action_mainActivityFragment_to_mainManuFragment, bundle);
                }
                else{
                    Toast.makeText(requireContext(), getResources().getString(R.string.please_enter_user_name), Toast.LENGTH_SHORT).show();
                }
            }
        });

        return binding.getRoot();
    }
}
