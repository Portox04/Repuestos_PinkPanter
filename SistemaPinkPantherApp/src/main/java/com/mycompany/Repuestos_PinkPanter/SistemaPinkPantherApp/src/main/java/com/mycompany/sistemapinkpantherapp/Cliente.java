/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

/**
 *
 * @author HP
 */
public class Cliente extends Usuario{

    private String cedula;
    private String telefono;
    private double creditoRestante;
    
    int idCliente = 0;

    public Cliente(String cedula, String telefono, double creditoRestante, int idUsuario, String nombre, String rol, String correo, String clave) {
        super(idUsuario, nombre, rol, correo, clave);
        this.idCliente = idCliente+1;
        this.cedula = cedula;
        this.telefono = telefono;
        this.creditoRestante = creditoRestante;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
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
