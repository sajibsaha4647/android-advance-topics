package com.example.myapplication.DBHelaper;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import androidx.annotation.Nullable;

import com.example.myapplication.model.Task;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "notepad.db";
    private static final int DATABASE_VERSION = 1;
   public static final String TABLE_NAME = "tasks";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_TASK = "task";
    public static final String COLUMN_PRIORITY = "priority";
    public static final String COLUMN_DUE_DATE = "due_date";
    public static final String COLUMN_IS_DONE = "is_done";


     public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
       String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " ("
        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
        + COLUMN_TASK + " TEXT, "
        + COLUMN_PRIORITY + " INTEGER, "
        + COLUMN_DUE_DATE + " TEXT, "
        + COLUMN_IS_DONE + " INTEGER)";

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    // Insert task
    public long insertTask(String task, int priority, String dueDate, int isDone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, task);
        values.put(COLUMN_PRIORITY, priority);
        values.put(COLUMN_DUE_DATE, dueDate);
        values.put(COLUMN_IS_DONE, isDone);
        long id = db.insert(TABLE_NAME, null, values);
        db.close();
        return id;
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, COLUMN_PRIORITY + " ASC");
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String task = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_TASK));
                int priority = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_PRIORITY));
                String dueDate = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DUE_DATE));
                int isDone = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_IS_DONE));

                taskList.add(new Task(id, task, priority, dueDate, isDone));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return taskList;
    }

    // Update task
    public int updateTask(int id, String task, int priority, String dueDate, int isDone) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TASK, task);
        values.put(COLUMN_PRIORITY, priority);
        values.put(COLUMN_DUE_DATE, dueDate);
        values.put(COLUMN_IS_DONE, isDone);
        int rows = db.update(TABLE_NAME, values, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }

    // Delete task
    public int deleteTask(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rows = db.delete(TABLE_NAME, COLUMN_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
        return rows;
    }
}
