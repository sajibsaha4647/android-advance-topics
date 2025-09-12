package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.myapplication.databinding.ActivityMainBinding;


public class MainActivity extends AppCompatActivity {

        private ActivityMainBinding binding ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        binding.AboutUsMenu.setOnClickListener(e->{
            Intent intent = new Intent(MainActivity.this, AboutUsMenuActivity.class);
            startActivity(intent);
            intent.putExtra("title","About us");
        });

        binding.ActivityLifeCycle.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ActivityLIfecycle.class);
            startActivity(intent);
            intent.putExtra("title","Activity lifecycle");
        });

        binding.ActivityResult.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ActivityResult.class);
            startActivityForResult(intent, 100); // requestCode = 100(intent);
            intent.putExtra("title","Activity result");
        });

        binding.ActivityPassDataToAnother.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ActivitySendDataToAnother.class);
            startActivity(intent);
            intent.putExtra("title","Activity share data");
        });

        binding.ApiModelRetrofit.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ApiAndModelWithRetrofit.class);
            startActivity(intent);
            intent.putExtra("title","Network api retrofit");
        });

         binding.AudioPlayer.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, AudioPlayerActivity.class);
            startActivity(intent);
            intent.putExtra("title","Audio player");
        });

        binding.AutoCompleteTextview.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, AutocompleteActivity.class);
            startActivity(intent);
            intent.putExtra("title","AutoComplete view");
        });

        binding.ExpandableListView.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ExpandableListViewActivity.class);
            startActivity(intent);
            intent.putExtra("title","Expandable Listview");
        });

        binding.FeedBackMenu.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, FeedBackMenuActivity.class);
            startActivity(intent);
            intent.putExtra("title","FeedBack menu");
        });

        binding.FireBaseChatApp.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, FirebaseChatActivity.class);
            startActivity(intent);
            intent.putExtra("title","Firebase chatApp");
        });

         binding.FragmentActivity.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, FragmentActivity.class);
            startActivity(intent);
            intent.putExtra("title","Fragment View");
        });

        binding.FullScreenActivity.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, FullscreenActivity.class);
            startActivity(intent);
            intent.putExtra("title","Fullscreen View");
        });

        binding.GridViewActivity.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, GridViewInfo.class);
            startActivity(intent);
            intent.putExtra("title","GridView");
        });

        binding.ActivityListView.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ListViewInfo.class);
            startActivity(intent);
            intent.putExtra("title","Activity Listview");
        });

        binding.MediaPlayer.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, MediaPlayerActivity.class);
            startActivity(intent);
            intent.putExtra("title","MediaPlayer");
        });

         binding.ActivityMenu.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, MenuViewInfo.class);
            startActivity(intent);
            intent.putExtra("title","Activity Menu");
        });

        binding.ProgressBar.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, Progressbarinfo.class);
            startActivity(intent);
            intent.putExtra("title","ProgressBar");
        });

        binding.ActivitySearch.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, SearchViewInfo.class);
            startActivity(intent);
            intent.putExtra("title","Activity search");
        });

        binding.ActivityShareBtn.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ShareMenuActivity.class);
            startActivity(intent);
            intent.putExtra("title","share social media");
        });

        binding.ActivitySharepreference.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, SharepreferenceActivity.class);
            startActivity(intent);
            intent.putExtra("title","Share-preference");
        });

         binding.ActivitySpinner.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, SpinnerInfo.class);
            startActivity(intent);
            intent.putExtra("title","Activity Spinner");
        });

        binding.SplashScreen.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, SplashScreenActivity.class);
            startActivity(intent);
            intent.putExtra("title","SPlash screen");
        });

        binding.SqliteBtn.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, SqliteActivity.class);
            startActivity(intent);
            intent.putExtra("title","Sqlite NotePad");
        });


        binding.RoomDatabase.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, RoomDatabase.class);
            startActivity(intent);
            intent.putExtra("title","RoomDatabase NotePad");
        });

        binding.ThreadsMultiThreads.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, ThreadsWithMultiThreads.class);
            startActivity(intent);
            intent.putExtra("title","Thread with MultiThread");
        });

        binding.SideDraweWithBottomNav.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, SideDraweWithBottomNav.class);
            startActivity(intent);
            intent.putExtra("title","SideDrawerWithBottomNav");
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            assert data != null;
            String value = data.getStringExtra("result_key");
            Toast.makeText(this, value, Toast.LENGTH_LONG).show();
            Log.d("TAG", "onActivityResult: " + value);

        }
    }


    @Override
    public void onBackPressed() {
        // Show a confirmation dialog before exiting
        new AlertDialog.Builder(this)
                .setTitle("Exit App")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", (dialog, which) -> super.onBackPressed())
                .setNegativeButton("No", null)
                .show();
    }
}