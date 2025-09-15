package com.example.java_android_app;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_android_app.databinding.ActivityMediaPlayerBinding;

import java.util.concurrent.atomic.AtomicReference;


public class MediaPlayerActivity extends AppCompatActivity {

    private ActivityMediaPlayerBinding binding ;
    private MediaPlayer mediaPlayer;
     private Handler handler = new Handler();
    private String audioUrl = "https://www.example.com/song.mp3"; // your server URL
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityMediaPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setTitle("Media player");
        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

       AtomicReference<MediaPlayer> mediaPlayer = new AtomicReference<>(MediaPlayer.create(this, R.raw.aidio));

binding.seekBar.setMax(mediaPlayer.get().getDuration());

binding.btnPlay.setOnClickListener(v -> mediaPlayer.get().start());
binding.btnPause.setOnClickListener(v -> mediaPlayer.get().pause());
binding.btnStop.setOnClickListener(v -> {
    mediaPlayer.get().stop();
    mediaPlayer.set(MediaPlayer.create(this, R.raw.aidio)); // recreate after stop
    binding.seekBar.setProgress(0);
});

        // SeekBar change listener
        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser && mediaPlayer.get() != null) {
                    mediaPlayer.get().seekTo(progress);
                }
            }

            @Override public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override public void onStopTrackingTouch(SeekBar seekBar) { }
        });

    Runnable updateSeekbar  = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer.get() != null) {
                    binding.seekBar.setProgress(mediaPlayer.get().getCurrentPosition());
                    handler.postDelayed(this, 500); // update every 0.5 second
                }
            }
        };
        handler.post(updateSeekbar);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }
}