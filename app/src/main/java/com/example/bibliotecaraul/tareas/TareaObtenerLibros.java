package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.modelos.LibroDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerLibros  implements Callable<ArrayList<Libro>> {
    @Override
    public ArrayList<Libro> call() throws Exception {
        ArrayList<Libro> libros = LibroDB.obtenerLibro();
        return libros;
    }
}
