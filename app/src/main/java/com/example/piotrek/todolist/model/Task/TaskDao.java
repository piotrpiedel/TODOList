package com.example.piotrek.todolist.model.Task;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
