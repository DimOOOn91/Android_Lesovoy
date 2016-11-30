package com.example.module02task01;

import java.util.Date;

public class DataSave {
    private String author;
    private String theme;
    private String context;
    private Date dateOfCreating;
    private Date dateOfEditing;

    public DataSave(String author, String theme, String context) {
        this.author = author;
        this.theme = theme;
        this.context = context;
        this.dateOfCreating = new Date();
        this.dateOfEditing = this.dateOfCreating;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public Date getDateOfCreating() {
        return dateOfCreating;
    }

    public Date getDateOfEditing() {
        return dateOfEditing;
    }

    public void setDateOfEditing(Date dateOfEditing) {
        this.dateOfEditing = dateOfEditing;
    }
}