package com.example.semana5persistenciapaulpereira;

import java.io.Serializable;

public class Entidad implements Comparable<Entidad>  {

    private int Id;
    private int imgFoto;
    private String nombre;
    private int rating;

    public void setId(int id) {
        Id = id;
    }

    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId() {
        return Id;
    }

    public Entidad(int imgFoto, String nombre, int rating) {
        this.imgFoto = imgFoto;
        this.nombre = nombre;
        this.rating = rating;
    }

    public Entidad() {

    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImgFoto() {
        return imgFoto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getRating() {
        return rating;
    }


    public int compareTo(Entidad otraEntidad) {

        if ((this.rating)>(otraEntidad.getRating())){
            return -1;
        }
        else if ((this.rating)<(otraEntidad.getRating())){
            return 1;
        }
        else return 0;

    }
}