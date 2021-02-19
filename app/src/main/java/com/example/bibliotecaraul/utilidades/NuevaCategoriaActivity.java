package com.example.bibliotecaraul.utilidades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.controladores.CategoriaController;

public class NuevaCategoriaActivity extends AppCompatActivity {

    EditText edt_nombrec = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nueva_categoria);
        edt_nombrec = findViewById(R.id.edt_nombrec);
    }

    public void insertarCategoria(View view) {
        String nombrec = String.valueOf(edt_nombrec.getText());
        if(nombrec.isEmpty())
        {
            edt_nombrec.setError("escribe algo en el nombre de la categoria");
            return;
        }
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("guardar la categoria?");
        //alerta1.setMessage(" no -> cancelar, si-> guardar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Categoria c = new Categoria(nombrec);
                boolean insercionOK = CategoriaController.insertarCategoria(c);
                mostrarToast(insercionOK);
            }
        });
        alerta1.setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        alerta1.show();

    }

    public void mostrarToast(boolean insercionOK)
    {
        if(insercionOK)
        {
            Toast.makeText(this,"provincia guardada correctamente", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(this,"No se pudo guardar la provincia", Toast.LENGTH_SHORT).show();
        }
    }
}