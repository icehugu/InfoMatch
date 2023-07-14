package com.example.infomatch.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import com.example.infomatch.R;
import java.io.File;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final File newFolder = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) + "/InfoMatch-Screenshots");

        try {
            if (newFolder.mkdir()) {
                Log.d("mkdir", "created a folder successfully");
            } else if (newFolder.exists()) {
                Log.d("mkdir", "folder already exists");
            } else {
                Log.d("mkdir", "error creating a folder");
            }
        } catch (SecurityException e) {
            Log.d("exception", e.getMessage());


        }
    }
}