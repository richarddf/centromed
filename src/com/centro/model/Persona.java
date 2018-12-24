package com.centro.model;


import java.text.SimpleDateFormat;
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
    private String sexo;
    private String email;

    public Persona() {
        this.apellidoYNombre = "";
        this.domicilio = "";
        this.tipoDocumento = "";
        this.numeroDocumento = 0;
        this.fechaNacimiento = null;
        this.telefono = "";
        this.idPersona = 0;
        this.nacionalidad = "";
        this.sexo = "";
        this.email = "";
    } 
    
    public Persona(String apellidoYNombre, String domicilio, String tipoDocumento, int numeroDocumento, Date fechaNacimiento, String telefono, int idPersona, String nacionalidad, String sexo, String email) {
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

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void mostrarPersona() {
        System.out.println("Id: "+this.getIdPersona());
        System.out.println("Apellido Y Nombre: "+this.getApellidoYNombre());
        System.out.println("Domicilio: "+this.getDomicilio());
        System.out.println("Tipo Documento: "+this.getTipoDocumento());
        System.out.println("Documento: "+this.getNumeroDocumento());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        System.out.println("Fecha Nacimiento: "+sdf.format(this.getFechaNacimiento()));
        System.out.println("Teléfono: "+this.getTelefono());
        System.out.println("Nacionalidad: "+this.getNacionalidad());
        System.out.println("Sexo: "+this.getSexo());
        System.out.println("Email: "+this.getEmail());
    }
}