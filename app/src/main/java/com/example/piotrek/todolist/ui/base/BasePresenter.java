package com.example.piotrek.todolist.ui.base;


public class BasePresenter<V> {

    protected V baseView;

    public void onAttach(V view) {
        this.baseView = view;
    }

    public void onDetach() {
        this.baseView = null;
    }

    public boolean isViewAttached() {
        return baseView != null;
    }

    public V getBaseView() {
        return baseView;
    }
}
