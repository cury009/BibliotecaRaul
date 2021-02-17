package com.example.bibliotecaraul.clases;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.utilidades.MostrarLibrosActivity;

public class LibroViewHolder  extends RecyclerView.ViewHolder implements View.OnClickListener {
    public static final String EXTRA_OBJETO_LIBRO = "com.example.bibliotecaraul.objetolibro";
    public TextView txt_rv_nombreL = null;
    public TextView txt_rv_Descripcion = null;
    public TextView txt_rv_Categoria = null;
    public ImageView img_libro = null;

    public ListaLibrosAdapter lcAdapter;

    public LibroViewHolder(@NonNull View itemView, ListaLibrosAdapter lcAdapter) {
        super(itemView);
        txt_rv_nombreL = (TextView)  itemView.findViewById(R.id.txt_rv_nombreL);
        txt_rv_Descripcion = (TextView)  itemView.findViewById(R.id.txt_rv_descripcion);
        txt_rv_Categoria = (TextView)  itemView.findViewById(R.id.txt_rv_categoria);
        img_libro = (ImageView)  itemView.findViewById(R.id.img_libro);
        this.lcAdapter = lcAdapter;
        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        // Get the position of the item that was clicked.
        int mPosition = getLayoutPosition();
        // Use that to access the affected item in mWordList.
        Libro libro = this.lcAdapter.getListaLibros().get(mPosition);
        // Change the word in the mWordList.
        // Log.i("ciudad","has seleccionado: " + ciudad.toString());
        //  Toast.makeText(lcAdapter.getC(), "has seleccionado: " + ciudad.toString(),Toast.LENGTH_SHORT).show();
        // Notify the adapter, that the data has changed so it can
        // update the RecyclerView to display the data.
        lcAdapter.notifyDataSetChanged();
        Intent intent = new Intent(lcAdapter.getC(), MostrarLibrosActivity.class);
        // ArrayList<FotoCiudad>  listaFotosCiudades = this.lcAdapter.getListaFotosCiudades();
        intent.putExtra(EXTRA_OBJETO_LIBRO, libro);
        lcAdapter.getC().startActivity(intent);
    }
}
