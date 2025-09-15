package com.example.java_android_app;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_android_app.databinding.ActivityResultBinding;


public class ActivityResult extends AppCompatActivity {

    private ActivityResultBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityResultBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("Activity Result");

        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

//            getSupportActionBar().setHomeAsUpIndicator(true); // optional custom icon
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        Intent resultIntent = new Intent();
        resultIntent.putExtra("result_key", "Some data from this activity");
        setResult(RESULT_OK, resultIntent);

        Log.d("TAG","press on it00000");

        // Finish activity
        finish();
//        onBackPressed(); // Go back when button is clicked
        return true;
    }
}