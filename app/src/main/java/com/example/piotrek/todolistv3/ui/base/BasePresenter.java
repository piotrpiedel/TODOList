package com.example.piotrek.todolistv3.ui.base;


public class BasePresenter<V> {
        protected V mView;
    public void attachView(V view) {
        this.mView = view;
    }
    //attachView(CategoryView)

    public void detachView() {
        this.mView = null;
    }

    public boolean isViewAttached() {
        return mView != null;
    }

    public V getmView() {
        return mView;
    }
}
