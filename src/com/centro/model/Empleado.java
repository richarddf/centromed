package com.centro.model;


import java.util.Date;


public class Empleado extends Persona {

    private String cargo;

    public Empleado(String cargo, String apellidoYNombre, String domicilio, String tipoDocumento, int numeroDocumento, Date fechaNacimiento, String telefono, int idPersona, String nacionalidad, int sexo, String email) {
        super(apellidoYNombre, domicilio, tipoDocumento, numeroDocumento, fechaNacimiento, telefono, idPersona, nacionalidad, sexo, email);
        this.cargo = cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void mostrarEmpleado() {
            throw new UnsupportedOperationException();
    }

}