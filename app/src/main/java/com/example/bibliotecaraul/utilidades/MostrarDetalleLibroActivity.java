package com.example.bibliotecaraul.utilidades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.clases.Libro;

public class MostrarDetalleLibroActivity extends AppCompatActivity {
    TextView txt_detalle_nombrec = null;
    TextView txt_detalle_descripcion = null;
    TextView txt_detalle_idcategoria = null;
    ImageView img_detalle_libro= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles_libro);
        txt_detalle_nombrec = findViewById(R.id.txt_detalles_nombreL);
        txt_detalle_descripcion = findViewById(R.id.txt_detalles_descripcion);
        txt_detalle_idcategoria = findViewById(R.id.txt_detalle_id_categoria);
        img_detalle_libro = findViewById(R.id.img_detalle_libro);
        Intent intent = getIntent();
        if(intent != null)
        {
            Libro l = (Libro) intent.getSerializableExtra(NuevoLibroActivity.EXTRA_OBJETO_LIBRO);
            txt_detalle_nombrec.setText(l.getNombre());
            txt_detalle_descripcion.setText("descripcion: " + String.valueOf(l.getDescripcion()));
            txt_detalle_idcategoria.setText("idcategoria: " + String.valueOf(l.getIdCategoria()));
        }
    }
}