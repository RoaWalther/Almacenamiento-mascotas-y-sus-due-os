package com.example.myappdealmacenamiento.entidades;

import java.io.Serializable;

public class Mascotas implements Serializable {
    private  Integer idDueño;
    private  Integer idMascota;
    private  String nombreMascota;
    private  String raza;

    public Mascotas() {

    }

    public Mascotas(Integer idDueño, Integer idMascota, String nombreMascota, String raza) {
        this.idDueño = idDueño;
        this.idMascota = idMascota;
        this.nombreMascota = nombreMascota;
        this.raza = raza;
    }

    public Integer getIdDueño() {
        return idDueño;
    }

    public void setIdDueño(Integer idDueño) {
        this.idDueño = idDueño;
    }

    public Integer getIdMascota() {
        return idMascota;
    }

    public void setIdMascota(Integer idMascota) {
        this.idMascota = idMascota;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }


}

