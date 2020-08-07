package com.example.semana5persistenciapaulpereira;

import android.os.Parcel;
import android.os.Parcelable;

public class Entidad2 implements Comparable<Entidad2> {
    private int id;
    private int imgFoto;
    private String nombre;
    private int rating;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Entidad2(int imgFoto, String nombre, int rating) {
        this.imgFoto = imgFoto;
        this.nombre = nombre;
        this.rating = rating;
    }
public Entidad2(){

}
    public void setImgFoto(int imgFoto) {
        this.imgFoto = imgFoto;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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


    @Override
    public int compareTo(Entidad2 o) {
        if(o.getRating()<rating){
            return -1;
        }else if(o.getRating()==rating){
            return 0;
        }else{
            return 1;
        }

      }
}
