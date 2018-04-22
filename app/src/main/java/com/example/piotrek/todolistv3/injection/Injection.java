package com.example.piotrek.todolistv3.injection;

import android.content.Context;

import com.example.piotrek.todolistv3.model.DbHelper;
import com.example.piotrek.todolistv3.model.CategoryRepository;
import com.example.piotrek.todolistv3.model.TasksRepository;
import com.example.piotrek.todolistv3.ui.lists.CategoryPresenter;
import com.example.piotrek.todolistv3.ui.tasks.TasksPresenter;



public class Injection {
    private static TasksRepository tasksRepository;
    private static CategoryRepository categoryRepository;
    private static DbHelper dbHelper;
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
            return new TasksRepository(getDbHelper(context));
        } else {
            return tasksRepository;
        }
    }

    public static DbHelper getDbHelper(Context context) {
        if (dbHelper == null) {
            return new DbHelper(context);
        } else {
            return dbHelper;
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
            return new CategoryRepository(getDbHelper(context));
        } else {
            return categoryRepository;
        }
    }


}
