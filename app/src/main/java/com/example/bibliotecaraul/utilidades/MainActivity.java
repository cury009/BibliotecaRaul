package com.example.bibliotecaraul.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.bibliotecaraul.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void nuevaCategoria(View view) {
        Intent intent = new Intent(this, NuevaCategoriaActivity.class);
        startActivity(intent);
    }

    public void borrarCategoria(View view) {
        Intent intent = new Intent(this,  BorrarCategoriaActivity.class);
        startActivity(intent);
    }

    public void mostrarLibros(View view) {
        Intent intent = new Intent(this, MostrarLibrosActivity.class);
        startActivity(intent);
    }
    public void actualizarCategoria(View view) {
        Intent intent = new Intent(this, ActualizarCategoriaActivity1.class);
        startActivity(intent);
    }
}