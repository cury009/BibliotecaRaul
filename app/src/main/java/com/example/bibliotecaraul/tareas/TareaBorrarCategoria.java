package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.modelos.LibroDB;

import java.util.concurrent.Callable;

public class TareaBorrarCategoria implements Callable<Boolean> {
    private Libro l = null;

    public TareaBorrarCategoria(Libro l) {
        this.l = l;
    }

    @Override
    public Boolean call() throws Exception {
        boolean borradoOK = LibroDB.borrarLibroTabla(l);
        return borradoOK;
    }
}
