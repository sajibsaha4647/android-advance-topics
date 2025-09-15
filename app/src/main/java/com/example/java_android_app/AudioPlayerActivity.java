package com.example.java_android_app;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.SeekBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_android_app.databinding.ActivityAudioPlayerBinding;


public class AudioPlayerActivity extends AppCompatActivity {

    private ActivityAudioPlayerBinding binding ;
    private MediaPlayer mediaPlayer;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityAudioPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setTitle("Audio player");

        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);

//            getSupportActionBar().setHomeAsUpIndicator(true); // optional custom icon
        }

        mediaPlayer = MediaPlayer.create(this,R.raw.aidio);
        binding.seekBar.setMax(mediaPlayer.getDuration());

        Runnable updateSeekbar  = new Runnable() {
            @Override
            public void run() {
                if (mediaPlayer != null) {
                    binding.seekBar.setProgress(mediaPlayer.getCurrentPosition());
                    handler.postDelayed(this, 500); // update every 0.5 second
                }
            }
        };
        handler.post(updateSeekbar);

        binding.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        binding.btnPause.setOnClickListener(e->{
            if (!mediaPlayer.isPlaying()) {
                mediaPlayer.start();

            }
            Log.d("TAG", "onCreate: "+mediaPlayer.isPlaying());
        });

        binding.btnPlay.setOnClickListener(e->{
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();

            }
            Log.d("TAG", "onCreate: "+mediaPlayer.isPlaying());
        });

        binding.btnStop.setOnClickListener(e->{
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.stop();
                mediaPlayer = MediaPlayer.create(this, R.raw.aidio); // reset for next play
            }
            Log.d("TAG", "onCreate: "+mediaPlayer.isPlaying());
        });
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacksAndMessages(null);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }
}