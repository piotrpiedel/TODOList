package com.example.piotrek.todolist.ui.tasks;

import androidx.annotation.NonNull;

import com.example.piotrek.todolist.model.Task.Task;
import com.example.piotrek.todolist.model.Task.TasksRepository;
import com.example.piotrek.todolist.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;


public class TasksPresenter extends BasePresenter<TasksContract.View> implements TasksContract.Presenter {

    private TasksRepository tasksRepository;

    private CompositeDisposable compositeDisposable;

    public TasksPresenter(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public void onViewCreated(int idCategory) {
        compositeDisposable = new CompositeDisposable();
        loadTasks(idCategory);
    }

    private void loadTasks(int idCategory) {
        Disposable disposable = tasksRepository.getAllTaskFromSelectedCategory(idCategory)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::refreshTasksList);
        compositeDisposable.add(disposable);
    }

    private void refreshTasksList(List<Task> tasks) {
        baseView.showTaskList(tasks);
    }


    @Override
    public void onTaskCreated(String taskTitle, int idCategory) {
        Task createdTask = getTask(taskTitle, idCategory);
        Completable.fromAction(() -> tasksRepository.insertTask(createdTask))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        loadTasks(createdTask.getCategoryId());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @NonNull
    private Task getTask(String taskTitle, int idCategory) {
        return new Task(taskTitle, idCategory);
    }

    @Override
    public void onDeleteButtonClicked(Task task) {
        Completable.fromAction(() -> tasksRepository.deleteTask(task))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onComplete() {
                        loadTasks(task.getCategoryId());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}
