package com.example.bibliotecaraul.tareas;

import com.example.bibliotecaraul.clases.FotoLibro;
import com.example.bibliotecaraul.modelos.LibroDB;

import java.util.ArrayList;
import java.util.concurrent.Callable;

public class TareaObtenerFotosLibros implements Callable<ArrayList<FotoLibro>> {

    private int width;
    private int height;

    public TareaObtenerFotosLibros(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public ArrayList<FotoLibro> call() throws Exception {
        ArrayList<FotoLibro> fotosLibros = LibroDB.obtenerFotosLibros(this.width, this.height);
        return fotosLibros;
    }
}
