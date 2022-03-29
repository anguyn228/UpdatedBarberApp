package com.example.barbercornerproj.model;

public class DataModel {
    private int id;
    private String name;
    private String title;
    private String userId;
    private String userName;
    private String password;

    //constructors


    public DataModel(String userName, String password, String name, String title) {
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.title = title;
    }

    public DataModel(int id, String name, String title, String userId, String password) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.userId = userId;
        this.password = password;
    }

    /*public DataModel(String name, String title, String userId, String password) {
        this.name = name;
        this.title = title;
        this.userId = userId;
        this.password = password;
    }*/

    public DataModel() {
    }

    @Override
    public String toString() {
        return  " ID: " + id +
                "  Name:'" + name + '\'' +
                "  Title: '" + title + '\'' +
                "  UserId: '" + userId + '\'' +
                "  Password:'" + password + '\'';
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

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
