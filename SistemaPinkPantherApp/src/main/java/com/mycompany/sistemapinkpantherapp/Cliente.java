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

    private int idCliente;
    private String nombre;
    private String cedula;
    private String telefono;
    private double creditoRestante;

    public Cliente(int idUsuario, String nombre, String rol, String correo, String clave) {
        super(idUsuario, nombre, rol, correo, clave);
    }

}
