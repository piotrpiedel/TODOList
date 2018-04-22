package com.example.piotrek.todolistv3.model;


import java.util.ArrayList;

public class CategoryRepository {
    private DbHelper dbHelper;

    public CategoryRepository(DbHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public void saveCategory(String category){
        dbHelper.insertNewCategory(category);
    }

    public ArrayList<Category> loadCategories(){
        return dbHelper.getCategoryList();
    }

    public void deleteCategory(String categorId){
        dbHelper.deleteCategory(categorId);
    }
}
