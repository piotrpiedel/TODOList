package com.example.piotrek.todolistv3.ui.lists;

import com.example.piotrek.todolistv3.model.Category;
import com.example.piotrek.todolistv3.model.CategoryRepository;
import com.example.piotrek.todolistv3.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {

    private CategoryRepository categoryRepository;

    private CompositeDisposable compositeDisposable;

    private Disposable disposable;

    public CategoryPresenter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void onViewCreated() {
        compositeDisposable = new CompositeDisposable();
        loadCategories();
    }

    private void loadCategories() {
        disposable = categoryRepository.selectAllCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Consumer<List<Category>>() {
                    @Override
                    public void accept(List<Category> categories) throws Exception {
                        refreshCategoryList(categories);
                    }
                });
        compositeDisposable.add(disposable);
    }

    private void refreshCategoryList(List<Category> categories) {
        view.showCategoryList(categories);
    }

    @Override
    public void onCategoryCreated(String categoryName) {
        Category category = getCategory(categoryName);
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                categoryRepository.insertCategory(category);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        loadCategories();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    private Category getCategory(String categoryName) {
        return new Category(categoryName);
    }

    @Override
    public void onDeleteButtonClicked(Category category) {
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Exception {
                categoryRepository.deleteCategory(category);
            }
        })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onComplete() {
                        Timber.d("onCategoryCreated: onComplete loadCategories ");
                        loadCategories();
                    }

                    @Override
                    public void onError(Throwable e) {
                    }
                });
    }

    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}