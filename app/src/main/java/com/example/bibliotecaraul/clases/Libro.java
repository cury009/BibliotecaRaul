package com.example.bibliotecaraul.clases;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.sql.Blob;
import java.util.Objects;

public class Libro implements Serializable {
    private int idLibro;
    private String nombre;
    private String descripcion;
    private int idCategoria;
    private Bitmap foto;

    public Libro(int idLibro, String nombre, String descripcion, int idCategoria ) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
    }
    public Libro(int idLibro, String nombre, String descripcion, int idCategoria, Bitmap foto ) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
        this.foto = foto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public Libro() {
        this.idLibro = 0;
        this.nombre = "";
        this.descripcion = "";
        this.idCategoria = 1;
        this.foto = null;
    }
    public Libro(String nombre, String descripcion, int idCategoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.idCategoria = idCategoria;
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", idCategoria=" + idCategoria +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return idLibro == libro.idLibro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idLibro);
    }
}
