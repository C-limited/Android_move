package com.example.xbg.learnlistview2;
public class Book {
    private int picId;
    private String name;
    public Book(int picId, String name) {
        this.picId = picId;
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getPicId() {
        return picId;
    }
}
