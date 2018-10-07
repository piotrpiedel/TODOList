package com.example.piotrek.todolistv3.ui.tasks;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.model.Task;

import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;


public class TaskAdapter extends ArrayAdapter<Task> {
    private Context mContext;

    private TasksPresenter tasksPresenter;

    TaskAdapter(Context context, TasksPresenter tasksPresenter) {
        super(context, 0);
        this.mContext = context;
        this.tasksPresenter = tasksPresenter;
    }

    @Nullable
    @Override
    public Task getItem(int position) {
        return super.getItem(position);
    }

    @NonNull
    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = LayoutInflater.from(mContext).inflate(R.layout.task_item_view, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.taskTitle.setText(Objects.requireNonNull(getItem(position), " TaskAdapter: getView task is null").getTaskTitle());
        holder.taskButtonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tasksPresenter.onDeleteButtonClicked(getItem(position));
            }
        });
        return view;
    }

    static class ViewHolder {
        @BindView(R.id.task_title)
        TextView taskTitle;

        @BindView(R.id.task_button_delete)
        Button taskButtonDelete;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}