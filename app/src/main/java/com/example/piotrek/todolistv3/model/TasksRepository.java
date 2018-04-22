package com.example.piotrek.todolistv3.model;

import java.util.ArrayList;
import java.util.List;

public class TasksRepository {
   private DbHelper dbHelper;

    public TasksRepository(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void saveTask(Task task){
        dbHelper.insertNewTask(task);
    }

    public ArrayList<Task> loadTasks(int idCategory){
        return dbHelper.getTaskList(idCategory);
    }

    public void deleteTask(Task task){
        dbHelper.deleteTask(task);
    }
}
