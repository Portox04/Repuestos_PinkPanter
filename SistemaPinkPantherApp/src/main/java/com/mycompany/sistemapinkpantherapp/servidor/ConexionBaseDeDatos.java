/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.servidor;

import com.mycompany.sistemapinkpantherapp.shared.Cliente;
import com.mycompany.sistemapinkpantherapp.shared.Producto;
import java.io.*;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.io.Serializable;
import com.mycompany.sistemapinkpantherapp.shared.Cliente;
import com.mycompany.sistemapinkpantherapp.shared.Ventas;
import java.sql.*;

/**
 *
 * @author HP
 */
public class ConexionBaseDeDatos implements Serializable {

    private static final String URL = "jdbc:mysql://localhost:3306/pink_panther?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "root";
    private static final String CLAVE = "s123";

    private List<Producto> productos;
    private List<Cliente> clientes;
    private List<Ventas> ventas;
    private transient ClienteDAO clienteDAO; // No serializar DAO

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se pudo cargar el driver de MySQL");
            e.printStackTrace();
        }
    }

    public ConexionBaseDeDatos() {
        productos = new ArrayList<>();
        clientes = new ArrayList<>();
        ventas = new ArrayList<>();
        clienteDAO = new ClienteDAO(); // Inicializar DAO
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, CLAVE);
    }

    public static void cerrarConexion(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                System.err.println("Error cerrando la conexión: " + e.getMessage());
            }
        }
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public List<Ventas> getVentas() {
        return ventas;
    }

    public void agregarProducto(Producto p) {
        productos.add(p);
    }

    public void agregarCliente(Cliente c) {
        clientes.add(c);
    }

    public void agregarVenta(Ventas v) {
        ventas.add(v);
    }

    public Cliente searchClientByCedula(String cedula) {
        for (Cliente c : clientes) {
            if (c.getCedula().equalsIgnoreCase(cedula)) {
                return c;
            }
        }
        return null;
    }

    public Producto buscarProductoPorId(int idProducto) {
        for (Producto p : productos) {
            if (p != null && p.getIdProducto() == idProducto) {
                return p;
            }
        }
        return null;
    }

    public ClienteDAO getClienteDAO() {
        if (clienteDAO == null) {
            clienteDAO = new ClienteDAO(); // Reinstanciar si fue deserializado
        }
        return clienteDAO;
    }

    public void guardar() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("datos_guardados.dat"))) {
            out.writeObject(this);
            System.out.println("Datos guardados correctamente en datos_guardados.dat");
        } catch (IOException e) {
            System.err.println("Error al guardar los datos: " + e.getMessage());
        }
    }

    public static ConexionBaseDeDatos cargar() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("datos_guardados.dat"))) {
            ConexionBaseDeDatos bd = (ConexionBaseDeDatos) in.readObject();
            bd.clienteDAO = new ClienteDAO(); // Reinstanciar DAO después de cargar
            return bd;
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("No se pudo cargar los datos: " + e.getMessage());
            return new ConexionBaseDeDatos();
        }
    }
}
