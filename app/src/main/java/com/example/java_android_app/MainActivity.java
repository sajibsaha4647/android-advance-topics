package com.example.java_android_app;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.PowerManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.java_android_app.databinding.ActivityMainBinding;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;


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

//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
//    Intent intent = new Intent();
//    String packageName = getPackageName();
//    PowerManager pm = (PowerManager) getSystemService(POWER_SERVICE);
//    if (!pm.isIgnoringBatteryOptimizations(packageName)) {
//        intent.setAction(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS);
//        startActivity(intent);
//    }
//}

FirebaseApp.initializeApp(this);

FirebaseMessaging.getInstance().setAutoInitEnabled(true);

       FirebaseApp app = FirebaseApp.getInstance();
Log.d("TAG", "FirebaseApp name: " + app.getName());


boolean available = FirebaseMessaging.getInstance() != null;
Log.d("TAG", "FirebaseMessaging instance available? " + available);
        // Initialize Firebase
        FirebaseApp.initializeApp(this);







        binding.AboutUsMenu.setOnClickListener(e->{

        FirebaseApp app1 = FirebaseApp.getInstance();
Log.d("TAG", "FirebaseApp name: " + app1.getName());


    boolean available1 = FirebaseMessaging.getInstance() != null;
    Log.d("TAG", "FirebaseMessaging instance available? " + available1);

    boolean available3 = GoogleApiAvailability.getInstance()
        .isGooglePlayServicesAvailable(this) == ConnectionResult.SUCCESS;
    Log.d("TAG", "Play Services available? " + available3);

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



FirebaseMessaging.getInstance().getToken()
    .addOnSuccessListener(token -> Log.d("TAG", "Token fetched: " + token))
    .addOnFailureListener(p -> Log.e("TAG", "Token fetch failed", p));



        Log.d("TAG", "press onComplete");
        FirebaseMessaging.getInstance().getToken()
    .addOnCompleteListener(task -> {
        Log.d("TAG", "Inside onComplete");
        if (!task.isSuccessful()) {
            Log.w("TAG", "Token fetch failed", task.getException());
            return;
        }
        Log.d("TAG", "FCM Token: " + task.getResult());
    });
//            Intent intent = new Intent(MainActivity.this, AboutUsMenuActivity.class);
//            startActivity(intent);
//            intent.putExtra("title","About us");
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
            intent.putExtra("title","Activity share data");
         startActivity(intent);

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

         binding.DropdownView.setOnClickListener(e->{
         Intent intent = new Intent(MainActivity.this, DropdownAcitvityInfo.class);
            startActivity(intent);
            intent.putExtra("title","Dropdown View");
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

//         binding.ActivityMenu.setOnClickListener(e->{
//         Intent intent = new Intent(MainActivity.this, MenuViewInfo.class);
//            startActivity(intent);
//            intent.putExtra("title","Activity Menu");
//        });

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

//         binding.ActivitySpinner.setOnClickListener(e->{
//         Intent intent = new Intent(MainActivity.this, SpinnerInfo.class);
//            startActivity(intent);
//            intent.putExtra("title","Activity Spinner");
//        });

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

//    private void diagnoseFcmIssue() {
//    Log.d("FCM_DIAGNOSE", "=== FCM DIAGNOSIS START ===");
//
//    try {
//        // Check Firebase App configuration
//        FirebaseApp app = FirebaseApp.getInstance();
//        Log.d("FCM_DIAGNOSE", "FirebaseApp: " + app.getName());
//
//        // Check all options
//        FirebaseOptions options = app.getOptions();
//        Log.d("FCM_DIAGNOSE", "Project ID: " + options.getProjectId());
//        Log.d("FCM_DIAGNOSE", "Application ID: " + options.getApplicationId());
//        Log.d("FCM_DIAGNOSE", "API Key: " + options.getApiKey());
//        Log.d("FCM_DIAGNOSE", "Database URL: " + options.getDatabaseUrl());
//        Log.d("FCM_DIAGNOSE", "Storage Bucket: " + options.getStorageBucket());
//        Log.d("FCM_DIAGNOSE", "GCM Sender ID: " + options.getGcmSenderId());
//
//        // Check if FCM is properly initialized
//        FirebaseMessaging messaging = FirebaseMessaging.getInstance();
//        Log.d("FCM_DIAGNOSE", "Messaging instance: " + messaging);
//
//        // Check auto-init status
//        boolean autoInit = messaging.isAutoInitEnabled();
//        Log.d("FCM_DIAGNOSE", "Auto-init enabled: " + autoInit);
//
//        // Check network connectivity
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
//        Log.d("FCM_DIAGNOSE", "Network connected: " + isConnected);
//
//        // Check Google Play Services
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int resultCode = apiAvailability.isGooglePlayServicesAvailable(this);
//        Log.d("FCM_DIAGNOSE", "Play Services result: " + resultCode);
//        Log.d("FCM_DIAGNOSE", "Play Services available: " + (resultCode == ConnectionResult.SUCCESS));
//
//        // Check if we can force a token refresh
//        checkTokenViaAlternativeMethods();
//
//    } catch (Exception e) {
//        Log.e("FCM_DIAGNOSE", "Diagnosis failed", e);
//    }
//
//    Log.d("FCM_DIAGNOSE", "=== FCM DIAGNOSIS END ===");
//}
//
//private void checkTokenViaAlternativeMethods() {
//    Log.d("FCM_DIAGNOSE", "Trying alternative token methods...");
//
//    // Method 1: Using CompletableFuture for better control
//    try {
//        Task<String> tokenTask = FirebaseMessaging.getInstance().getToken();
//        Log.d("FCM_DIAGNOSE", "Token task created: " + tokenTask);
//        Log.d("FCM_DIAGNOSE", "Task complete: " + tokenTask.isComplete());
//        Log.d("FCM_DIAGNOSE", "Task successful: " + tokenTask.isSuccessful());
//
//        if (tokenTask.isComplete() && !tokenTask.isSuccessful()) {
//            Exception ex = tokenTask.getException();
//            Log.e("FCM_DIAGNOSE", "Task failed with exception: " + ex.getMessage(), ex);
//        }
//    } catch (Exception e) {
//        Log.e("FCM_DIAGNOSE", "Alternative method failed", e);
//    }
//}
//
//
//    private void debugFcmSetup() {
//    try {
//        Log.d("FCM_DEBUG", "=== FCM SETUP DEBUG ===");
//
//        // Check Firebase app
//        FirebaseApp app = FirebaseApp.getInstance();
//        Log.d("FCM_DEBUG", "FirebaseApp: " + app.getName());
//        Log.d("FCM_DEBUG", "Project ID: " + app.getOptions().getProjectId());
//        Log.d("FCM_DEBUG", "Application ID: " + app.getOptions().getApplicationId());
//
//        // Check Play Services
//        GoogleApiAvailability apiAvailability = GoogleApiAvailability.getInstance();
//        int playServicesStatus = apiAvailability.isGooglePlayServicesAvailable(this);
//        Log.d("FCM_DEBUG", "Play Services status: " + playServicesStatus);
//        Log.d("FCM_DEBUG", "Play Services available: " +
//            (playServicesStatus == ConnectionResult.SUCCESS));
//
//        if (playServicesStatus != ConnectionResult.SUCCESS) {
//            Log.d("FCM_DEBUG", "Play Services error: " +
//                apiAvailability.getErrorString(playServicesStatus));
//        }
//
//        // Check network connectivity
//        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
//        boolean isConnected = activeNetwork != null && ((NetworkInfo) activeNetwork).isConnectedOrConnecting();
//        Log.d("FCM_DEBUG", "Network connected: " + isConnected);
//
//        // Check if FirebaseMessaging is available
//        boolean fcmAvailable = FirebaseMessaging.getInstance() != null;
//        Log.d("FCM_DEBUG", "FCM Instance available: " + fcmAvailable);
//
//        Log.d("FCM_DEBUG", "=== END FCM DEBUG ===");
//
//    } catch (Exception e) {
//        Log.e("FCM_DEBUG", "Debug setup failed", e);
//    }
//}
//
//
//private void getFirebaseToken() {
//    Log.d("FCM_DEBUG", "getFirebaseToken() method called");
//
//    // Set a timeout to detect if task hangs
//    Handler handler = new Handler(Looper.getMainLooper());
//    Runnable timeoutRunnable = new Runnable() {
//        @Override
//        public void run() {
//            Log.e("FCM_ERROR", "Token retrieval timeout - task never completed");
//            Toast.makeText(MainActivity.this, "Token retrieval timed out", Toast.LENGTH_SHORT).show();
//        }
//    };
//
//    handler.postDelayed(timeoutRunnable, 10000); // 10 second timeout
//
//    try {
//        FirebaseMessaging.getInstance().getToken()
//            .addOnCompleteListener(task -> {
//                // Remove timeout check since we got a response
//                handler.removeCallbacks(timeoutRunnable);
//
//                Log.d("FCM_DEBUG", "Token task completed, successful: " + task.isSuccessful());
//
//                if (task.isSuccessful()) {
//                    String token = task.getResult();
//                    Log.d("FCM_TOKEN", "Token retrieved: " + token);
//
//                    // Show only first few characters to avoid Toast overflow
//                    String tokenPreview = token.length() > 20 ?
//                        token.substring(0, 20) + "..." : token;
//                    Toast.makeText(this, "Token: " + tokenPreview, Toast.LENGTH_SHORT).show();
//
//                    // Store token for later use
////                    storeFCMToken(token);
//                } else {
//                    Exception exception = task.getException();
//                    Log.e("FCM_ERROR", "Token retrieval failed", exception);
//
//                    if (exception != null) {
//                        Log.e("FCM_ERROR", "Exception message: " + exception.getMessage());
//                        Log.e("FCM_ERROR", "Exception type: " + exception.getClass().getName());
//                        // Handle specific errors
////                        handleTokenError(exception);
//                    }
//                }
//            })
//            .addOnFailureListener(e -> {
//                handler.removeCallbacks(timeoutRunnable);
//                Log.e("FCM_ERROR", "Token retrieval failed with exception", e);
//                Toast.makeText(this, "Failed to get token: " + e.getMessage(),
//                    Toast.LENGTH_SHORT).show();
//            });
//    } catch (Exception e) {
//        handler.removeCallbacks(timeoutRunnable);
//        Log.e("FCM_ERROR", "Unexpected error in getFirebaseToken", e);
//    }
//}
//
//private void getFirebaseTokenAlternative() {
//    Log.d("FCM_DEBUG", "Trying alternative token retrieval method");
//
//    try {
//        Task<String> tokenTask = FirebaseMessaging.getInstance().getToken();
//
//        // Check if task is complete immediately
//        if (tokenTask.isComplete()) {
//            Log.d("FCM_DEBUG", "Task already complete");
//            if (tokenTask.isSuccessful()) {
//                String token = tokenTask.getResult();
//                Log.d("FCM_TOKEN", "Token: " + token);
//            } else {
//                Log.e("FCM_ERROR", "Task failed: " + tokenTask.getException());
//            }
//            return;
//        }
//
//        // Add listeners with explicit logging
//        tokenTask.addOnSuccessListener(token -> {
//            Log.d("FCM_TOKEN", "Success! Token: " + token);
//            Toast.makeText(this, "Token retrieved", Toast.LENGTH_SHORT).show();
//        });
//
//        tokenTask.addOnFailureListener(e -> {
//            Log.e("FCM_ERROR", "Failure: " + e.getMessage(), e);
//        });
//
//        tokenTask.addOnCompleteListener(task -> {
//            Log.d("FCM_DEBUG", "Task complete: " + task.isSuccessful());
//        });
//
//    } catch (Exception e) {
//        Log.e("FCM_ERROR", "Alternative method failed", e);
//    }
//}
//
//
//private void checkFirebaseDependencies() {
//    Log.d("FCM_DEBUG", "=== FIREBASE DEPENDENCY CHECK ===");
//
//    try {
//        // Check Firebase initialization
//        FirebaseApp.initializeApp(this);
//        FirebaseApp app = FirebaseApp.getInstance();
//
//        Log.d("FCM_DEBUG", "FirebaseApp: " + app.getName());
//        Log.d("FCM_DEBUG", "Project ID: " + app.getOptions().getProjectId());
//        Log.d("FCM_DEBUG", "Application ID: " + app.getOptions().getApplicationId());
//
//        // Check FCM instance
//        boolean fcmAvailable = FirebaseMessaging.getInstance() != null;
//        Log.d("FCM_DEBUG", "FCM Instance available: " + fcmAvailable);
//
//        // Check auto-init
//        boolean autoInitEnabled = FirebaseMessaging.getInstance().isAutoInitEnabled();
//        Log.d("FCM_DEBUG", "FCM Auto-init enabled: " + autoInitEnabled);
//
//        Log.d("FCM_DEBUG", "=== END DEPENDENCY CHECK ===");
//
//    } catch (Exception e) {
//        Log.e("FCM_ERROR", "Dependency check failed", e);
//        Toast.makeText(this, "Firebase setup error: " + e.getMessage(), Toast.LENGTH_LONG).show();
//    }
//}




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