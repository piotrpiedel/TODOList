package com.example.piotrek.todolist.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import android.content.Context;

import com.example.piotrek.todolist.model.Category.Category;
import com.example.piotrek.todolist.model.Category.CategoryDao;
import com.example.piotrek.todolist.model.Task.Task;
import com.example.piotrek.todolist.model.Task.TaskDao;

@Database(entities = {Category.class, Task.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase instance;

    public abstract CategoryDao getCategoryDao();

    public abstract TaskDao getTaskDao();

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "app_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }

}


