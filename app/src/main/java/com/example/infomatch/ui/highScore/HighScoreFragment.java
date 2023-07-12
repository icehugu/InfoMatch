package com.example.infomatch.ui.highScore;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.infomatch.R;
import com.example.infomatch.data.GameResult;
import com.example.infomatch.databinding.FragmentHighscoreBinding;
import com.example.infomatch.databinding.FragmentMainmanuBinding;

public class HighScoreFragment extends Fragment {

    private FragmentHighscoreBinding binding;
    private HighScoreViewModel highScoreViewModel;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHighscoreBinding.inflate(inflater, container, false);
        View view = binding.getRoot();
        highScoreViewModel = new ViewModelProvider(this).get(HighScoreViewModel.class);
        binding.userName.setText(getArguments().getString("userName")+"\n"+getResources().getString(R.string.to_delete_a_score_do_a_long_press));
        binding.backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("MainManuFragment", "click");

                Navigation.findNavController(v).popBackStack();
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
