package com.example.projectp3sem1;

public class Book {

    private int id;
    private String name, author, description, cover;

    public Book(int id, String name, String author, String description, String cover) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.description = description;
        this.cover = cover;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getCover() {
        return cover;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
