package com.centro.model;


public class ObraSocial {

    private int idObraSocial;
    private String nombreObraSocial;
    private String cuit;
    private String domicilio;
    private String telefono;

    public ObraSocial() {
        this.idObraSocial = 0;
        this.nombreObraSocial = "";
        this.cuit = "";
        this.domicilio = "";
        this.telefono = "";
    }
    
    public ObraSocial(int idObraSocial, String nombreObraSocial, String cuit, String domicilio, String telefono) {
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

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
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