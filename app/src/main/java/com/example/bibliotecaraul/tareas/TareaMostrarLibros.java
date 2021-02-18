package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.modelos.CategoriaDB;

import java.util.concurrent.Callable;

public class TareaMostrarLibros  implements Callable<Boolean> {

    private Categoria c = null;

    public TareaMostrarLibros() {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        boolean insertadoOK = CategoriaDB.insertarCategoriaTabla(c);
        return insertadoOK;
    }
}
