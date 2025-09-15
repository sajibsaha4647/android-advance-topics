package com.example.java_android_app.HelperRoomDatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.java_android_app.model.TaskModelRoom;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    void insertTask(TaskModelRoom task);

    @Update
    void updateTask(TaskModelRoom task);

    @Delete
    void deleteTask(TaskModelRoom task);

    @Query("SELECT * FROM tasks_room ORDER BY id DESC")
    List<TaskModelRoom> getAllTasks();
}

