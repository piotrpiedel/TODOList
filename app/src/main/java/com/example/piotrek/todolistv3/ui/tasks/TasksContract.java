package com.example.piotrek.todolistv3.ui.tasks;

import com.example.piotrek.todolistv3.model.Task;

import java.util.ArrayList;
import java.util.List;



public interface TasksContract {
    interface View {
        void onFabButtonClicked();

        void showSnackbar();

        void showAddTaskView();

        void showTaskList(ArrayList<Task> taskList);

        void signalFromButtonDeleteClicked(android.view.View view);

    }

    interface Presenter {
        void onAddTaskButtonClicked();

        void onAddTaskClicked();

        void onViewCreated(int idCategory);

        void onTaskCreated(Task task);

        void onDeleteButtonClicked(Task task);

    }
}
