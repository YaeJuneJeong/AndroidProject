package com.codesample.note;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import com.codesample.note.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.eventButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                binding.manuText.setVisibility(View.INVISIBLE);
                binding.locationText.setVisibility(View.INVISIBLE);
                binding.eventText.setVisibility(View.VISIBLE);
            }


        });
        binding.locationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.manuText.setVisibility(View.INVISIBLE);
                binding.locationText.setVisibility(View.VISIBLE);
                binding.eventText.setVisibility(View.INVISIBLE);

            }
        });
        binding.manuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.manuText.setVisibility(View.VISIBLE);
                binding.locationText.setVisibility(View.INVISIBLE);
                binding.eventText.setVisibility(View.INVISIBLE);
            }
        });
    }




}
