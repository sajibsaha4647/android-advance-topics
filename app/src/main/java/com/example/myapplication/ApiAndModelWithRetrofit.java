package com.example.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.Network.ApiService;
import com.example.myapplication.Network.RetrofitClient;
import com.example.myapplication.databinding.ActivityApiAndModelWithRetrofitBinding;
import com.example.myapplication.model.PostModel;
import com.example.myapplication.utils.NetworkUtils;

import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ApiAndModelWithRetrofit extends AppCompatActivity {

    private ActivityApiAndModelWithRetrofitBinding binding ;
    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityApiAndModelWithRetrofitBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("Retro fit api call");

        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        getObjectPosts(this,apiService);
    }

    private void getObjectPosts(Context context, ApiService apiService) {
        if (!NetworkUtils.isNetworkAvailable(this)) {
            Toast.makeText(this, "No Internet Connection", Toast.LENGTH_SHORT).show();
            return;
        }

        apiService = RetrofitClient.getClient().create(ApiService.class);
        Call<PostModel> call = apiService.getPost();
        call.enqueue(new Callback<PostModel>() {
            @Override
            public void onResponse(Call<PostModel> call, Response<PostModel> response) {
                PostModel model = response.body();
                if (model != null && model.title != null) {
                    binding.tvTitle.setText(model.title);
                    binding.tvTitle.setText(model.body);
                    // This works because Callback is an inner class of MainActivity
                    Toast.makeText(context, "Title: " + model.title, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Response body is null", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<PostModel> call, Throwable t) {
                Toast.makeText(context, "Failure: " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }
}