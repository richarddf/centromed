package com.centro.model;


import java.util.Date;


public class Paciente extends Persona {

    private String codigoAfiliadoObraSocial;
    private String planObraSocial;
    private ObraSocial obraSocial;

    public Paciente(String codigoAfiliadoObraSocial, String planObraSocial, ObraSocial obraSocial, String apellidoYNombre, String domicilio, String tipoDocumento, int numeroDocumento, Date fechaNacimiento, String telefono, int idPersona, String nacionalidad, int sexo, String email) {
        super(apellidoYNombre, domicilio, tipoDocumento, numeroDocumento, fechaNacimiento, telefono, idPersona, nacionalidad, sexo, email);
        this.codigoAfiliadoObraSocial = codigoAfiliadoObraSocial;
        this.planObraSocial = planObraSocial;
        this.obraSocial = obraSocial;
    }

    public String getCodigoAfiliadoObraSocial() {
        return codigoAfiliadoObraSocial;
    }

    public void setCodigoAfiliadoObraSocial(String codigoAfiliadoObraSocial) {
        this.codigoAfiliadoObraSocial = codigoAfiliadoObraSocial;
    }

    public String getPlanObraSocial() {
        return planObraSocial;
    }

    public void setPlanObraSocial(String planObraSocial) {
        this.planObraSocial = planObraSocial;
    }

    public ObraSocial getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(ObraSocial obraSocial) {
        this.obraSocial = obraSocial;
    }

}