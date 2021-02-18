package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.modelos.CategoriaDB;
import com.example.bibliotecaraul.modelos.LibroDB;

import java.util.concurrent.Callable;

public class TareaBorrarCategoria implements Callable<Boolean> {
    private Categoria c = null;

    public TareaBorrarCategoria(Categoria c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOK = CategoriaDB.borrarCategoriaTabla(c);
        return borradoOK;
    }
}
