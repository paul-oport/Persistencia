package com.example.semana5persistenciapaulpereira;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.semana5persistenciapaulpereira.Adapter.Adaptador;

import com.example.semana5persistenciapaulpereira.BD.ConexionBD;
import com.example.semana5persistenciapaulpereira.BD.Utilidades;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    private Context context;
    private ListView lvItems;
    private Adaptador adaptador;
    public ArrayList<Entidad2> lista;
    public ArrayList<Entidad> listItems;
    String c0,c1,c2,c3,c4,c5,c6,c7,c8,c9,c10="0";
    int cont0,cont1,cont2,cont3,cont4,cont5,cont6,cont7,cont8,cont9,cont10=0;
    public int img;
    public int rat;
    public String nom;
    public boolean vacia;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
       // setSupportActionBar(toolbar);
        toolbar.setTitle("Mis mascotas");

        lvItems = (ListView) findViewById(R.id.lvItems);

      if (tablaVacia()){
          inicializarBD();

          adaptador = new Adaptador(this, ObtenerMascotas());
          lvItems.setAdapter(adaptador);
      }

        adaptador = new Adaptador(this, ObtenerMascotas());
        lvItems.setAdapter(adaptador);


        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {


            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                actualizarRanking(position);

                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    //******************************************************
        public boolean  tablaVacia() {

        boolean vacia;
        String quString = "select exists(select 1 from " + Utilidades.TABLA_MASCOTA  + ");";
        ConexionBD conn = new ConexionBD(this,"mascota",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        Cursor cursor = db.rawQuery(quString, null);
        cursor.moveToFirst();
        int count= cursor.getInt(0);
        if (count ==1) {
            vacia =  false;
        } else {
            vacia = true;
        }
        cursor.close();
        db.close();

        return vacia;
    }
//*****************************************************

    public void inicializarBD(){
        listItems = new ArrayList<Entidad>();

        listItems.add(new Entidad(R.drawable.aventurero1, "Aventurero ",0));
        listItems.add(new Entidad(R.drawable.bigotes1,"Bigotes    ",0));
        listItems.add(new Entidad(R.drawable.cocinero1,"Cocinero   ",0));
        listItems.add(new Entidad(R.drawable.cool1,"Cool       ",0));
        listItems.add(new Entidad(R.drawable.dormilon1,"Dormilón   ",0));
        listItems.add(new Entidad(R.drawable.intelectual1,"Intelectual",0));
        listItems.add(new Entidad(R.drawable.ojitos1,"Ojitos     ",0));
        listItems.add(new Entidad(R.drawable.peinado1,"Peinado    ",0));
        listItems.add(new Entidad(R.drawable.robusto1,"Robusto    ",0));
        listItems.add(new Entidad(R.drawable.triste1,"Triste     ",0));
        listItems.add(new Entidad(R.drawable.viajero1,"Viajero    ",0));

        ConexionBD conn = new ConexionBD(this, "mascota", null,1);

        SQLiteDatabase db=conn.getWritableDatabase();


        for (int i = 0;i< listItems.size();i++){

            img=listItems.get(i).getImgFoto();
            nom=listItems.get(i).getNombre();
            rat=listItems.get(i).getRating();
            ContentValues values =new ContentValues();
            values.put(Utilidades.MASCOTAS_NOMBRE,nom);
            values.put(Utilidades.MASCOTAS_FOTO,img);
            values.put(Utilidades.MASCOTAS_RATING,rat);
            db.insert("mascotas",null,values);
        }
        db.close();
    }

//************************************************************
public ArrayList<Entidad> ObtenerMascotas() {

    ArrayList<Entidad> mascotas=new ArrayList<>();
    ConexionBD conn = new ConexionBD(this,"mascota",null,1);
    SQLiteDatabase db = conn.getReadableDatabase();
    String query = "SELECT * FROM "+Utilidades.TABLA_MASCOTA;

    Cursor registros =db.rawQuery(query,null);

    while (registros.moveToNext()){
        Entidad EntidadActual = new Entidad();
        EntidadActual.setNombre(registros.getString(1));
        EntidadActual.setImgFoto(registros.getInt(2));
        EntidadActual.setRating(registros.getInt(3));

        mascotas.add(EntidadActual);
    }

    db.close();
    return mascotas;
    }
//*********************************************************************
    public void actualizarRanking(int position){
        int rat;
        String nom;
        int img;
        ConexionBD conn = new ConexionBD(this,"mascota",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM "+Utilidades.TABLA_MASCOTA;
        String parametros[] = new String[1];
        parametros[0]=Integer.toString(position+1);
        String campos[]=new String[4];
        campos[0]=Utilidades.MASCOTAS_ID;
        campos[1]=Utilidades.MASCOTAS_NOMBRE;
        campos[2]=Utilidades.MASCOTAS_FOTO;
        campos[3]=Utilidades.MASCOTAS_RATING;


            Cursor cursor=db.query(Utilidades.TABLA_MASCOTA,campos,Utilidades.MASCOTAS_ID+"=?",parametros,null,null,null);
            cursor.moveToFirst();
            rat=(cursor.getInt(3)+1);
            img=cursor.getInt(2);
            nom=cursor.getString(1);

            ContentValues values =new ContentValues();
            values.put(Utilidades.MASCOTAS_NOMBRE,nom);
            values.put(Utilidades.MASCOTAS_FOTO,img);
            values.put(Utilidades.MASCOTAS_RATING,rat);
            db.update(Utilidades.TABLA_MASCOTA,values,Utilidades.MASCOTAS_ID+"=?",parametros);
    }







//********************************************************
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }
    public boolean onOptionsItemSelected(MenuItem item){
       lista = new ArrayList<Entidad2>();

        int id = item.getItemId();
        if (id == R.id.rating5) {

            Intent intent = new Intent(MainActivity.this, RecyclerRating.class);
            startActivity(intent);
        }
        return true;
    }

    protected void onSaveInstanceDtate(Bundle outState){
        super.onSaveInstanceState(outState);
        outState.putSerializable("listado",listItems);
    }

    protected void onRestoreInstanceState(Bundle savedInstanceState){
        super.onRestoreInstanceState(savedInstanceState);


    }






}
