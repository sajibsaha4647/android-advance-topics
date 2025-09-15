package com.example.myapplication;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.myapplication.Adaptar.TaskAdapter;
import com.example.myapplication.DBHelaper.DatabaseHelper;
import com.example.myapplication.databinding.ActivitySqliteBinding;
import com.example.myapplication.model.Task;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class SqliteActivity extends AppCompatActivity {


    private ActivitySqliteBinding binding;


 DatabaseHelper db;
    RecyclerView recyclerView;
    TaskAdapter adapter;
    ArrayList<Task> taskList;
    EditText editTextTask;
    ImageButton btnAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivitySqliteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
         ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
                    Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                    v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
                    return insets;
                });
        setTitle("Sqlite notepad");
        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

         db = new DatabaseHelper(this);
           editTextTask = findViewById(R.id.editTextTask);
        btnAdd = findViewById(R.id.btnAdd);
        recyclerView = findViewById(R.id.recyclerViewTasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
         taskList = (ArrayList<Task>) db.getAllTasks();
          adapter = new TaskAdapter(this, taskList, db);
           recyclerView.setAdapter(adapter);

           btnAdd.setOnClickListener(v -> {
            String taskName = editTextTask.getText().toString().trim();
            if (!taskName.isEmpty()) {
                // Insert into DB
                long id = db.insertTask(taskName, 1, "", 0); // 0 = not done

                // Add to list with the returned ID
                Task newTask = new Task((int) id, taskName, 1, "", 0);
                taskList.add(newTask);

                // Notify adapter
                adapter.notifyItemInserted(taskList.size() - 1);

                // Clear input
                editTextTask.setText("");
            }
        });




}


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }


}