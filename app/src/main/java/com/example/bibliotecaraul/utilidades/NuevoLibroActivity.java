package com.example.bibliotecaraul.utilidades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.controladores.CategoriaController;
import com.example.bibliotecaraul.controladores.LibroController;

import java.util.ArrayList;

public class NuevoLibroActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener   {

    public static final String EXTRA_OBJETO_LIBRO = "com.example.bibliotecaraul";
    Spinner sp_nuevol_categoria = null;
    Categoria cseleccionada = null;
    ArrayAdapter<Categoria> adapter = null;
    ArrayList<Categoria> categorias = null;
    EditText edt_nuevol_nombre = null;
    EditText edt_nuevol_descripcion = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_libro);
        edt_nuevol_nombre = findViewById(R.id.edt_nuevol_nombre);
        edt_nuevol_descripcion = findViewById(R.id.edt_nuevol_descripcion);
        sp_nuevol_categoria = (Spinner) findViewById(R.id.sp_nuevoL_categoria);
        if(sp_nuevol_categoria != null) {
            sp_nuevol_categoria.setOnItemSelectedListener(this);
            categorias = CategoriaController.obtenerCategorias();
            if(categorias != null) {
                adapter = new ArrayAdapter<Categoria>(this, R.layout.item_categoria, categorias);
                sp_nuevol_categoria.setAdapter(adapter);
            }
        }
    }

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
    public void insertarLibro(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("quieres guardar el libro?");
        //alerta1.setMessage(" no -> cancelar, si-> guardar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if(cseleccionada == null)
                {
                    mostrarToast("selecciona una categoria");
                    return;
                }
                Libro l = null;
                try{
                    String nombre = String.valueOf(edt_nuevol_nombre.getText());
                    String descripcion = String.valueOf(edt_nuevol_descripcion.getText());
                    l = new Libro(nombre, descripcion, cseleccionada.getIdCategoria());

                }catch (Exception e)
                {
                    mostrarToast("error, revisa los datos introducidos");
                }
                //insertar Libro
                boolean insertadoOK = LibroController.InsertarLibro(l);
                if(insertadoOK)
                {
                    mostrarToast("libro insertada correctamente");
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_OBJETO_LIBRO, l);
                    setResult(RESULT_OK, intent);
                    finish();
                }
                else{
                    mostrarToast("no se pudo crear el libro");
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
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Categoria c = (Categoria) sp_nuevol_categoria.getItemAtPosition(position);
        cseleccionada = c;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}