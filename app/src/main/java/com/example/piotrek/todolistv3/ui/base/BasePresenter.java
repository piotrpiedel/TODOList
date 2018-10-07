package com.example.piotrek.todolistv3.ui.base;


public class BasePresenter<V> {

    protected V view;

    public void onAttach(V view) {
        this.view = view;
    }

    public void onDetach() {
        this.view = null;
    }

    public boolean isViewAttached() {
        return view != null;
    }

    public V getView() {
        return view;
    }
}
