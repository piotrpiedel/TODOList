package com.example.piotrek.todolist.ui.tasks.views;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.Menu;
import android.widget.EditText;

import com.example.piotrek.todolist.R;
import com.example.piotrek.todolist.injection.Injection;
import com.example.piotrek.todolist.model.Task.Task;
import com.example.piotrek.todolist.ui.tasks.TasksContract;
import com.example.piotrek.todolist.ui.tasks.TasksPresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksView extends AppCompatActivity implements TasksContract.View {

    @BindView(R.id.task_list_recyclerView)
    RecyclerView taskRecyclerView;

    int idCategory;

    private TasksPresenter tasksPresenter;
    TaskAdapter taskAdapter;
    List<Task> taskList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);
        ButterKnife.bind(this);
        this.idCategory = getCategoryIdFromIntent();
        setTaskPresenter();
        loadTasks();
        setTaskAdapter();
    }

    private void loadTasks() {
        tasksPresenter.onViewCreated(idCategory);
    }

    private void setTaskPresenter() {
        tasksPresenter = Injection.injectTasksPresenter(getApplicationContext());
        tasksPresenter.onAttach(this);
    }

    private int getCategoryIdFromIntent() {
        Intent i = getIntent();
        return i.getIntExtra("idCategory", 0);
    }

    private void setTaskAdapter() {
        taskAdapter = new TaskAdapter(taskList, tasksPresenter);
        taskRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taskRecyclerView.setAdapter(taskAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public void showAddTaskView() {
        final EditText taskEditText = new EditText(this);
//        AddTaskDialog addTaskDialog = new AddTaskDialog();
//        addTaskDialog.show(getSupportFragmentManager(),"TASK_DIALOG_FRAGMENT");
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", (dialog1, which) -> {
                    String task = String.valueOf(taskEditText.getText());
                    tasksPresenter.onTaskCreated(task, idCategory);
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

    @Override
    @OnClick(R.id.task_list_activity_fub_button)
    public void onFabButtonClicked() {
        showAddTaskView();
    }

    @Override
    public void showTaskList(List<Task> taskList) {
        this.taskList.clear();
        this.taskList.addAll(taskList);
        taskAdapter.notifyDataSetChanged();
    }
}
