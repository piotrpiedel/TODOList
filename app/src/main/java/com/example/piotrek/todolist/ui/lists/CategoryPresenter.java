package com.example.piotrek.todolist.ui.lists;

import com.example.piotrek.todolist.model.Category.Category;
import com.example.piotrek.todolist.model.Category.CategoryRepository;
import com.example.piotrek.todolist.ui.base.BasePresenter;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;


public class CategoryPresenter extends BasePresenter<CategoryContract.View> implements CategoryContract.Presenter {

    private CategoryRepository categoryRepository;

    private CompositeDisposable compositeDisposable;

    public CategoryPresenter(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void onViewCreated() {
        compositeDisposable = new CompositeDisposable();
        loadCategories();
    }

    private void loadCategories() {
        Disposable disposable = categoryRepository.selectAllCategories()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(this::refreshCategoryList);
        compositeDisposable.add(disposable);
    }

    private void refreshCategoryList(List<Category> categories) {
        baseView.showCategoryList(categories);
    }

    @Override
    public void onCategoryCreated(String categoryName) {
        Category category = getCategory(categoryName);
        Completable.fromAction(() -> categoryRepository.insertCategory(category))
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
        Completable.fromAction(() -> categoryRepository.deleteCategory(category))
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

    void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (!compositeDisposable.isDisposed())
            compositeDisposable.dispose();
    }
}