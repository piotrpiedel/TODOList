package com.example.piotrek.todolistv3.ui.tasks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.model.Task;

import java.util.ArrayList;

import butterknife.BindView;


public class TaskAdapter extends ArrayAdapter<Task> {
    private Context mContext;
    public TaskAdapter(Context context) {
        super(context,0);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Task task = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        }
        TextView taskTitle = convertView.findViewById(R.id.task_title);
        TextView idTask = convertView.findViewById(R.id.id_task);
        taskTitle.setText(task.getNameTask());
        String idTaskString= String.valueOf(task.getIdTask());
        idTask.setText(idTaskString);
        return convertView;
    }
}