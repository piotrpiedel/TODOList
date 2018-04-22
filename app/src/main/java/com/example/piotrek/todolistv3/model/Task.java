package com.example.piotrek.todolistv3.model;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;


public class Task {
    private static final AtomicInteger count = new AtomicInteger(0);
    private  int idTask;
    private String nameTask = "";
    private Date date;
    private int categoryId;


    public Task(String nameTask, int categoryId) {
        this.nameTask = nameTask;
        this.categoryId = categoryId;
    }

    public Task(int idTask, String nameTask, int categoryId) {
        this.idTask = idTask;
        this.nameTask = nameTask;
        this.categoryId = categoryId;
    }
    public Task(String nameTask) {
//        this.idTask = count.incrementAndGet();
        this.idTask = count.getAndIncrement();
        this.nameTask = nameTask;
    }

    public Task(int idTask, String nameTask) {
        this.idTask = idTask;
        this.nameTask = nameTask;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public static AtomicInteger getCount() {
        return count;
    }

    public int getIdTask() {
        return idTask;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public String toString() {
        return nameTask;
    }
}
