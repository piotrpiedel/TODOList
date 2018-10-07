package com.example.piotrek.todolistv3.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Update
    void updateTask(Task task);

    @Query("SELECT * FROM task_table WHERE id =:idTaskOther")
    Flowable<Task> getTask(int idTaskOther);

    @Query("SELECT * FROM task_table")
    Flowable<List<Task>> getAllTasks();

    @Query("SELECT * FROM task_table WHERE categoryId=:categoryId")
    Flowable<List<Task>> getAllTaskFromCategory(int categoryId);

    @Query("DELETE FROM task_table")
    void deleteAllTasks();
}
