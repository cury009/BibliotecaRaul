package com.example.bibliotecaraul.modelos;

import android.graphics.Bitmap;
import android.util.Log;

import com.example.bibliotecaraul.clases.FotoLibro;
import com.example.bibliotecaraul.clases.Libro;
import com.example.bibliotecaraul.utilidades.ImagenesBlobBitmap;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class LibroDB {
    public static ArrayList<Libro> obtenerLibro() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        ArrayList<Libro> librosDevueltas = new ArrayList<Libro>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from libro";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                int idLibro = resultado.getInt("idLibro");
                String nombre = resultado.getString("nombre");
                String descripcion = resultado.getString("descripcion");
                int idCategoria = resultado.getInt("idCategoria");
                Libro l = new Libro(idLibro, nombre, descripcion, idCategoria);
                librosDevueltas.add(l);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return librosDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }

    public static boolean insertarLibroTabla(Libro l) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO libro (nombre, descripcion,idCategoria) VALUES (?, ?, ?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, l.getNombre());
            pst.setString(2, l.getDescripcion());
            pst.setInt(3, l.getIdCategoria());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    //-----------------------------------------------------------
    public static boolean borrarLibroTabla(Libro l) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "DELETE FROM libro WHERE idlibro = ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setInt(1, l.getIdLibro());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    //---------------------------------------------------------------
    public static boolean actualizarLibroTabla(Libro l) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "UPDATE libro SET nombre = ?, descripcion = ?, idCategoria = ? WHERE (idlibro = ?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, l.getNombre());
            pst.setString(2, l.getDescripcion());
            pst.setInt(3, l.getIdCategoria());
            pst.setInt(4, l.getIdLibro());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if (filasAfectadas > 0) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
    public static Libro buscarLibroTabla(String nombre) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        //---------------------------------
        Libro libroEncontrada = null;
        try {
            String ordensql = "select * from libro where nombre like ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, nombre);
            ResultSet resultadosql = pst.executeQuery();
            //------------------------------------------------
            while (resultadosql.next()) {
                int idLibro = resultadosql.getInt("idLibro");
                String nombreLibro = resultadosql.getString("nombre");
                String descripcion = resultadosql.getString("descripcion");
                int idCategoria = resultadosql.getInt("idCategoria");
                libroEncontrada = new Libro(idLibro, nombreLibro, descripcion, idCategoria);
            }
            resultadosql.close();
            pst.close();
            conexion.close();
            return libroEncontrada;
        } catch (SQLException e) {
            return null;
        }
    }

    public static ArrayList<FotoLibro> obtenerFotosLibros(int width, int height) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if (conexion == null) {
            return null;
        }
        ArrayList<FotoLibro> fotosCiudadesDevueltas = new ArrayList<FotoLibro>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from fotos_libros";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while (resultado.next()) {
                int idfoto = resultado.getInt("idfoto");
                Blob foto = resultado.getBlob("foto");
                Bitmap bm_foto = ImagenesBlobBitmap.blob_to_bitmap(foto, width, height);
                int idlibro = resultado.getInt("idlibro");
                FotoLibro fl = new FotoLibro(idfoto, bm_foto, idlibro);
                fotosCiudadesDevueltas.add(fl);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return fotosCiudadesDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return null;
        }
    }
}
