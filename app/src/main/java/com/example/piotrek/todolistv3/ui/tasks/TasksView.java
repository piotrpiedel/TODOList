package com.example.piotrek.todolistv3.ui.tasks;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.injection.Injection;
import com.example.piotrek.todolistv3.model.Task;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksView extends AppCompatActivity implements TasksContract.View {

    @BindView(R.id.lstTask)
    ListView lstTask;
    int idCategory;
    private TasksPresenter tasksPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        tasksPresenter = Injection.injectTasksPresenter(getApplicationContext());
        tasksPresenter.attachView(this);
        Intent i = getIntent();
        String tempIdCategory= i.getStringExtra("idCategory");
        idCategory = Integer.valueOf(tempIdCategory);
        Log.d("CategoryView", "Przekazane CategoryId do taskView  =  " + idCategory);
        tasksPresenter.onViewCreated(idCategory);


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }


    public void showAddTaskView() {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add New Task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                        Task newTask = new Task(task,idCategory);
                        tasksPresenter.onTaskCreated(newTask);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }


    @Override
    @OnClick(R.id.fab)
    public void onFabButtonClicked() {
        tasksPresenter.onAddTaskButtonClicked();
    }

    @Override
    public void showSnackbar() {
        View parentLayout = findViewById(android.R.id.content);
        Snackbar.make(parentLayout, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public void showTaskList(ArrayList<Task> taskList) {
        for (Task task: taskList){
            Log.d("TAG",task.toString());
        }
        TaskAdapter taskAdapter = new TaskAdapter(this);
        taskAdapter.addAll(taskList);
        lstTask.setAdapter(taskAdapter);
        taskAdapter.notifyDataSetChanged();
    }


    public void signalFromButtonDeleteClicked(android.view.View view) {
        android.view.View parent = (View) view.getParent();
        TextView taskNameTextView = (TextView) parent.findViewById(R.id.task_title);
        TextView taskIdTextView = (TextView) parent.findViewById(R.id.id_task);
        Log.e("String", (String) taskNameTextView.getText());
        String taskName = String.valueOf(taskNameTextView.getText());
        String taskIdString = String.valueOf(taskIdTextView.getText());
        int taskId = Integer.valueOf(taskIdString);
        Task task = new Task(taskId,taskName,idCategory);
        tasksPresenter.onDeleteButtonClicked(task);
    }

}
