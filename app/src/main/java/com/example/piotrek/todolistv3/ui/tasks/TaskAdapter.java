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
        // Get the data item for this position
        Task task = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.row, parent, false);
        }
        // Lookup view for data population
        TextView taskTitle = convertView.findViewById(R.id.task_title);
        TextView idTask = convertView.findViewById(R.id.id_task);

        // Populate the data into the template view using the data object
        taskTitle.setText(task.getNameTask());
        String idTaskString= String.valueOf(task.getIdTask());
        idTask.setText(idTaskString);
        // Return the completed view to render on screen
        return convertView;
    }
}