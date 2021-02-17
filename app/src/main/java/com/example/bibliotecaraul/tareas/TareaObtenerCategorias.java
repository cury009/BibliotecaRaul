package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.modelos.CategoriaDB;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerCategorias implements Callable<ArrayList<Categoria>> {
    @Override
    public ArrayList<Categoria> call() throws Exception {
        ArrayList<Categoria> categorias = CategoriaDB.obtenerCategoria();
        return categorias;
    }
}
