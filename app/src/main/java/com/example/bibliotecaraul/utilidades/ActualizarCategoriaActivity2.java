package com.example.bibliotecaraul.utilidades;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.controladores.CategoriaController;

import java.util.ArrayList;

public class ActualizarCategoriaActivity2 extends AppCompatActivity {

    Categoria cseleccionada = null;
    EditText edt_actualizar_idc= null;
    EditText edt_actualizar_nombrec = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_categoria2);
        edt_actualizar_idc = (EditText) findViewById(R.id.edt_actualizar_idc);
        edt_actualizar_nombrec = (EditText) findViewById(R.id.edt_actualizar_nombreC);
        Intent intent = getIntent();
        if(intent != null)
        {
            cseleccionada = (Categoria) intent.getSerializableExtra(ActualizarCategoriaActivity1.EXTRA_OBJETO_CATEGORIA);
            if(cseleccionada!=null)
            {
                edt_actualizar_idc.setText(String.valueOf(cseleccionada.getIdCategoria()));
                edt_actualizar_idc.setEnabled(false);
                edt_actualizar_nombrec.setText(cseleccionada.getNombre());
            }
        }
    }
    public void actualizarCategoria(View view) {
        AlertDialog.Builder alerta1 = new AlertDialog.Builder(this);
        alerta1.setTitle("actualizar la categoria?");
        //alerta1.setMessage(" no -> cancelar, si-> actualizar");
        alerta1.setPositiveButton("si", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //actualizar categoria
                int idc = Integer.valueOf(String.valueOf(edt_actualizar_idc.getText()));
                String nombrec = String.valueOf(edt_actualizar_nombrec.getText());
                Categoria c = new Categoria(idc, nombrec);
                boolean actualizarOK = CategoriaController.actualizarCategoria(c);
                // recargar combo (o desde la base de datos volver a solicitar todo, o quitar solamente el item borrado)
                if(actualizarOK)
                {
                    ActualizarCategoriaActivity1.adapter.clear();
                    ArrayList<Categoria> categorias = CategoriaController.obtenerCategorias();
                    ActualizarCategoriaActivity1.adapter.addAll(categorias);
                    mostrarToast("categoria actualizada correctamente");
                }
                else{
                    mostrarToast("la categoria no se pudo actualizar");
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

    public void mostrarToast(String mensaje)
    {
        Toast.makeText(this, mensaje, Toast.LENGTH_SHORT).show();
    }
}