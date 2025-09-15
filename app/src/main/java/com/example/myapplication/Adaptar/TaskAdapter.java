package com.example.myapplication.Adaptar;
import java.util.List;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.DBHelaper.DatabaseHelper;
import com.example.myapplication.R;
import com.example.myapplication.model.Task;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

     private Context context;
    private ArrayList<Task> taskList;
    private DatabaseHelper db;

    public TaskAdapter(Context context, ArrayList<Task> taskList, DatabaseHelper db) {
        this.context = context;
        this.taskList = taskList;
        this.db = db;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view = LayoutInflater.from(context).inflate(R.layout.item_task,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Task task = taskList.get(position);
            holder.textViewTask.setText(task.getTask());
           holder.btnDelete.setOnClickListener(v -> {
    int pos = holder.getAdapterPosition(); // current valid position
    if (pos != RecyclerView.NO_POSITION) {
        db.deleteTask(taskList.get(pos).getId()); // delete from DB
        taskList.remove(pos);                     // remove from list
        notifyItemRemoved(pos);                   // notify adapter
        notifyItemRangeChanged(pos, taskList.size()); // optional to refresh remaining items

    }
});


    holder.btnEdit.setOnClickListener(e->{
        View dialogView =
        LayoutInflater.from(holder.itemView.getContext()).inflate(R.layout.dialog_edit_task,null);
         EditText editTextTask = dialogView.findViewById(R.id.editTextTaskDialog);
        editTextTask.setText(task.getTask());

        new AlertDialog.Builder(holder.itemView.getContext())
            .setTitle("Edit Task")
            .setView(dialogView)
            .setPositiveButton("Update", (dialog, which) -> {
                String updatedTask = editTextTask.getText().toString().trim();
                if (!updatedTask.isEmpty()) {
                    task.setTask(updatedTask);
                    db.updateTask(task.getId(), updatedTask, task.getPriority(), task.getDueDate(), task.getIsDone());
                    notifyItemChanged(position);
                }
            })
            .setNegativeButton("Cancel", null)
            .show();
    });


    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{

             TextView textViewTask;
        ImageButton btnEdit, btnDelete;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                textViewTask = itemView.findViewById(R.id.textViewTask);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            }
        }
}
