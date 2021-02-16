package com.example.bibliotecaraul.clases;

import java.io.Serializable;
import java.util.Objects;

public class Libro implements Serializable {
    private int idLibro;
    private String nombre;
    private String descripcion;

    public Libro(int idLibro, String nombre, String descripcion) {
        this.idLibro = idLibro;
        this.nombre = nombre;
        this.descripcion = descripcion;
    }

    public Libro() {
        this.idLibro = 0;
        this.nombre = "";
        this.descripcion = "";
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

    @Override
    public String toString() {
        return "Libro{" +
                "idLibro=" + idLibro +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
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
