package com.example.infomatch.ui.highScore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.infomatch.data.GameResult;
import com.example.infomatch.databinding.HighscoreItemBinding;
import java.util.List;

public class HighScoreListAdapter extends RecyclerView.Adapter<HighScoreListAdapter.HighScoreViewHolder> {


    interface ItemListener{

        public void onItemLongClicked(int index);
    }
    private List<GameResult> items;
    public ItemListener callback;
    public HighScoreListAdapter(List<GameResult> items, ItemListener callback) {
        super();
        this.items = items;
        this.callback = callback;
    }

    class HighScoreViewHolder extends RecyclerView.ViewHolder implements View.OnLongClickListener {

        private final TextView username;
        private final TextView dateAndTime;
        private final TextView score;
        private final TextView elapsedTime;
        private final TextView pairsFound;

        private HighScoreViewHolder(HighscoreItemBinding binding) {
            super(binding.getRoot());

            username = binding.username;
            dateAndTime = binding.timeAndDate;
            score = binding.score;
            elapsedTime = binding.elapsedTime;
            pairsFound = binding.pairsfound;
            binding.getRoot().setOnLongClickListener(this);
        }

        public void bind(GameResult gameResult) {
            username.setText(gameResult.getName());
            dateAndTime.setText(gameResult.getDateAndTime());
            score.setText(String.valueOf(gameResult.getScore()));
            elapsedTime.setText(String.valueOf(gameResult.getTime()));
            pairsFound.setText(String.valueOf(gameResult.getNumOfPairs()));
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