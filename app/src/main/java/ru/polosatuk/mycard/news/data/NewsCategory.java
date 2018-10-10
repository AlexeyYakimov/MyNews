package ru.polosatuk.mycard.news.data;

public class NewsCategory {
    private final int id;
    private final String name;

    public NewsCategory(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}