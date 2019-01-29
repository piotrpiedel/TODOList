package com.example.piotrek.todolist.ui.lists;

import android.content.Context;
import android.view.View;

import com.example.piotrek.todolist.model.Category.Category;
import com.example.piotrek.todolist.model.Category.CategoryRepository;
import com.example.piotrek.todolist.ui.base.BasePresenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.mockito.Mockito.verify;

@RunWith(RobolectricTestRunner.class)
public class CategoryPresenterTest {
    @Mock
    Context context;
    @Mock
    CategoryRepository categoryRepository;

    @Mock
    Category category;
    @Mock
    View view;
    @Mock
    CategoryContract categoryContract;
    @Mock
    CategoryContract.View viewcategorycontract;
    @Mock
    CategoryView categoryView;
    @Mock
    BasePresenter<CategoryContract.View> basePresenter;
    @Mock
    CategoryContract.View mView;

    private ActivityController<CategoryView> controller;
    @Mock
    CategoryPresenter categoryPresenter;

    @Before
    public void setUp() throws Exception {

        MockitoAnnotations.initMocks(this);
        controller = Robolectric.buildActivity(CategoryView.class);
//        categoryPresenter.attachView(categoryView);
        mView = categoryView;
//        basePresenter.attachView(baseView);
        categoryPresenter.setCategoryRepository(categoryRepository);


    }


    @Test
    public void if_onAddCategoryClicked() throws Exception {
        //  i tutaj nie wiem co wpisac co to jest ten baseView
        mView.showAddCategoryView();
        //tutaj bym weryfikował czy metoda została wywolana??
        verify(categoryView).showAddCategoryView();

    }
//    stub vs mock

    @Test
    public void onViewCreated() throws Exception {
    }

    @Test
    public void onCategoryCreated() throws Exception {
    }

    @Test
    public void onDeleteButtonClicked() throws Exception {
    }

}