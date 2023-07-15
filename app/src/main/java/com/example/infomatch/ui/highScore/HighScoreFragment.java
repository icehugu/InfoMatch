package com.example.infomatch.ui.highScore;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.example.infomatch.R;
import com.example.infomatch.data.GameResult;
import com.example.infomatch.databinding.FragmentHighscoreBinding;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Date;

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

                Navigation.findNavController(v).popBackStack();
            }
        });

        binding.screenshotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takeScreenshot();
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

    private void takeScreenshot() {
        Date now = new Date();
        android.text.format.DateFormat.format("yyyyMMddhhmmss", now);
        DateFormat dateFormat = android.text.format.DateFormat.getDateFormat(requireContext());
        DateFormat timeFormat = android.text.format.DateFormat.getTimeFormat(requireContext());

        try {
            
            String mPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/InfoMatch-Screenshots"  + "/" + dateFormat.format(now).replace("/",".") +
                    timeFormat.format(now).replace(":", ".")+ ".jpg";


            View v1 = requireActivity().getWindow().getDecorView().getRootView();

            Bitmap bitmap = Bitmap.createBitmap(v1.getMeasuredWidth(), v1.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            v1.draw(canvas);


            File imageFile = new File(mPath);

            FileOutputStream outputStream = new FileOutputStream(imageFile);
            int quality = 100;
            bitmap.compress(Bitmap.CompressFormat.JPEG, quality, outputStream);
            outputStream.flush();
            outputStream.close();

            shareScreenshot(imageFile);
        } catch (Throwable e) {

            e.printStackTrace();
        }
    }

    private void shareScreenshot(File imageFile) {

        Uri uri = FileProvider.getUriForFile(requireContext(), requireContext().getApplicationContext().getPackageName() + ".provider", imageFile);

        Intent shareIntent = new Intent();
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/jpg");
        startActivity(Intent.createChooser(shareIntent, "Share screenshot"));


    }
}
