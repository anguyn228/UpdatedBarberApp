package com.example.barbercornerproj.model;

public class StaffModel {
    private int id;
    private String userID;
    private String name;
    private String title;
    private String location;
    private String shift;

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    private String bio;

    //constructors
    public StaffModel(int id, String userID, String name, String title, String location, String shift, String bio) {
        this.id = id;
        this.name = name;
        this.title = title;
        this.location = location;
        this.shift = shift;
        this.userID = userID;
        this.bio = bio;
    }

    public StaffModel(String userID, String title, String location, String shift, String bio) {
        this.userID = userID;
        this.title = title;
        this.location = location;
        this.shift = shift;
        this.bio = bio;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    // toString method
    @Override
    public String toString() {
        return  "  ID: " + id +
                "  Title: '" + title + '\'' +
                "  Location: '" + location + '\'' +
                "  Shift:'" + shift + '\'' +
                "Bio:" + bio + '\'';
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {

        this.id = id;
    }
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShift() {
        return shift;
    }

    public void setShift(String shift) {
        this.shift = shift;
    }


}
