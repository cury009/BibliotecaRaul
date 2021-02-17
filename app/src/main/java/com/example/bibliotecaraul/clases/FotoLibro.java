package com.example.bibliotecaraul.clases;

import android.graphics.Bitmap;

import java.util.Objects;

public class FotoLibro {

    private int idfoto;
    private Bitmap foto;
    private int idlibro;

    public FotoLibro(int idfoto, Bitmap foto, int idlibro) {
        this.idfoto = idfoto;
        this.foto = foto;
        this.idlibro = idlibro;
    }

    public int getIdfoto() {
        return idfoto;
    }

    public void setIdfoto(int idfoto) {
        this.idfoto = idfoto;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }

    public int getIdlibro() {
        return idlibro;
    }

    public void setIdlibro(int idlibro) {
        this.idlibro = idlibro;
    }

    @Override
    public String toString() {
        return "FotoLibro{" +
                "idfoto=" + idfoto +
                ", foto=" + foto +
                ", idlibro=" + idlibro +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FotoLibro fotoLibro = (FotoLibro) o;
        return idfoto == fotoLibro.idfoto &&
                idlibro == fotoLibro.idlibro &&
                foto.equals(fotoLibro.foto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idfoto, foto, idlibro);
    }
}
