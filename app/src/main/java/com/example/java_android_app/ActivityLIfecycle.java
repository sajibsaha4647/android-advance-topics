package com.example.java_android_app;

import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_android_app.databinding.ActivityLifecycleBinding;


public class ActivityLIfecycle extends AppCompatActivity {

    private ActivityLifecycleBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityLifecycleBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Toast.makeText(this,"oncreate",Toast.LENGTH_LONG).show();

        setTitle("ActivityLife");


        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

//            getSupportActionBar().setHomeAsUpIndicator(true); // optional custom icon
        }
    }

    @Override
    protected void onStart() {
        Toast.makeText(this,"onStart",Toast.LENGTH_LONG).show();
        super.onStart();
    }

    @Override
    protected void onResume() {
        Toast.makeText(this,"onResume",Toast.LENGTH_LONG).show();
        super.onResume();
    }

    @Override
    protected void onStop() {
        Toast.makeText(this,"onStop",Toast.LENGTH_LONG).show();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Toast.makeText(this,"onDestroy",Toast.LENGTH_LONG).show();
        super.onDestroy();
    }

    @Override
    protected void onRestart() {
        Toast.makeText(this,"onRestart",Toast.LENGTH_LONG).show();
        super.onRestart();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }
}