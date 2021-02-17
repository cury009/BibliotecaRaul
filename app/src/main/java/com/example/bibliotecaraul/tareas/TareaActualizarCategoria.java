package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.modelos.CategoriaDB;

import java.util.concurrent.Callable;

public class TareaActualizarCategoria implements Callable<Boolean> {
    private Categoria c = null;

    public TareaActualizarCategoria(Categoria c) {
        this.c = c;
    }

    @Override
    public Boolean call() throws Exception {
        boolean actualizadoOK = CategoriaDB.actualizarCategoriaTabla(c);
        return actualizadoOK;
    }
}
