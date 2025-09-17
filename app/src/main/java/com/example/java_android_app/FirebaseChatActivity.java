package com.example.java_android_app;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_android_app.databinding.ActivityFirebaseChatBinding;
import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;


public class FirebaseChatActivity extends AppCompatActivity {

    private ActivityFirebaseChatBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityFirebaseChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setTitle("Chat App");
        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

         // 🔹 Initialize Firebase
            FirebaseApp.initializeApp(this);

         // 🔹 Get the current FCM token
        FirebaseMessaging.getInstance().getToken()
            .addOnCompleteListener(task -> {
                if (!task.isSuccessful()) {
                    Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                    return;
                }

                // Get new FCM registration token
                String token = task.getResult();
                Log.d("TAG", "FCM Token: " + token);

                // 👉 Send this token to your server if needed
            });


  // Initialize Firebase
//    FirebaseApp.initializeApp(this);

    // Fetch token
//    com.google.firebase.messaging.FirebaseMessaging.getInstance().getToken()
//        .addOnCompleteListener(task -> {
//            if (!task.isSuccessful()) {
//                Log.w("TAG", "Fetching FCM token failed", task.getException());
//                return;
//            }
//            String token = task.getResult();
//            Log.d("TAG", "FCM Token: " + token);
//        });
    }





    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }
}