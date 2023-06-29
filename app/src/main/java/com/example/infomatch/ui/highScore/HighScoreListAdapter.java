package com.example.infomatch.ui.highScore;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.infomatch.data.GameResult;
import com.example.infomatch.databinding.HighscoreItemBinding;

import java.util.List;

public class HighScoreListAdapter extends RecyclerView.Adapter<HighScoreListAdapter.HighScoreViewHolder> {


    interface ItemListener{
        public void onItemClicked(int index);
        public void onItemLongClicked(int index);
    }
    private List<GameResult> items;
    public ItemListener callback;
    public HighScoreListAdapter(List<GameResult> items, ItemListener callback) {
        super();
        this.items = items;
        this.callback = callback;
    }

    class HighScoreViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private final TextView username;
        private final TextView dateAndTime;
        private final TextView score;
        private final TextView elaspedTime;
        private final TextView pairsFound;

        private HighScoreViewHolder(HighscoreItemBinding binding) {
            super(binding.getRoot());

            username = binding.username;
            dateAndTime = binding.timeAndDate;
            score = binding.score;
            elaspedTime = binding.elapsedTime;
            pairsFound = binding.pairsfound;
            binding.getRoot().setOnClickListener(this);
            binding.getRoot().setOnLongClickListener(this);
        }

        public void bind(GameResult gameResult) {
            username.setText(gameResult.getName());
            dateAndTime.setText(gameResult.getDateAndTime());
            score.setText(String.valueOf(gameResult.getScore()));
            elaspedTime.setText(String.valueOf(gameResult.getTime()));
            pairsFound.setText(String.valueOf(gameResult.getNumOfPairs()));
        }

        @Override
        public void onClick(View v) {
            callback.onItemClicked(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            callback.onItemLongClicked(getAdapterPosition());
            return true;
        }

    }
    @Override
    public HighScoreViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new HighScoreViewHolder(HighscoreItemBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void onBindViewHolder(HighScoreViewHolder holder, int position) {
        GameResult current = items.get(position);
        holder.bind(current);
    }

    public GameResult itemAt(int position) {
        return items.get(position);
    }


}