package com.example.bibliotecaraul.modelos;

import android.util.Log;

import com.example.bibliotecaraul.clases.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CategoriaDB {
    //----------------------------------------------------------....
    public static boolean insertarCategoriaTabla(Categoria c)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "INSERT INTO categoria (nombre) VALUES (?);";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, c.getNombre());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
    //-----------------------------------------------------------
    public static Categoria buscarCategoriaTabla(String nombre)
    {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        //---------------------------------
        Categoria categoriaEncontrada = null;
        try {
            ResultSet resultadosql = BaseDB.buscarFilasEnTabla(conexion, "categoria", "nombre", nombre);
            //------------------------------------------------
            if(resultadosql == null)
            {
                return null;
            }
            while(resultadosql.next())
            {
                int idCategoria = resultadosql.getInt("idCategoria");
                String nombreLibro = resultadosql.getString("nombre");
                categoriaEncontrada = new Categoria(idCategoria,nombreLibro);
            }
            resultadosql.close();
            conexion.close();
            return categoriaEncontrada;
        } catch (SQLException e) {
            return null;
        }
    }

    public static ArrayList<Categoria> obtenerCategoria() {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return null;
        }
        ArrayList<Categoria> categoriasDevueltas = new ArrayList<Categoria>();
        try {
            Statement sentencia = conexion.createStatement();
            String ordenSQL = "select * from categorias";
            ResultSet resultado = sentencia.executeQuery(ordenSQL);
            while(resultado.next())
            {
                int idCategoria = resultado.getInt("idCategoria");
                String nombre = resultado.getString("nombre");
                Categoria c = new Categoria(idCategoria, nombre);
                categoriasDevueltas.add(c);
            }
            resultado.close();
            sentencia.close();
            conexion.close();
            return categoriasDevueltas;
        } catch (SQLException e) {
            Log.i("sql", "error sql");
            return categoriasDevueltas;
        }
    }

    public static boolean borrarCategoriaTabla(Categoria c) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "DELETE FROM categoria WHERE nombre LIKE ?;";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, c.getNombre());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public static boolean actualizarCategoriaTabla(Categoria p) {
        Connection conexion = BaseDB.conectarConBaseDeDatos();
        if(conexion == null)
        {
            return false;
        }
        //----------------------------
        try {
            String ordensql = "UPDATE categorias SET nombre = ? WHERE idCategoria = ?";
            PreparedStatement pst = conexion.prepareStatement(ordensql);
            pst.setString(1, p.getNombre());
            pst.setInt(2, p.getIdCategoria());
            int filasAfectadas = pst.executeUpdate();
            pst.close();
            conexion.close();
            if(filasAfectadas > 0)
            {
                return true;
            }
            else {
                return false;
            }
        } catch (SQLException e) {
            return false;
        }
    }
//--------------------------------------------------------------
}
