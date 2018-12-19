package com.centro.model;


public class Usuario {

    private int idUsuario;
    private Persona persona;
    private String password;

    public Usuario(int idUsuario, Persona persona, String password) {
        this.idUsuario = idUsuario;
        this.persona = persona;
        this.password = password;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    
    public void mostrarUsuario() {
            throw new UnsupportedOperationException();
    }

    public void verificarUsuario() {
            throw new UnsupportedOperationException();
    }

}