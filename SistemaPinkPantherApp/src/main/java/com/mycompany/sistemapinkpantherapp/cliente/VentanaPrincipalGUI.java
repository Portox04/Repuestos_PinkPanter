/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.cliente;

import com.mycompany.sistemapinkpantherapp.cliente.VentasGUI;
import com.mycompany.sistemapinkpantherapp.cliente.FacturaGUI;
import com.mycompany.sistemapinkpantherapp.cliente.ClientesGUI;
import com.mycompany.sistemapinkpantherapp.servidor.ConexionBaseDeDatos;
import com.mycompany.sistemapinkpantherapp.shared.Producto;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.*;

/**
 *
 * @author HP
 */
public class VentanaPrincipalGUI extends JFrame {

    private JButton btnVentas, btnClientes, btnFactura, btnInventario, btnSalir, btnGuardarBD;
    private ConexionBaseDeDatos datos;

    public VentanaPrincipalGUI() {
        datos = ConexionBaseDeDatos.cargar();

        if (datos.getProductos().isEmpty()) {
            Producto prueba = new Producto(
                    1, "2321313SA", "Termometro", 2200.00, 10,
                    "Temperatura", "Chevy Cavalier 2019", 1021
            );
            datos.agregarProducto(prueba);
        }

        setTitle("Pantalla Principal - Pink Panther");
        setSize(400, 350);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblBienvenida = new JLabel("Bienvenido al Sistema Pink Panther");
        lblBienvenida.setBounds(70, 20, 300, 25);
        add(lblBienvenida);

        btnVentas = new JButton("Ventas");
        btnVentas.setBounds(120, 60, 150, 30);
        add(btnVentas);

        btnClientes = new JButton("Clientes");
        btnClientes.setBounds(120, 100, 150, 30);
        add(btnClientes);

        btnFactura = new JButton("Factura");
        btnFactura.setBounds(120, 140, 150, 30);
        add(btnFactura);

        btnInventario = new JButton("Inventario");
        btnInventario.setBounds(120, 180, 150, 30);
        add(btnInventario);

        btnGuardarBD = new JButton("Guardar Base de Datos...");
        btnGuardarBD.setBounds(100, 230, 200, 30);
        add(btnGuardarBD);

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(120, 270, 150, 30);
        add(btnSalir);

        // Acciones
        btnVentas.addActionListener(e -> new VentasGUI(this, datos).setVisible(true));
        btnClientes.addActionListener(e -> new ClientesGUI(this, datos.getClienteDAO()).setVisible(true));
        btnFactura.addActionListener(e -> new FacturaGUI().setVisible(true));
        btnInventario.addActionListener(e -> new InventarioGUI().setVisible(true));

        btnGuardarBD.addActionListener(e -> {
            datos.guardar();
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        });

        btnSalir.addActionListener(e -> {
            int opcion = JOptionPane.showConfirmDialog(
                    this,
                    "¿Estás seguro que deseas salir del sistema?",
                    "Confirmar salida",
                    JOptionPane.YES_NO_OPTION
            );
            if (opcion == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });
    }
}
