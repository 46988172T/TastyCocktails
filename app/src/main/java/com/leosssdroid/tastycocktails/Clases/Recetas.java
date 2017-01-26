package com.leosssdroid.tastycocktails.Clases;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Leosss on 26/01/2017.
 */

public class Recetas {
    private String nombre, descripcion, ingredientes;
    //private Map<Integer,String> pasos = new HashMap<Integer, String>();

    public Recetas() {
    }

    public Recetas(String nombre, String descripcion, String ingredientes/*, Map<Integer, String> pasos*/) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ingredientes = ingredientes;
        //this.pasos = pasos;
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

    /*public Map<Integer, String> getPasos() {
        return pasos;
    }

    public void setPasos(Map<Integer, String> pasos) {
        this.pasos = pasos;
    }*/
}
