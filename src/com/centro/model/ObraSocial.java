package com.centro.model;


public class ObraSocial {

    private int idObraSocial;
    private String nombreObraSocial;
    private int cuit;
    private String domicilio;
    private String telefono;

    public ObraSocial(int idObraSocial, String nombreObraSocial, int cuit, String domicilio, String telefono) {
        this.idObraSocial = idObraSocial;
        this.nombreObraSocial = nombreObraSocial;
        this.cuit = cuit;
        this.domicilio = domicilio;
        this.telefono = telefono;
    }

    public int getIdObraSocial() {
        return idObraSocial;
    }

    public void setIdObraSocial(int idObraSocial) {
        this.idObraSocial = idObraSocial;
    }

    public String getNombreObraSocial() {
        return nombreObraSocial;
    }

    public void setNombreObraSocial(String nombreObraSocial) {
        this.nombreObraSocial = nombreObraSocial;
    }

    public int getCuit() {
        return cuit;
    }

    public void setCuit(int cuit) {
        this.cuit = cuit;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void mostrarObraSocial() {
            throw new UnsupportedOperationException();
    }

}