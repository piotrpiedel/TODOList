package com.example.piotrek.todolist.ui.tasks.views;

import com.example.piotrek.todolist.ui.base.BaseDialog;

public class AddTaskDialog extends BaseDialog  {

    @Override
    protected String getInputHint() {
        return "Add your task";
    }

    @Override
    protected String getTitle() {
        return "New Task";
    }

    @Override
    protected void onSaveButtonClick() {
        super.onSaveButtonClick();
    }

    @Override
    protected void onCancelButtonClick() {
        super.onCancelButtonClick();
    }
}
