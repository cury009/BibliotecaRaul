package com.example.bibliotecaraul.controladores;

import com.example.bibliotecaraul.clases.Categoria;
import com.example.bibliotecaraul.tareas.TareaActualizarCategoria;
import com.example.bibliotecaraul.tareas.TareaBorrarCategoria;
import com.example.bibliotecaraul.tareas.TareaInsertarCategoria;
import com.example.bibliotecaraul.tareas.TareaObtenerCategorias;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

public class CategoriaController {
    public static boolean insertarCategoria(Categoria c) {
        FutureTask t = new FutureTask(new TareaInsertarCategoria(c));
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

    //---------------------------------------------------------------------------

    public static ArrayList<Categoria> obtenerCategorias()
    {
        ArrayList<Categoria> categoriasDevueltas = null;
        FutureTask t = new FutureTask (new TareaObtenerCategorias());
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        try {
            categoriasDevueltas= (ArrayList<Categoria>)t.get();
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
        return categoriasDevueltas;
    }
    //---------------------------------------------------------------------------
    public static boolean   borrarCategoria(Categoria c) {
        FutureTask t = new FutureTask(new TareaBorrarCategoria(c));
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

    public static boolean actualizarCategoria(Categoria c) {
        FutureTask t = new FutureTask(new TareaActualizarCategoria(c));
        ExecutorService es = Executors.newSingleThreadExecutor();
        es.submit(t);
        boolean actualizadoOK = false;
        try {
            actualizadoOK = (boolean) t.get();
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
            return actualizadoOK;
        }
    }
}
