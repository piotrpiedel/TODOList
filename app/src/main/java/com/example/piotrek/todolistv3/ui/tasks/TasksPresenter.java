package com.example.piotrek.todolistv3.ui.tasks;

import android.support.annotation.NonNull;

import com.example.piotrek.todolistv3.model.Task;
import com.example.piotrek.todolistv3.model.TasksRepository;
import com.example.piotrek.todolistv3.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class TasksPresenter extends BasePresenter<TasksContract.View> implements TasksContract.Presenter {

    private TasksRepository tasksRepository;

    private CompositeDisposable compositeDisposable;

    private Disposable disposable;

    public TasksPresenter(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public void onViewCreated(int idCategory) {
        compositeDisposable = new CompositeDisposable();
        loadTasks(idCategory);
    }

    private void loadTasks(int idCategory) {
        disposable = tasksRepository.getAllTaskFromSelectedCategory(idCategory)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Task>>() {
                    @Override
                    public void accept(List<Task> tasks) throws Exception {
                        refreshTasksList(tasks);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void refreshTasksList(List<Task> tasks) {
        view.showTaskList(tasks);
    }


    @Override
    public void onTaskCreated(String taskTitle, int idCategory) {
        Task createdTask = getTask(taskTitle, idCategory);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                tasksRepository.insertTask(createdTask);
            }
        })
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
                        Timber.d("onCategoryCreated: onComplete loadCategories ");
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
