package com.example.piotrek.todolistv3.model;


import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "category_table")
public class Category {

    public void setId(int id) {
        this.id = id;
    }

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String categoryTitle;

    public Category(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public int getId() {
        return id;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    @Override
    public String toString() {
        return categoryTitle;
    }
}
