/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class VentanaPrincipalGUI extends JFrame {

    private JButton btnVentas, btnClientes, btnFactura, btnInventario, btnSalir;

    public VentanaPrincipalGUI() {
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

        btnSalir = new JButton("Salir");
        btnSalir.setBounds(120, 230, 150, 30);
        add(btnSalir);

        // Acciones de los botones
        btnVentas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new VentasGUI().setVisible(true);
            }
        });

        btnClientes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new ClientesGUI().setVisible(true);
            }
        });

        btnFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FacturaGUI().setVisible(true);
            }
        });

        btnInventario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new InventarioGUI().setVisible(true);
            }
        });

        btnSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra la ventana principal
            }
        });

        JButton btnGuardarBD = new JButton("Guardar Base de Datos");
        btnGuardarBD.addActionListener(e -> {
            SistemaPinkPantherApp.bd.guardar();
            JOptionPane.showMessageDialog(this, "Datos guardados correctamente.");
        });
        this.add(btnGuardarBD);
    }

}
