package com.leosssdroid.tastycocktails.Clases;

import android.net.Uri;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Leosss on 21/01/2017.
 */

public class UserTasty {
    private String idFacebbok, name, firstName, lastName, description, picture;
    private Map<String,String> recipes = new TreeMap();
    private Map<String,String> followers = new TreeMap();
    private Map<String,String> following = new TreeMap();
    private Map<String,String> youlike = new TreeMap();


    public UserTasty() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public UserTasty(String idFacebbok, String picture, String description, String lastName, String firstName, String name,
                     Map<String,String> recipes, Map<String,String> followers, Map<String,String> following, Map<String,String> youlike) {
        this.idFacebbok = idFacebbok;
        this.picture = picture;
        this.description = description;
        this.lastName = lastName;
        this.firstName = firstName;
        this.name = name;
        this.followers = followers;
        this.following = following;
        this.recipes = recipes;
        this.youlike = youlike;
    }

    //GETTERS & SETTERS

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

    public Map getFollowers() {
        return followers;
    }

    public void setFollowers(Map<String,String> followers) {
        this.followers = followers;
    }

    public Map getFollowing() {
        return following;
    }

    public void setFollowing(Map<String,String> following) {
        this.following = following;
    }

    public Map getRecipes() {
        return recipes;
    }

    public void setRecipes(Map<String,String> recipes) {
        this.recipes = recipes;
    }

    public Map<String, String> getYoulike() {
        return youlike;
    }

    public void setYoulike(Map<String, String> youlike) {
        this.youlike = youlike;
    }
}
