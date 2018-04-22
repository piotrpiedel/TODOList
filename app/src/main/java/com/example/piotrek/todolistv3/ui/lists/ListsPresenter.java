package com.example.piotrek.todolistv3.ui.lists;

import com.example.piotrek.todolistv3.model.ListsRepository;
import com.example.piotrek.todolistv3.ui.base.BasePresenter;



public class ListsPresenter extends BasePresenter<ListsContract.View> implements ListsContract.Presenter {
    private ListsRepository listsRepository;

    public ListsPresenter(ListsRepository listsRepository) {
        this.listsRepository = listsRepository;
    }


}