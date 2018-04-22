package com.example.piotrek.todolistv3.ui.lists;


import com.example.piotrek.todolistv3.model.Category;

import java.util.ArrayList;
import java.util.List;

public interface CategoryContract {
    interface View {
        void onFabButtonClicked();


        void showAddCategoryView();

        void showCategoryList(ArrayList<Category> categoryList);

        void categorySignalFromButtonDeleteClicked(android.view.View view);

        void setPresenter(CategoryPresenter presenter);
    }

    interface Presenter {

        void onAddCategoryClicked();

        void onViewCreated();

        void onCategoryCreated(Category category);

        void onDeleteButtonClicked(String categoryName,String categoryId);

    }
}
