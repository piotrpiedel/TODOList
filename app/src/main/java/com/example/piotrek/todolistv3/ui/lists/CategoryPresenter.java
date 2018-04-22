package com.example.piotrek.todolistv3.ui.lists;

import com.example.piotrek.todolistv3.model.Category;
import com.example.piotrek.todolistv3.model.CategoryRepository;
import com.example.piotrek.todolistv3.ui.base.BasePresenter;

import java.util.ArrayList;


public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {
    private CategoryRepository categoryRepository;

    public CategoryPresenter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }



    @Override
    public void onAddCategoryClicked() {
        mView.showAddCategoryView();
    }

    @Override
    public void onViewCreated() {
        ArrayList<Category> categories = loadCategories();
        mView.showCategoryList(categories);
    }

    private ArrayList<Category> loadCategories() {
        return categoryRepository.loadCategories();
    }

    @Override
    public void onCategoryCreated(Category category) {
        categoryRepository.saveCategory(category.getCategoryName());
        mView.showCategoryList(loadCategories());
    }

    @Override
    public void onDeleteButtonClicked(String categoryName,String categoryId ) {
        categoryRepository.deleteCategory(categoryId);
        mView.showCategoryList(loadCategories());
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
}