package com.styros.esales.model;

/**
 * Created by atul on 11/19/2017.
 */
public class User  {

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    private int locationId;
    private String password;
    private String fullName;
    private String userId;
    private String username;
    private boolean isAdmin;


    private int id;
}
