package com.example.piotrek.todolistv3.model;


public class Lists {
    private int idList;
    private String name;
    public Lists(int idList, String name) {
        this.idList = idList;
        this.name = name;
    }

    public int getIdList() {
        return idList;
    }

    public String getName() {
        return name;
    }

    public void setIdList(int idList) {
        this.idList = idList;
    }

    public void setName(String name) {
        this.name = name;
    }
}
