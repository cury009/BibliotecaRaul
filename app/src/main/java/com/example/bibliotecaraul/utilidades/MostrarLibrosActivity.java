package com.example.bibliotecaraul.utilidades;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.bibliotecaraul.R;
import com.example.bibliotecaraul.clases.FotoLibro;
import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.clases.ListaLibrosAdapter;
import com.example.bibliotecaraul.controladores.FotoLibrosController;
import com.example.bibliotecaraul.controladores.LibroController;

import java.util.ArrayList;
import java.util.Collections;

public class MostrarLibrosActivity extends AppCompatActivity {

    private static final int PETICION1 = 1;
    private RecyclerView mRecyclerView;
    private ListaLibrosAdapter mAdapter;
    private ArrayList<Libro> libros;
    private ArrayList<FotoLibro> fotosLibros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_libros);
        //-----------------------------------------------------------
        libros = LibroController.obtenerLibros();
        fotosLibros = FotoLibrosController.obtenerFotosLibros();
        if (libros != null) {
            // mostrarToast("se ha estableciod la conexion con la base de datos");
            //  mostrarToast("el número de libros recuperadas es " + libros.size());
            //-------------------------------------------------------
            // Get a handle to the RecyclerView.
            mRecyclerView = findViewById(R.id.rv_libros);
            // Create an adapter and supply the data to be displayed.
            mAdapter = new ListaLibrosAdapter(this, libros, fotosLibros);
            // Connect the adapter with the RecyclerView.
            mRecyclerView.setAdapter(mAdapter);
            // Give the RecyclerView a default layout manager.
            mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            //------------------------------------------------------------
            ItemTouchHelper helper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT |
                    ItemTouchHelper.DOWN | ItemTouchHelper.UP, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                    int from = viewHolder.getAdapterPosition();
                    int to = target.getAdapterPosition();
                    Collections.swap(libros, from, to);
                    mAdapter.notifyItemMoved(from, to);
                    return true;
                }

                @Override
                public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                    if (direction == ItemTouchHelper.LEFT) {
                        mostrarToast("ha pulsado izquierda");
                        // Ciudad c = ciudades.get(viewHolder.getAdapterPosition());
                        // CiudadController.borrarCiudad(c);
                        libros.remove(viewHolder.getAdapterPosition());
                        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                    if (direction == ItemTouchHelper.RIGHT) {
                        mostrarToast("ha pulsado derecha");
                        libros.remove(viewHolder.getAdapterPosition());
                        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                    }
                }

            });
            helper.attachToRecyclerView(mRecyclerView);
        } else {
            mostrarToast("no se pudo establecer la conexion con la base de datos");
        }
    }

    private void mostrarToast(String texto) {
        Toast.makeText(this,texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PETICION1) {
            if (resultCode == RESULT_OK) {
                Libro l = (Libro) data.getSerializableExtra(NuevoLibroActivity.EXTRA_OBJETO_LIBRO);
                libros.add(l);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(libros.size());
                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(libros.size());
            }
        }
    }
    public void nuevosLibro(View view) {
        Intent intent = new Intent(this, NuevoLibroActivity.class);
        startActivityForResult(intent, PETICION1);
    }

    public void refrescarLibros(View view) {
        libros = LibroController.obtenerLibros();
        if(libros != null) {
            mAdapter.setListaLibros(libros);
            mRecyclerView.getAdapter().notifyDataSetChanged();
        }
    }

}
