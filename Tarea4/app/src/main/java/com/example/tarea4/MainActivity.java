package com.example.tarea4;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tarea4.db.dbHelper;

public class MainActivity extends AppCompatActivity {

    EditText nombre,cedula;
    Button btn_guardar, btn_mostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nombre=findViewById(R.id.nombre);
        cedula=findViewById(R.id.cedula);

        btn_guardar=findViewById(R.id.btn_guardar);
        btn_mostrar=findViewById(R.id.btn_mostrar);

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardar(nombre.getText().toString(),cedula.getText().toString());
            }
        });
        btn_mostrar.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Listado.class));
            }
        });
    }

    private void guardar (String Nombre, String cedula){
        dbHelper helper = new dbHelper(this,"demo",null,1);
        SQLiteDatabase db=helper.getWritableDatabase();
        try{
            ContentValues c=new ContentValues();
            c.put("Nombre",Nombre);
            c.put("Cedula",cedula);
            db.insert("Persona",null,c);
            Toast.makeText(this,"Registro insertado",Toast.LENGTH_SHORT).show();
        }catch(Exception e){
            Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();
        }

    }
}