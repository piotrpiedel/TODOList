package com.example.piotrek.todolistv3.ui.lists;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.piotrek.todolistv3.model.CategoryRepository;
import com.example.piotrek.todolistv3.ui.tasks.TasksView;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.android.controller.ActivityController;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Piotr on 2018-01-13.
 */
@RunWith(RobolectricTestRunner.class)
public class CategoryViewTest {
    @Mock
    View view;
    @Mock
    View view2;
    @Mock
    ViewParent parentView;

    @Mock
    Context context;
    @Mock
    CategoryRepository categoryRepository;
    @Mock
    private CategoryView categoryView;
    @Mock
    CategoryPresenter categoryPresenter;

    private ActivityController<CategoryView> controller;

    @Before
    public void setUp() throws Exception {
        controller = Robolectric.buildActivity(CategoryView.class);

        MockitoAnnotations.initMocks(this);

        categoryView = controller.get();
        categoryView.setPresenter(categoryPresenter);


    }

    @Test
    public void startNewActivity() throws Exception {
        Intent expectedIntent = new Intent(categoryView, TasksView.class);
        TextView textView = new TextView(RuntimeEnvironment.application.getApplicationContext());
        textView.setText("TEST_CATEGORY");
        when(view.findViewById(anyInt())).thenReturn(textView);

        categoryView.startNewActivity(view);
        assertTrue(shadowOf(categoryView).getNextStartedActivity().filterEquals(expectedIntent));


    }


    @Test
    public void ifTheButtonGiveSignalToThePresenter() throws Exception{
        ViewGroup parent = new RelativeLayout(RuntimeEnvironment.application);
        parent.addView(view);
        when(view.getParent()).thenReturn(parent);
        TextView categoryNameTextView = new TextView(RuntimeEnvironment.application.getApplicationContext());
        categoryNameTextView.setText("");
        when(parent.findViewById(anyInt())).thenReturn(categoryNameTextView);
        TextView categoryIdTextView = new TextView(RuntimeEnvironment.application.getApplicationContext());
        categoryNameTextView.setText("");
        when(parent.findViewById(anyInt())).thenReturn(categoryIdTextView);
//        categoryView.buttonDeleteClicked(view);
//        verify(categoryPresenter).onDeleteButtonClicked("");

    }

}