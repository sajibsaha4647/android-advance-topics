package com.example.java_android_app.HelperRoomDatabase;


import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.Room;
import android.content.Context;
import com.example.java_android_app.model.TaskModelRoom;

@Database(entities = {TaskModelRoom.class}, version = 1, exportSchema = false)
public abstract class TaskDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();


    private static TaskDatabase INSTANCE;

    public static TaskDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    TaskDatabase.class, "task_db")
                    .allowMainThreadQueries() // only for simple apps
                    .build();
        }
        return INSTANCE;
    }
}

