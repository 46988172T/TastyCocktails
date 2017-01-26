package com.leosssdroid.tastycocktails.Clases;

import android.net.Uri;

/**
 * Created by Leosss on 21/01/2017.
 */

public class UserTasty {
    private String idFacebbok, name, firstName, lastName, description, picture;


    public UserTasty() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserTasty(String idFacebbok, String picture, String description, String lastName, String firstName, String name) {
        this.idFacebbok = idFacebbok;
        this.picture = picture;
        this.description = description;
        this.lastName = lastName;
        this.firstName = firstName;
        this.name = name;
    }

    //GETTERS Y SETTERS

    public String getIdFacebbok() {
        return idFacebbok;
    }

    public void setIdFacebbok(String idFacebbok) {
        this.idFacebbok = idFacebbok;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
