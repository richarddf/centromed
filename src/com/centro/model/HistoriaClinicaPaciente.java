package com.centro.model;


public class HistoriaClinicaPaciente {

    private Paciente paciente;
    private String Contenido;

    public HistoriaClinicaPaciente(Paciente paciente, String Contenido) {
        this.paciente = paciente;
        this.Contenido = Contenido;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getContenido() {
        return Contenido;
    }

    public void setContenido(String Contenido) {
        this.Contenido = Contenido;
    }

    public void mostrarHistoria() {
            throw new UnsupportedOperationException();
    }

}