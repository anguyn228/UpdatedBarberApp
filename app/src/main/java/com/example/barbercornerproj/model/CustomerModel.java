package com.example.barbercornerproj.model;

public class CustomerModel {

    private String userId;
    private String address;
    private String age;

    public CustomerModel(String userId, String address, String age) {
        this.userId = userId;
        this.address = address;
        this.age = age;
    }

    public String getUserId() {
        return userId;
    }

    public void setUsername(String userId) {
        this.userId = userId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
