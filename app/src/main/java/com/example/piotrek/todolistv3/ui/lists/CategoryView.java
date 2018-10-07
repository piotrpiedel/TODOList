package com.example.piotrek.todolistv3.ui.lists;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.piotrek.todolistv3.R;
import com.example.piotrek.todolistv3.injection.Injection;
import com.example.piotrek.todolistv3.model.Category;
import com.example.piotrek.todolistv3.ui.tasks.TasksView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import timber.log.Timber;

public class CategoryView extends AppCompatActivity implements CategoryContract.View {

    @BindView(R.id.categories_list_view)
    ListView categoryView;

    @BindView(R.id.list_activity_fub_button)
    FloatingActionButton floatingActionButton;

    private CategoryPresenter categoryPresenter;
    CategoryAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        ButterKnife.bind(this);
        setPresenter();
        setAdapter();
        setCategoryView();
    }


    private void setAdapter() {
        categoryAdapter = new CategoryAdapter(this, categoryPresenter);
        categoryView.setAdapter(categoryAdapter);
    }

    @Override
    @OnClick(R.id.list_activity_fub_button)
    public void onFabButtonClicked() {
        showAddCategoryView();
    }

    private void setCategoryView() {
        categoryPresenter.onViewCreated();
        categoryView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Timber.d("category has been clicked");
                startNewActivity(categoryAdapter.getItem(position));
            }
        });
    }

    private void setPresenter() {
        categoryPresenter = Injection.injectListsPresenter(getApplicationContext());
        categoryPresenter.onAttach(this);
    }

    public void startNewActivity(Category category) {
        Intent intent = new Intent(CategoryView.this, TasksView.class);
        intent.putExtra("idCategory", category.getId());
        startActivity(intent);
    }


    @Override
    public void showAddCategoryView() {
        final EditText taskEditText = new EditText(this);
        Timber.d("showAddCategory");
        AlertDialog dialog = buildNewCategoryDialog(taskEditText);
        dialog.show();

    }

    private AlertDialog buildNewCategoryDialog(EditText taskEditText) {
        return new AlertDialog.Builder(this)
                .setTitle("Add New Category")
                .setView(taskEditText)
                .setPositiveButton("Add new Category", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog1, int which) {
                        categoryPresenter.onCategoryCreated(taskEditText.getText().toString());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
    }

    @Override
    public void showCategoryList(List<Category> categoryList) {
        categoryAdapter.clear();
        categoryAdapter.addAll(categoryList);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CategoryPresenter presenter) {
        this.categoryPresenter = presenter;
    }

}
