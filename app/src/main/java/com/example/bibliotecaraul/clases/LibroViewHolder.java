package com.example.bibliotecaraul.clases;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LibroViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_OBJETO_LIBRO = "com.example.bibliotecaraul.objetolibro";
    public TextView txt_rv_nombreL;
    public TextView txt_rv_Descripcion;
    public TextView txt_rv_Categoria;
    public ListaLibrosAdapter lcAdapter;

    public LibroViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    @Override
    public void onClick(View v) {

    }
}
