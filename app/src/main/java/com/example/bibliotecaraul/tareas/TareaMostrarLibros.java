package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.modelos.CategoriaDB;

import java.util.concurrent.Callable;

public class TareaMostrarLibros  implements Callable<Boolean> {

    private Categoria c = null;

    public TareaMostrarLibros(Categoria c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK = CategoriaDB.insertarCategoriaTabla(c);
        return insertadoOK;
    }
}
