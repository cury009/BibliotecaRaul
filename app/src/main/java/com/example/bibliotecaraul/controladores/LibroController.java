package com.example.bibliotecaraul.controladores;

import android.widget.TextView;

import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.tareas.TareaBorrarLibro;
import com.example.bibliotecaraul.tareas.TareaInsertarLibros;
import com.example.bibliotecaraul.tareas.TareaMostrarLibros;
import com.example.bibliotecaraul.tareas.TareaObtenerLibros;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class LibroController {

    public static ArrayList<Libro> obtenerLibros()
    {
        ArrayList<Libro> librosDevueltas = null;
        FutureTask t = new FutureTask ((Callable) new TareaObtenerLibros());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            librosDevueltas= (ArrayList<Libro>)t.get();
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
        return librosDevueltas;
    }
    //---------------------------------------------------------------------------
    //-----------------------------------------------------------------------------
    public static void MostrarLibros(TextView txt_libros)
    {
        FutureTask t = new FutureTask ((Callable) new TareaMostrarLibros());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            ArrayList<Libro> librosDevueltas= (ArrayList<Libro>)t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
            String texto_libros ="LIBROS \n";
            if(librosDevueltas != null) {
                for (Libro l: librosDevueltas) {
                    texto_libros += l.toString() + "\n";
                }
                txt_libros.setText(texto_libros);
            }
            else {
                txt_libros.setText("no se recuperaron las libros");
            }
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //---------------------------------------------------------------------------
    public static boolean InsertarLibro(Libro l)
    {
        FutureTask t = new FutureTask(new TareaInsertarLibros(l));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean insercionOK = false;
        try {
            insercionOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return insercionOK;
        }
    }

    public static boolean borrarLibro(Libro lseleccionada) {
        FutureTask t = new FutureTask((Callable) new TareaBorrarLibro(lseleccionada));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean borradoOK = false;
        try {
            borradoOK = (boolean) t.get();
            es.shutdown();
            try {
                if (!es.awaitTermination(800, TimeUnit.MILLISECONDS)) {
                    es.shutdownNow();
                }
            } catch (InterruptedException e) {
                es.shutdownNow();
            }
        } catch (
                ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finally {
            return borradoOK;
        }
    }
}
