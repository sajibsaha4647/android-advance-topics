package com.example.java_android_app.Adaptar;

import android.view.LayoutInflater;
import java.util.List;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.java_android_app.databinding.RoomDbItemListsBinding;
import com.example.java_android_app.model.TaskModelRoom;

public class CustomRoomAdapter extends RecyclerView.Adapter<CustomRoomAdapter.ViewHolder>{
    private List<TaskModelRoom> taskList;
    private final OnTaskActionListener listener;

    public CustomRoomAdapter(List<TaskModelRoom> taskList, OnTaskActionListener listener) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        RoomDbItemListsBinding binding = RoomDbItemListsBinding.inflate(inflater,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TaskModelRoom task = taskList.get(position);
        holder.binding.textViewTaskRoom.setText(task.getTask());
        holder.binding.btnDeleteRoom.setOnClickListener(v -> {
            listener.onDelete(task, position);
        });

        // Edit
        holder.binding.btnEditRoom.setOnClickListener(v -> {
            listener.onEdit(task, position);
        });
    }

    @Override
    public int getItemCount() {
         return taskList.size();
    }

     // Update list after changes
    public void updateTasks(List<TaskModelRoom> newTasks) {
        this.taskList = newTasks;
        notifyDataSetChanged();
    }

    public interface OnTaskActionListener {
        void onDelete(TaskModelRoom task, int position);
        void onEdit(TaskModelRoom task, int position);
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        RoomDbItemListsBinding binding ;

        public ViewHolder(RoomDbItemListsBinding binding) {
             super(binding.getRoot());
            this.binding = binding;
        }
    }

}
