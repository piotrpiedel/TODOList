package com.example.piotrek.todolist.model.Category;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

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
