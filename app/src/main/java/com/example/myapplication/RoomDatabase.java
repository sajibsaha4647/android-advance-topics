package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.List;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.myapplication.Adaptar.CustomRoomAdapter;
import com.example.myapplication.HelperRoomDatabase.TaskDao;
import com.example.myapplication.HelperRoomDatabase.TaskDatabase;
import com.example.myapplication.databinding.ActivityRoomDatabaseBinding;
import com.example.myapplication.model.TaskModelRoom;


public class RoomDatabase extends AppCompatActivity {

    private ActivityRoomDatabaseBinding binding ;
      TaskDatabase db;
    List<TaskModelRoom> taskList;
    CustomRoomAdapter adapter;
     TaskDao taskDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        binding = ActivityRoomDatabaseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ViewCompat.setOnApplyWindowInsetsListener(binding.getRoot(), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setTitle("Room Database");
        // Enable the back/up button in the toolbar
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        binding.recyclerViewRoom.setLayoutManager(new LinearLayoutManager(this));
         db = TaskDatabase.getInstance(this);
         taskList = db.taskDao().getAllTasks();
         taskDao = db.taskDao();


        adapter = new CustomRoomAdapter(taskList, new CustomRoomAdapter.OnTaskActionListener() {
             @Override
             public void onDelete(TaskModelRoom task, int position) {
                taskDao.deleteTask(task);
                taskList.remove(position);
                 adapter.notifyItemRemoved(position);
             }

             @Override
             public void onEdit(TaskModelRoom task, int position) {
                showEditDialog(task, position); // custom dialog
             }
         });

            binding.recyclerViewRoom.setAdapter(adapter);


         binding.fabAdd.setOnClickListener(e->{
           View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_task, null);
            EditText editTextTask = dialogView.findViewById(R.id.editTextTaskDialog);
            editTextTask.setText("");
            new AlertDialog.Builder(this)
                .setTitle("add Task")
                .setView(dialogView)
                .setPositiveButton("Add", (d, which) -> {
                    String newText = editTextTask.getText().toString().trim();
                     if (!newText.isEmpty()) {
                    // 1. Create new Task object
                    TaskModelRoom newTask = new TaskModelRoom(newText, 1, "2025-09-15", false);

                    // 2. Insert into Room DB
                    taskDao.insertTask(newTask);

                    // 3. Refresh list from DB
                    taskList.clear();
                    taskList.addAll(taskDao.getAllTasks());

                         Toast.makeText(this, "Added successfully", Toast.LENGTH_SHORT).show();

                    // 4. Notify adapter
//                    adapter.updateTasks(taskList);
                    adapter.notifyDataSetChanged();
                }

                })
                .setNegativeButton("Cancel", null)
                .show();

         });





    }

    private void showEditDialog(TaskModelRoom task, int position) {
        View dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_edit_task, null);
        EditText editTextTask = dialogView.findViewById(R.id.editTextTaskDialog);
        editTextTask.setText(task.getTask());

        new AlertDialog.Builder(this)
                .setTitle("Edit Task")
                .setView(dialogView)
                .setPositiveButton("Update", (d, which) -> {
                    String newText = editTextTask.getText().toString().trim();
                    if (!newText.isEmpty()) {
                        task.setTask(newText);
                        taskDao.updateTask(task);
                        taskList.set(position, task);
                        adapter.notifyItemChanged(position);
                    }
                })
                .setNegativeButton("Cancel", null)
                .show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed(); // Go back when button is clicked
        return true;
    }
}