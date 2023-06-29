package com.example.infomatch.ui.highScore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.infomatch.R;
import com.example.infomatch.databinding.FragmentHighscoreBinding;
import com.example.infomatch.databinding.FragmentMainmanuBinding;

public class HighScoreFragment extends Fragment {

    private FragmentHighscoreBinding binding;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHighscoreBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        binding.userName.setText(getArguments().getString("userName"));
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainManuFragment", "click");

                Navigation.findNavController(v).navigate(R.id.action_highScoreFragment_to_mainManuFragment);
            }
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        highScoreViewModel.getAll().observe(getViewLifecycleOwner(), item -> {
            binding.gameResultRecyclerView.setAdapter(new HighScoreListAdapter(item, new HighScoreListAdapter.ItemListener() {
                @Override
                public void onItemClicked(int index) {

                }

                @Override
                public void onItemLongClicked(int index) {
                    GameResult item =  ((HighScoreListAdapter) binding.gameResultRecyclerView.getAdapter()).itemAt(index);
                    highScoreViewModel.deleteItem(item);

                }
            }));
        });

        binding.gameResultRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
