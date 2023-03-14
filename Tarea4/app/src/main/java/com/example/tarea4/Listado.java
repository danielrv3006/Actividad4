package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.tarea4.db.dbHelper;

import java.util.ArrayList;

public class Listado extends AppCompatActivity {

    ListView listView;
    ArrayList<String> listado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado);
        listView = findViewById(R.id.listView);
        CargarListado();
    }

    private void CargarListado() {
        listado = ListaPersona();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listado);
        listView.setAdapter(adapter);
    }

    private ArrayList<String> ListaPersona() {
        ArrayList<String> datos = new ArrayList<String>();
        dbHelper helper = new dbHelper(this, "demo", null, 1);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select Id,Nombre, cedula from persona";
        Cursor c=db.rawQuery(sql,null);
        if(c.moveToFirst()){
            do{
                String linea=c.getInt(0)+" "+ c.getString(1)+" "+c.getString(2);
                datos.add(linea);
            }while(c.moveToNext());
        }
        db.close();
        return datos;
}
}