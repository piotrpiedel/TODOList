package com.example.piotrek.todolist.model.Task;

import android.app.Application;

import com.example.piotrek.todolist.model.AppDatabase;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class TasksRepository {

    private TaskDao taskDao;

    public TasksRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        taskDao = database.getTaskDao();
    }

    public void insertTask(Task task) {
        Completable.fromAction(() -> taskDao.insertTask(task))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public void updateTask(Task task) {
        Completable.fromAction(() -> taskDao.updateTask(task))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public void deleteTask(Task task) {
        Completable.fromAction(() -> taskDao.deleteTask(task))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public void deleteAllTasks() {
        Completable.fromAction(() -> taskDao.deleteAllTasks())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public Flowable<List<Task>> getAllTaskFromSelectedCategory(int categoryId) {
        return taskDao.getAllTaskFromCategory(categoryId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Task>> getAllTasks() {
        return taskDao.getAllTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private class CompletableObserverMain implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }
    }

}
