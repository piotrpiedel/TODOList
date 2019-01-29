package com.example.piotrek.todolist.ui.lists;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;

public class AddCategoryDialog extends AlertDialog {

    protected AddCategoryDialog(Context context) {
        super(context);
    }

    protected AddCategoryDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    protected AddCategoryDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
