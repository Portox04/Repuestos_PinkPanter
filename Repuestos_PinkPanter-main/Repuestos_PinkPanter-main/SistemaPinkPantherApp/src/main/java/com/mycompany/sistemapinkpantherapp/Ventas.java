/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author porto
 */
public class Ventas implements IVentaService, Serializable {
    
    private static int contadorVentas = 0; 
    private int idVenta;
    private Cliente cliente;
    private double totalVenta;
    private String fecha;
    

    public Ventas(Cliente cliente, double totalVenta, String fecha) {
        this.idVenta = ++contadorVentas;
        this.cliente = cliente;
        this.totalVenta = totalVenta;
        this.fecha = fecha;
    }

    public Cliente getCliente() {
        return cliente;
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
   @Override
   public Ventas registrarVentas(Cliente cliente, double total) {
        String fechaHoy = LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        return new Ventas(cliente, total, fechaHoy);
    }
    
   public double calcularTotal() {
        return this.totalVenta; // Aquí podés sumar lógica de impuestos o descuentos
    }
    
}
