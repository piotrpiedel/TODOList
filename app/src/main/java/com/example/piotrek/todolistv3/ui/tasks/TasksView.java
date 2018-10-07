package com.example.piotrek.todolistv3.ui.tasks;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.EditText;
import android.widget.ListView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.injection.Injection;
import com.example.piotrek.todolistv3.model.Task;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TasksView extends AppCompatActivity implements TasksContract.View {

    @BindView(R.id.task_list_view)
    ListView taskListView;

    int idCategory;

    private TasksPresenter tasksPresenter;

    private TaskAdapter taskAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_task);
        ButterKnife.bind(this);
        this.idCategory = getCategoryIdFromIntent();
        setTaskPresenter();
        setTaskAdapter();
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
        taskAdapter = new TaskAdapter(this, tasksPresenter);
        taskListView.setAdapter(taskAdapter);
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
                        tasksPresenter.onTaskCreated(task, idCategory);
                    }
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
        taskAdapter.clear();
        taskAdapter.addAll(taskList);
        taskAdapter.notifyDataSetChanged();
    }

}
