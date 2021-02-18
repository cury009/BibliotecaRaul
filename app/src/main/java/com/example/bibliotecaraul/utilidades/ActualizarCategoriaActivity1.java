package com.example.bibliotecaraul.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.controladores.CategoriaController;

import java.util.ArrayList;

public class ActualizarCategoriaActivity1 extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    public static final String EXTRA_OBJETO_CATEGORIA = "com.example.bibliotecaRaul";
    Spinner sp_actualizarc = null;
    static Categoria cseleccionada = null;
    static ArrayAdapter<Categoria> adapter = null;
    ArrayList<Categoria> categorias = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_categoria1);
        sp_actualizarc = (Spinner) findViewById(R.id.sp_actualizarc);
        if(sp_actualizarc != null) {
            sp_actualizarc.setOnItemSelectedListener(this);
            categorias = CategoriaController.obtenerCategorias();
            if(categorias != null) {
                adapter = new ArrayAdapter<Categoria>(this, R.layout.item_categoria, categorias);
                sp_actualizarc.setAdapter(adapter);
            }
        }
    }

    public void siguienteactualizarCategoria(View view) {
        Intent intent = new Intent(this, ActualizarCategoriaActivity2.class);
        intent.putExtra(EXTRA_OBJETO_CATEGORIA, cseleccionada);
        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Categoria c = (Categoria) sp_actualizarc.getItemAtPosition(position);
        cseleccionada = c;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}