package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.modelos.LibroDB;

import java.util.concurrent.Callable;

public class TareaInsertarLibros  implements Callable<Boolean> {
    private Libro l = null;

    public TareaInsertarLibros(Libro l) {
        this.l = l;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK = LibroDB.insertarLibroTabla(l);
        return insertadoOK;
    }
}
