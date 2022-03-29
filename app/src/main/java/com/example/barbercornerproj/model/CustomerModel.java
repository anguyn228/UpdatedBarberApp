package com.example.barbercornerproj.model;

public class CustomerModel {
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

    private String userId;

    public CustomerModel(String userId, String address, String age) {
        this.userId = userId;
        this.address = address;
        this.age = age;
    }

    private String address;
    private String age;
}
