package com.example.bibliotecaraul.controladores;

import com.example.bibliotecaraul.clases.FotoLibro;
import com.example.bibliotecaraul.tareas.TareaObtenerFotosLibros;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class FotoCiudadController {
    public static ArrayList<FotoLibro> obtenerFotosLibros() {

        ArrayList<FotoLibro> fotosLibro = null;
        FutureTask t = new FutureTask (new TareaObtenerFotosLibros(100,100));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            fotosLibro= (ArrayList<FotoLibro>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return fotosLibro;
    }
}
