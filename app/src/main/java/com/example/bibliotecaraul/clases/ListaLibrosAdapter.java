package com.example.bibliotecaraul.clases;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bibliotecaraul.R;

import java.util.ArrayList;

public class ListaLibrosAdapter extends RecyclerView.Adapter<LibroViewHolder> {
    private Context c;
    private ArrayList<Libro> listaLibros;
    private  ArrayList<FotoLibro> listaFotosLibros;
    private LayoutInflater mInflater;

    public ListaLibrosAdapter(Context c, ArrayList<Libro> listaLibros, ArrayList<FotoLibro> fotosLibros) {
        this.c = c;
        this.listaLibros = listaLibros;
        this.listaFotosLibros = fotosLibros;
        mInflater = LayoutInflater.from(c);
    }

    public Context getC() { return c; }

    public void setC(Context c) {this.c = c; }

    public ArrayList<Libro> getListaLibros() { return listaLibros; }

    public void setListaLibros(ArrayList<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @NonNull
    @Override
    public LibroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.item_recyclerview_libros, parent, false);
        return new LibroViewHolder(mItemView, this);
    }
    @Override
    public void onBindViewHolder(@NonNull LibroViewHolder holder, int position) {
        Libro libroActual = listaLibros.get(position);
        holder.txt_rv_nombreL.setText("Libro: " + libroActual.getNombre());
        holder.txt_rv_Descripcion.setText(String.valueOf("Descripcion: " + libroActual.getDescripcion()));
        holder.txt_rv_Categoria.setText(String.valueOf("Categoria: " + libroActual.getIdCategoria()));
        Bitmap fotobm = libroActual.getFoto();
        if (fotobm != null) {
            holder.img_libro.setImageBitmap(fotobm);
        }

    }

    @Override
    public int getItemCount() {
        return listaLibros.size();
    }
}
