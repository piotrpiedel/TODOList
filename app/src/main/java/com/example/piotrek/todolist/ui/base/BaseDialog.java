package com.example.piotrek.todolist.ui.base;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.piotrek.todolist.R;

import butterknife.BindView;
import butterknife.OnClick;

public class BaseDialog extends AppCompatDialogFragment {

    @BindView(R.id.dialog_title)
    TextView dialogTitle;

    @BindView(R.id.dialog_input_text)
    EditText dialogInputEditText;

    @BindView(R.id.dialog_button_cancel)
    Button buttonCancel;

    @BindView(R.id.dialog_button_save)
    Button buttonSave;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_layout, container);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupTitle();
        setupInputHint();
    }

    private void setupInputHint() {
        dialogInputEditText.setHint(getInputHint());
    }

    protected String getInputHint() {
        return null;
    }

    private void setupTitle() {
        dialogTitle.setText(getTitle());
    }

    protected String getTitle() {
        return null;
    }

    @OnClick(R.id.dialog_button_save)
    protected void onSaveButtonClick() {

    }

    @OnClick(R.id.dialog_button_cancel)
    protected void onCancelButtonClick() {
        dismiss();
    }


}
