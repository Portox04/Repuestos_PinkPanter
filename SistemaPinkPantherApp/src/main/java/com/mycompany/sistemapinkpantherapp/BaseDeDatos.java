/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author HP
 */
public class BaseDeDatos implements Serializable {

    private static final long serialVersionUID = 1L;

    private ArrayList<Cliente> clientes = new ArrayList<>();
    private ArrayList<Producto> productos = new ArrayList<>();
    private ArrayList<Ventas> ventas = new ArrayList<>();
    private ArrayList<Usuario> usuarios = new ArrayList<>();

    private static final String ARCHIVO = "basedatos.dat";

    // Métodos para guardar y cargar datos
    public void guardar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(ARCHIVO))) {
            out.writeObject(this);
            System.out.println("✅ Datos guardados con éxito.");

        } catch (IOException e) {
            System.err.println("❌ Error al guardar: " + e.getMessage());
        }
    }

    public static BaseDeDatos cargar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(ARCHIVO))) {
            return (BaseDeDatos) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("⚠️ No se pudo cargar base de datos, se crea nueva.");
            return new BaseDeDatos();
        }
    }

    // Métodos para agregar elementos o datos
    public void agregarCliente(Cliente c) {
        clientes.add(c);
    }
     public void deleteClient(String cedula) {
        clientes.removeIf(v -> v.getCedula().equals(cedula));
    }

    public Cliente searchClientByCedula(String cedula) {
        for (Cliente bo : clientes) {
            if (String.valueOf(bo.getCedula()).equals(cedula)) {
                return bo;
            }
        }
        return null;
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void agregarVenta(Ventas v) {
        ventas.add(v);
    }

    public void agregarUsuario(Usuario u) {
        usuarios.add(u);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public ArrayList<Producto> getProductos() {
        return productos;
    }

    public ArrayList<Ventas> getVentas() {
        return ventas;
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setClientes(ArrayList<Cliente> newClient) {
        clientes.clear();
        this.clientes = newClient;
    }
}
