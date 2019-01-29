package com.example.piotrek.todolist.ui.lists;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.EditText;

import com.example.piotrek.todolist.R;
import com.example.piotrek.todolist.injection.Injection;
import com.example.piotrek.todolist.model.Category.Category;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CategoryView extends AppCompatActivity implements CategoryContract.View {

    @BindView(R.id.categories_recycler_view)
    RecyclerView categoriesRecyclerView;

    @BindView(R.id.list_activity_fub_button)
    FloatingActionButton floatingActionButton;

    private CategoryPresenter categoryPresenter;

    CategoryAdapter categoryAdapter;

    List<Category> categories = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_categories);
        ButterKnife.bind(this);
        setPresenter();
        loadCategories();
        setAdapter();
    }

    private void setAdapter() {
        categoryAdapter = new CategoryAdapter(categories, categoryPresenter);
        categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        categoriesRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    @OnClick(R.id.list_activity_fub_button)
    public void onFabButtonClicked() {
        showAddCategoryView();
    }

    private void loadCategories() {
        categoryPresenter.onViewCreated();
    }

    private void setPresenter() {
        categoryPresenter = Injection.injectListsPresenter(getApplicationContext());
        categoryPresenter.onAttach(this);
    }

    @Override
    public void showAddCategoryView() {
        final EditText taskEditText = new EditText(this);
        AlertDialog dialog = buildNewCategoryDialog(taskEditText);
        dialog.show();
    }

    private AlertDialog buildNewCategoryDialog(EditText taskEditText) {
        return new AlertDialog.Builder(this)
                .setTitle("Add New Category")
                .setView(taskEditText)
                .setPositiveButton("Add new Category", (dialog1, which) -> categoryPresenter.onCategoryCreated(taskEditText.getText().toString()))
                .setNegativeButton("Cancel", null)
                .create();
    }

    @Override
    public void showCategoryList(List<Category> categoryList) {
        this.categories.clear();
        this.categories.addAll(categoryList);
        categoryAdapter.notifyDataSetChanged();
    }

    @Override
    public void setPresenter(CategoryPresenter presenter) {
        this.categoryPresenter = presenter;
    }

}
