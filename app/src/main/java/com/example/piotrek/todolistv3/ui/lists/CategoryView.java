package com.example.piotrek.todolistv3.ui.lists;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.injection.Injection;
import com.example.piotrek.todolistv3.model.AndroidDatabaseManager;
import com.example.piotrek.todolistv3.model.Category;
import com.example.piotrek.todolistv3.ui.tasks.TasksView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryView extends AppCompatActivity implements CategoryContract.View {
    @BindView(R.id.list_view)
    ListView categoryView;
    private CategoryPresenter categoryPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_activity);
        ButterKnife.bind(this);

        categoryPresenter = Injection.injectListsPresenter(getApplicationContext());
        categoryPresenter.attachView(this);
        categoryPresenter.onViewCreated();
        categoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, android.view.View view, int position, long l) {
                startNewActivity(view);
            }

        });


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.list_activity__fub_button);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("CategoryView","Clicked on add category button");
                showAddCategoryView();
            }
        });

//        FloatingActionButton button =(FloatingActionButton) findViewById(R.id.fabbuttonstartdatabaseview);
//
//        button.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//
//                Intent dbmanager = new Intent(CategoryView.this,AndroidDatabaseManager.class);
//                startActivity(dbmanager);
//            }
//        });
    }

    public void startNewActivity(View view){
        TextView categoryIdTextView = (TextView) view.findViewById(R.id.id_category);
        String categoryId = String.valueOf(categoryIdTextView.getText());
        Log.d("CategoryView", "CategoryId =  " + categoryId);
        Intent intent = new Intent(CategoryView.this, TasksView.class);
        intent.putExtra("idCategory",categoryId);

        startActivity(intent);
    }

    @Override
    public void onFabButtonClicked() {

    }


    @Override
    public void showAddCategoryView() {
        final EditText taskEditText = new EditText(this);
        Log.d("CategoryView","showAddCategory");
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add New Category")
//                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    //tutaj trzeba przerobić na obiekt??? chyba tutaj
                    public void onClick(DialogInterface dialog, int which) {
                        String table = String.valueOf(taskEditText.getText());
                        Category newTable = new Category(table);
                        categoryPresenter.onCategoryCreated(newTable);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();

    }

    @Override
    public void showCategoryList(ArrayList<Category> categoryList) {
//        for (Category category: categoryList){
//            Log.d("TAG",category.toString());
//        }
        CategoryAdapter categoryAdapter = new CategoryAdapter(this);
        categoryAdapter.addAll(categoryList);


        categoryView.setAdapter(categoryAdapter);

        categoryAdapter.notifyDataSetChanged();
    }

    public void categorySignalFromButtonDeleteClicked(View view) {
        View parent = (View) view.getParent();
        TextView categoryNameTextView = (TextView) parent.findViewById(R.id.category_title);
        TextView categoryIdTextView = (TextView) parent.findViewById(R.id.id_category);
        Log.e("String", (String) categoryNameTextView.getText());
        String categoryName = String.valueOf(categoryNameTextView.getText());
        String categoryId = String.valueOf(categoryIdTextView.getText());

        categoryPresenter.onDeleteButtonClicked(categoryName, categoryId);
    }

    @Override
    public void setPresenter(CategoryPresenter presenter) {
        this.categoryPresenter = presenter;
    }

}
