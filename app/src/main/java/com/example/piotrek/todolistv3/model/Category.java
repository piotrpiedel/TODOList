package com.example.piotrek.todolistv3.model;


public class Category {
    private int idCategory;
    private String CategoryName;
    public Category(int idCategory, String CategoryName) {
        this.idCategory = idCategory;
        this.CategoryName = CategoryName;
    }

    public Category(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public void setName(String name) {
        this.CategoryName = CategoryName;
    }
    @Override
    public String toString() {
        return CategoryName;
    }
}
