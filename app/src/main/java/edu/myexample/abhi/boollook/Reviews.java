package edu.myexample.abhi.boollook;

import com.google.firebase.database.Exclude;

public class Reviews {

    public Reviews() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
@Exclude
    private String key;
    private String Author;
    private String Data;
    private String Username3;

    public Reviews(String author, String data, String username) {
        this.Author = author;
        this.Data = data;
        this.Username3 = username;
    }



    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getData() {
        return Data;
    }

    public void setData(String data) {
        Data = data;
    }

    public String getUsername() {
        return Username3;
    }

    public void setUsername(String username) {
        Username3 = username;
    }





}
