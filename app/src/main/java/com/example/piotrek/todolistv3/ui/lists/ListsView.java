package com.example.piotrek.todolistv3.ui.lists;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.injection.Injection;

public class ListsView extends AppCompatActivity implements ListsContract.View {
    @BindView(R.id.list_view)
    android.widget.ListView list_view;
    private ListsPresenter listsPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ButterKnife.bind(this);

        listsPresenter = Injection.injectListsPresenter(getApplicationContext());
        listsPresenter.attachView(this);
//        TODO: dodać tutaj zawartość metodę
//        listsPresenter.onViewCreated();





        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
