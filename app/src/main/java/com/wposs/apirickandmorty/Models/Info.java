package com.wposs.apirickandmorty.Models;

public class Info extends BaseModel {
    private int count;
    private int pages;
    private String next;
    private String prev;

    public Info(int count, int pages, String next, String prev) {
        this.count = count;
        this.pages = pages;
        this.next = next;
        this.prev = prev;
    }

    public int getCount() {
        return count;
    }

    public int getPages() {
        return pages;
    }

    public String getNext() {
        return next;
    }

    public String getPrev() {
        return prev;
    }
}
