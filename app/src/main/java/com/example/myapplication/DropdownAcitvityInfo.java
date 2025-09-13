package com.example.myapplication;

import android.os.Bundle;


import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.navigation.ui.AppBarConfiguration;

import com.example.myapplication.databinding.ActivityDropdownAcitvityInfoBinding;


public class DropdownAcitvityInfo extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityDropdownAcitvityInfoBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityDropdownAcitvityInfoBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("dropdown activity");



        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

//            getSupportActionBar().setHomeAsUpIndicator(true); // optional custom icon
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }


}