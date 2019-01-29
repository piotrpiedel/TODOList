package com.example.piotrek.todolist.ui.tasks;

import com.example.piotrek.todolist.model.Task.Task;

import java.util.List;


public interface TasksContract {

    interface View {

        void onFabButtonClicked();

        void showAddTaskView();

        void showTaskList(List<Task> taskList);
    }

    interface Presenter {

        void onViewCreated(int idCategory);

        void onTaskCreated(String task, int idCategory);

        void onDeleteButtonClicked(Task task);
    }
}
