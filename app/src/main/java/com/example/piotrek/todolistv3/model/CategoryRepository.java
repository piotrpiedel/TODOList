package com.example.piotrek.todolistv3.model;


import android.app.Application;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CategoryRepository {

    private CategoryDao categoryDao;

    private Flowable<List<Category>> allCategories;

    public CategoryRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        categoryDao = database.getCategoryDao();
    }

    public void insertCategory(Category category) {
        Completable.fromAction(() -> categoryDao.insertCategory(category))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public void updateCategory(Category category) {
        Completable.fromAction(() -> categoryDao.updateCategory(category))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public void deleteCategory(Category category) {
        Completable.fromAction(() -> categoryDao.deleteCategory(category))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public void deleteAllCategories() {
        Completable.fromAction(() -> categoryDao.deleteAllCategories())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserverMain());
    }

    public Flowable<Category> selectCategory(int idCategoryOther) {
        return categoryDao.selectCategory(idCategoryOther)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<List<Category>> selectAllCategories() {
        return categoryDao.selectAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    private class CompletableObserverMain implements CompletableObserver {

        @Override
        public void onSubscribe(Disposable d) {

        }

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }
    }
}
