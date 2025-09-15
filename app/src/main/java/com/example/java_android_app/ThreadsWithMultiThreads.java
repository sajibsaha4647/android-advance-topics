package com.example.java_android_app;

import android.os.Bundle;
import android.util.Log;

import com.example.java_android_app.databinding.ActivityThreadsWithMultiThreadsBinding;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class ThreadsWithMultiThreads extends AppCompatActivity {


    private ActivityThreadsWithMultiThreadsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityThreadsWithMultiThreadsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("Thread in java");

        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    binding.startThreadBtn.setOnClickListener(v -> startBackgroundTask());


    }

private void startBackgroundTask() {
    Log.d("TAG", "startBackgroundTask: ");

    new Thread(() -> {
        for (int i = 1; i <= 10; i++) {
            try {
                Thread.sleep(1000); // wait 1 second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Log.d("TAG", "startBackgroundTask: " + i);

            int progress = i;

            // Update UI safely from background
            runOnUiThread(() -> {
                binding.textView.setText("Progress: " + progress + "/10");
            });
        }

        // Final update after task is done
        runOnUiThread(() -> {
            binding.textView.setText("Task Completed ✅");
        });
    }).start(); // <-- IMPORTANT
}




    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }

}