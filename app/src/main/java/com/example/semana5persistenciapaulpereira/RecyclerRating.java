package com.example.semana5persistenciapaulpereira;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.LauncherActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.semana5persistenciapaulpereira.Adapter.Adaptador;
import com.example.semana5persistenciapaulpereira.Adapter.RatingAdaptador;
import com.example.semana5persistenciapaulpereira.BD.ConexionBD;
import com.example.semana5persistenciapaulpereira.BD.Utilidades;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class RecyclerRating extends AppCompatActivity {
    private List<Entidad2> items = new ArrayList<>();
    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lmanager;
    private String nombre;
    private int foto;
    private String rating;
    private ListView lvItems;
    private Adaptador adaptador;
    public ArrayList<Entidad2> lista;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_rating);

        Toolbar toolbar1 = (Toolbar)findViewById(R.id.toolbar);
        toolbar1.setTitle("Top 5 Rating");

        recycler=(RecyclerView)findViewById(R.id.reciclador);
        recycler.setHasFixedSize(true);

        lmanager=new LinearLayoutManager(this);
        recycler.setLayoutManager(lmanager);

        adapter = new RatingAdaptador(ObtenerRating());
        recycler.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu1,menu);

        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        lista = new ArrayList<Entidad2>();
        int foto1 = 100;
        String nombre1;
        String rating1;
        int id = item.getItemId();
        if (id == R.id.atras) {


            Intent intent = new Intent(RecyclerRating.this, MainActivity.class);


            startActivity(intent);
       }
        return true;
    }
    public ArrayList<Entidad2> ObtenerRating() {

        ArrayList<Entidad2> mascotasrat=new ArrayList<>();
        ConexionBD conn = new ConexionBD(this,"mascota",null,1);
        SQLiteDatabase db = conn.getReadableDatabase();
        String query = "SELECT * FROM "+ Utilidades.TABLA_MASCOTA;

        Cursor registros =db.rawQuery(query,null);

        while (registros.moveToNext()){
            Entidad2 EntidadActual = new Entidad2();
            EntidadActual.setNombre(registros.getString(1));
            EntidadActual.setImgFoto(registros.getInt(2));
            EntidadActual.setRating(registros.getInt(3));

            mascotasrat.add(EntidadActual);
        }
        Collections.sort(mascotasrat);

        db.close();

        ArrayList<Entidad2> lista=new ArrayList<>();
        int rat;
        int img;
        String nom;


        for (int i=0;i<5;i++) {
            nom = mascotasrat.get(i).getNombre();
            img = mascotasrat.get(i).getImgFoto();
            rat = mascotasrat.get(i).getRating();
            if (rat > 0) {
                lista.add(new Entidad2(img, nom, rat));
            }
        }
        return lista;
    }


}
