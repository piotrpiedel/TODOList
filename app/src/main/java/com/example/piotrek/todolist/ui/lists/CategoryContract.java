package com.example.piotrek.todolist.ui.lists;


import com.example.piotrek.todolist.model.Category.Category;

import java.util.List;

public interface CategoryContract {
    interface View {

        void onFabButtonClicked();

        void showAddCategoryView();

        void showCategoryList(List<Category> categoryList);

        void setPresenter(CategoryPresenter presenter);
    }

    interface Presenter {

        void onViewCreated();

        void onCategoryCreated(String categoryName);

        void onDeleteButtonClicked(Category category);
    }
}
