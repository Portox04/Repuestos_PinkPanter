/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.shared;

import java.io.Serializable;

/**
 *
 * @author porto
 */
public class Usuario implements Serializable {

    private int idUsuario;
    private String nombre;
    private String rol;
    private String correo;
    private String clave;

    // Métodos internos de autenticación y verificación de rol
    private boolean autenticar(String passwordRequest) {
        return passwordRequest.contentEquals(clave);
    }

    private boolean verifyRol(String roles) {
        return roles.contentEquals(rol);
    }

    // Constructor completo
    public Usuario(int idUsuario, String nombre, String rol, String correo, String clave) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.rol = rol;
        this.correo = correo;
        this.clave = clave;
    }

    // Getter adicional para compatibilidad con UsuarioDAO
    public int getId() {
        return idUsuario;
    }

    // Getters y setters estándar
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public boolean login(String passwordRequest) {
        return autenticar(passwordRequest);
    }
}
