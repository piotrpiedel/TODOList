package com.example.piotrek.todolist.ui.tasks.views;

import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.piotrek.todolist.R;
import com.example.piotrek.todolist.model.Task.Task;
import com.example.piotrek.todolist.ui.tasks.TasksPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.ViewHolder> {

    private TasksPresenter tasksPresenter;

    private Context context;

    private List<Task> taskList;

    TaskAdapter(List<Task> taskList, TasksPresenter tasksPresenter) {
        this.tasksPresenter = tasksPresenter;
        this.taskList = taskList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Task task = taskList.get(position);
        holder.taskTitle.setText(task.getTaskTitle());
        holder.taskButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tasksPresenter.onDeleteButtonClicked(task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_title)
        TextView taskTitle;

        @BindView(R.id.item_button_delete)
        Button taskButtonDelete;

        Task task; // TODO: refactor and cast itemView to Task item

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}