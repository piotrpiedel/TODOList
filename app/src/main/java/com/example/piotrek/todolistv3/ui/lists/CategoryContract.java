package com.example.piotrek.todolistv3.ui.lists;


import com.example.piotrek.todolistv3.model.Category;

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
