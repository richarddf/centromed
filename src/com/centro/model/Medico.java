package com.centro.model;


import java.util.Date;


public class Medico extends Persona {

    private String profesion;
    private String cuit;
    private int numeroMatricula;
    private String categoriaIva;

   public String getProfesion() {
        return profesion;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public String getCuit() {
        return cuit;
    }

    public void setCuit(String cuit) {
        this.cuit = cuit;
    }

    public int getNumeroMatricula() {
        return numeroMatricula;
    }

    public void setNumeroMatricula(int numeroMatricula) {
        this.numeroMatricula = numeroMatricula;
    }

    public String getCategoriaIva() {
        return categoriaIva;
    }

    public void setCategoriaIva(String categoriaIva) {
        this.categoriaIva = categoriaIva;
    }

    public Medico() {
        super();
        this.profesion = "";
        this.cuit = "";
        this.numeroMatricula = 0;
        this.categoriaIva = "";
    }    
    public Medico(String profesion, String cuit, int numeroMatricula, String categoriaIva, String apellidoYNombre, String domicilio, String tipoDocumento, int numeroDocumento, Date fechaNacimiento, String telefono, int idPersona, String nacionalidad, String sexo, String email) {
        super(apellidoYNombre, domicilio, tipoDocumento, numeroDocumento, fechaNacimiento, telefono, idPersona, nacionalidad, sexo, email);
        this.profesion = profesion;
        this.cuit = cuit;
        this.numeroMatricula = numeroMatricula;
        this.categoriaIva = categoriaIva;
    }

    public void mostrarMedico() {
        super.mostrarPersona();
        System.out.println("Profesión: "+this.getProfesion());
        System.out.println("CUIT: "+this.getCuit());
        System.out.println("Número Matricula: "+this.getNumeroMatricula());
        System.out.println("Categoría Iva: "+this.getCategoriaIva());
    }
}