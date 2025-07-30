/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import java.io.Serializable;

/**
 *
 * @author HP
 */
public class DetalleVenta implements Serializable{

    private Producto producto;
    private int cantidad;
    private double precioUnitario;

    public double calcularSubtotal() {
        return cantidad * precioUnitario;

    }

}
