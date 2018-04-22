package com.example.piotrek.todolistv3.ui.tasks;

import com.example.piotrek.todolistv3.model.Task;
import com.example.piotrek.todolistv3.model.TasksRepository;
import com.example.piotrek.todolistv3.ui.base.BasePresenter;

import java.util.ArrayList;
import java.util.List;



public class TasksPresenter extends BasePresenter<TasksContract.View> implements TasksContract.Presenter {
    private TasksRepository tasksRepository;

    public TasksPresenter(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    @Override
    public void onAddTaskButtonClicked() {
        mView.showAddTaskView();
    }

    @Override
    public void onAddTaskClicked() {
        mView.showAddTaskView();
    }

    @Override
    public void onViewCreated(int idCategory) {
        ArrayList<Task> tasks = loadTasks(idCategory);
        mView.showTaskList(tasks);
    }

    private ArrayList<Task> loadTasks(int idCategory){
        return tasksRepository.loadTasks(idCategory);
    }

    @Override
    public void onTaskCreated(Task task) {
        tasksRepository.saveTask(task);
        mView.showTaskList(loadTasks(task.getCategoryId()));
    }

    @Override
    public void onDeleteButtonClicked(Task task){
        tasksRepository.deleteTask(task);
        mView.showTaskList(loadTasks(task.getCategoryId()));
    }
}
