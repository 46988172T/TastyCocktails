package com.leosssdroid.tastycocktails.Clases;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Leosss on 26/01/2017.
 */

public class Recetas implements Serializable {
    private String nombre, descripcion, ingredientes, picture, idUsuario;
    private Map<String,String> likes = new TreeMap();

    public Recetas() {
    }

    public Recetas(String nombre, String descripcion, String ingredientes, String picture, String idUsuario, Map<String, String> likes) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        this.picture = picture;
        this.idUsuario = idUsuario;
        this.likes = likes;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getIngredientes() {
        return ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        this.ingredientes = ingredientes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Map<String, String> getLikes() {
        return likes;
    }

    public void setLikes(Map<String, String> likes) {
        this.likes = likes;
    }

    //TODO implementar likes
}
