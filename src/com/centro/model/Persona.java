package com.centro.model;


import java.util.Date;

public abstract class Persona {

    private String apellidoYNombre;
    private String domicilio;
    private String tipoDocumento;
    private int numeroDocumento;
    private Date fechaNacimiento;
    private String telefono;
    private int idPersona;
    private String nacionalidad;
    private int sexo;
    private String email;

    public Persona(String apellidoYNombre, String domicilio, String tipoDocumento, int numeroDocumento, Date fechaNacimiento, String telefono, int idPersona, String nacionalidad, int sexo, String email) {
        this.apellidoYNombre = apellidoYNombre;
        this.domicilio = domicilio;
        this.tipoDocumento = tipoDocumento;
        this.numeroDocumento = numeroDocumento;
        this.fechaNacimiento = fechaNacimiento;
        this.telefono = telefono;
        this.idPersona = idPersona;
        this.nacionalidad = nacionalidad;
        this.sexo = sexo;
        this.email = email;
    }
    public String getApellidoYNombre() {
        return apellidoYNombre;
    }

    public void setApellidoYNombre(String apellidoYNombre) {
        this.apellidoYNombre = apellidoYNombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public int getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(int numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public int getSexo() {
        return sexo;
    }

    public void setSexo(int sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void mostrarPersona() {
            throw new UnsupportedOperationException();
    }

}