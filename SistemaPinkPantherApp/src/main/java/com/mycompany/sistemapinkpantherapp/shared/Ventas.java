/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.shared;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author porto
 */
public class Ventas implements Serializable {

    private static int contadorVentas = 0;
    private int idVenta;
    private Cliente cliente;
    private double totalVenta;
    private LocalDate fecha;

    public Ventas(Cliente cliente, double totalVenta, LocalDate fecha) {
        this.idVenta = ++contadorVentas;
        this.cliente = cliente;
        this.totalVenta = totalVenta;
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public static int getContadorVentas() {
        return contadorVentas;
    }

    public static void setContadorVentas(int contadorVentas) {
        Ventas.contadorVentas = contadorVentas;
    }

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getTotalVenta() {
        return totalVenta;
    }

    public void setTotalVenta(double totalVenta) {
        this.totalVenta = totalVenta;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Ventas registrarVentas(Cliente cliente, double total) {
        return new Ventas(cliente, total, LocalDate.now());
    }

    public double calcularTotal() {
        return this.totalVenta; 
    }

}
