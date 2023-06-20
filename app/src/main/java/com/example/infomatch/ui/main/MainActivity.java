package com.example.infomatch.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.infomatch.R;
import com.example.infomatch.databinding.ActivityMainBinding;
import com.example.infomatch.ui.mainManu.MainManuFragment;
import com.example.infomatch.ui.mainManu.MainManuViewModel;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Button mButton = binding.enterButton;
        mainMenuViewModel = new ViewModelProvider(this).get(MainManuViewModel.class);

        final androidx.fragment.app
                .FragmentManager mFragmentManager
                = getSupportFragmentManager();
        final androidx.fragment.app
                .FragmentTransaction mFragmentTransaction
                = mFragmentManager.beginTransaction();
        final MainManuFragment mFragment
                = new MainManuFragment();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(binding.userName.getText().toString() != null) {
                    mainMenuViewModel.username = binding.userName.getText().toString();
                }
//                Bundle mBundle = new Bundle();
//                mBundle.putString("mText", binding.userName.getText().toString());
//                mFragment. = mBundle;
//                mFragmentTransaction.add(R.id.frameLayout, mFragment).commit();
            }
        });






    }
}