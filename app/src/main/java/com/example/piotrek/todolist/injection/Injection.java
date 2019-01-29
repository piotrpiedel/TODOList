package com.example.piotrek.todolist.injection;

import android.app.Application;
import android.content.Context;

import com.example.piotrek.todolist.model.Category.CategoryRepository;
import com.example.piotrek.todolist.model.Task.TasksRepository;
import com.example.piotrek.todolist.ui.lists.CategoryPresenter;
import com.example.piotrek.todolist.ui.tasks.TasksPresenter;


public class Injection {
    private static TasksRepository tasksRepository;
    private static CategoryRepository categoryRepository;
    private static TasksPresenter tasksPresenter;
    private static CategoryPresenter categoryPresenter;

    public static TasksPresenter injectTasksPresenter(Context context) {
        if (tasksPresenter == null) {
            return new TasksPresenter(getTasksRepository(context));
        } else {
            return tasksPresenter;
        }
    }

    public static TasksRepository getTasksRepository(Context context) {
        if (tasksRepository == null) {
            return new TasksRepository((Application)context.getApplicationContext());
        } else {
            return tasksRepository;
        }
    }

    public static CategoryPresenter injectListsPresenter(Context context) {
        if (categoryPresenter == null) {
            return new CategoryPresenter(getListsRepository(context));
        } else {
            return categoryPresenter;
        }
    }

    public static CategoryRepository getListsRepository(Context context) {
        if (categoryRepository == null) {
            return new CategoryRepository((Application)context.getApplicationContext());
        } else {
            return categoryRepository;
        }
    }


}
