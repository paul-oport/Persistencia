package com.example.semana5persistenciapaulpereira.BD;

public class Utilidades {
    //Constantes de la tabla mascotas
    public static final String DATABASE_NAME="mascota";
    public static final int DATABASE_version=1;

    public static final String TABLA_MASCOTA="mascotas";
    public static final String MASCOTAS_ID="id";
    public static final String MASCOTAS_NOMBRE="nombre";
    public static final String MASCOTAS_FOTO="foto";
    public static final String MASCOTAS_RATING="rating";

    public static final String CREAR_TABLA_MASCOTA="CREATE TABLE" +
    " " + TABLA_MASCOTA + " (" + MASCOTAS_ID +
    " " + "INTEGER PRIMARY KEY AUTOINCREMENT, " + MASCOTAS_NOMBRE + " TEXT, "+MASCOTAS_FOTO+ " INTEGER, " +MASCOTAS_RATING+ " INTEGER" +")";

}
