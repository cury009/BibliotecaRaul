package com.example.bibliotecaraul.utilidades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.controladores.CategoriaController;

import java.util.ArrayList;

public class BorrarCategoriaActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {

    Spinner sp_borrarc = null;
    Categoria cseleccionada = null;
    ArrayAdapter<Categoria> adapter = null;
    ArrayList<Categoria> categorias = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrar_categoria);
        sp_borrarc = (Spinner) findViewById(R.id.sp_categoria);
        if(sp_borrarc != null) {
            sp_borrarc.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
            categorias = CategoriaController.obtenerCategorias();
            if(categorias != null) {
                adapter = new ArrayAdapter<Categoria>(this, R.layout.item_categoria, categorias);
                sp_borrarc.setAdapter(adapter);
            }
        }
    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
    public void borrarCategoriaActivity(View view) {

        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("borrar la categoria?");
        //alerta1.setMessage(" no -> cancelar, si-> guardar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(cseleccionada == null)
                {
                    mostrarToast("selecciona una categoria");
                    return;
                }
                //borrar provincia
                boolean borradoOK = CategoriaController.borrarCategoria(cseleccionada);
                // recargar combo (o desde la base de datos volver a solicitar todo, o quitar solamente el item borrado)
                if(borradoOK)
                {
                    mostrarToast("categoria borrada correctamente");
                    // opcion 1, leemos todas las provincias de la base de datos otra vez
                    adapter.clear();
                    categorias = CategoriaController.obtenerCategorias();
                    adapter.addAll(categorias);
                    //  adapter.add(new Provincia("provincia3"));
                    // opcion 2, borramos del adaptador la provincia borrada
                    //  adapter.remove(pseleccionada);
                }
                else{
                    mostrarToast("la categoria no se pudo borrar");
                }
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta1.show();


    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Categoria c = (Categoria) sp_borrarc.getItemAtPosition(position);
        cseleccionada = c;
        //  Toast.makeText(this, p.getNombre(), Toast.LENGTH_SHORT).show();
    }


    public void onNothingSelected(AdapterView<?> parent) {

    }
}