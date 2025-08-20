/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.shared;

/**
 *
 * @author porto
 */
public class Cliente extends Usuario {

    private String cedula;
    private String telefono;
    private double creditoRestante;




    public Cliente(String cedula, String telefono, double creditoRestante, int idUsuario, String nombre, String rol, String correo, String clave) {
        super(idUsuario, nombre, rol, correo, clave);
        rol = "Cliente";
        this.cedula = cedula;
        this.telefono = telefono;
        this.creditoRestante = creditoRestante;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public double getCreditoRestante() {
        return creditoRestante;
    }

    public void setCreditoRestante(double creditoRestante) {
        this.creditoRestante = creditoRestante;
    }




}

