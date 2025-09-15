package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivitySharepreferenceBinding;


public class SharepreferenceActivity extends AppCompatActivity {

    private ActivitySharepreferenceBinding binding;

    private SharedPreferences sharedPreferences;
    private static final String PREFS_NAME = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding  = ActivitySharepreferenceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("Share preference");
        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        // Initialize SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);

         // Save button
       binding.btnSave.setOnClickListener(v -> {
            String username = binding.edtUsername.getText().toString().trim();
            boolean remember = binding.chkRemember.isChecked();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", username);
            editor.putBoolean("rememberMe", remember);
            editor.apply(); // save asynchronously

            Toast.makeText(this, "Saved!", Toast.LENGTH_SHORT).show();
        });

        // Load button
        binding.btnLoad.setOnClickListener(v -> {
            String username = sharedPreferences.getString("username", "");
            boolean remember = sharedPreferences.getBoolean("rememberMe", false);

            binding.edtUsername.setText(username);
            binding.chkRemember.setChecked(remember);

            Toast.makeText(this, "Loaded!", Toast.LENGTH_SHORT).show();
        });

        // Auto-load if rememberMe is true
        boolean remember = sharedPreferences.getBoolean("rememberMe", false);
        if (remember) {
            binding.edtUsername.setText(sharedPreferences.getString("username", ""));
            binding.chkRemember.setChecked(true);
        }

        binding.clear.setOnClickListener(e->{
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove("username");
            editor.remove("rememberMe");
            editor.apply();
        });




    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }
}