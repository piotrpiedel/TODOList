package com.example.piotrek.todolistv3.model;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import org.intellij.lang.annotations.Flow;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface CategoryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertCategory(Category category);

    @Delete
    void deleteCategory(Category category);

    @Update
    void updateCategory(Category category);

    @Query("SELECT * FROM category_table WHERE id =:idCategoryOther")
    Flowable<Category> selectCategory(int idCategoryOther);

    @Query("SELECT * FROM category_table")
    Flowable<List<Category>> selectAllCategories();

    @Query("DELETE FROM category_table")
    void deleteAllCategories();

}
