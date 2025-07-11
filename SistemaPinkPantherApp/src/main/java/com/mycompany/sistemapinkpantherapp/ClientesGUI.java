/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class ClientesGUI extends JFrame {

    private JTextField txtNombre, txtCedula, txtTelefono, txtDireccion, txtCredito;
    private JButton btnRegistrar, btnBuscarHistorial;

    public ClientesGUI() {
        setTitle("Clientes");
        setSize(400, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre:");
        lblNombre.setBounds(20, 20, 100, 25);
        add(lblNombre);

        txtNombre = new JTextField();
        txtNombre.setBounds(130, 20, 200, 25);
        add(txtNombre);

        JLabel lblCedula = new JLabel("Cedula:");
        lblCedula.setBounds(20, 60, 100, 25);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(130, 60, 200, 25);
        add(txtCedula);

        JLabel lblTelefono = new JLabel("Telefono:");
        lblTelefono.setBounds(20, 100, 100, 25);
        add(lblTelefono);

        txtTelefono = new JTextField();
        txtTelefono.setBounds(130, 60, 200, 25);
        add(txtTelefono);

        JLabel lblDireccion = new JLabel("Telefono:");
        lblDireccion.setBounds(20, 140, 100, 25);
        add(lblDireccion);

        txtDireccion = new JTextField();
        txtDireccion.setBounds(130, 140, 200, 25);
        add(txtDireccion);

        JLabel lblCredito = new JLabel("Telefono:");
        lblCredito.setBounds(20, 180, 120, 25);
        add(lblCredito);

        txtCredito = new JTextField();
        txtCredito.setBounds(150, 180, 180, 25);
        add(txtCredito);

        btnRegistrar = new JButton("Registrar Cliente");
        btnRegistrar.setBounds(80, 240, 150, 30);
        add(btnRegistrar);

        btnBuscarHistorial = new JButton("Historial");
        btnBuscarHistorial.setBounds(240, 240, 100, 30);
        add(btnBuscarHistorial);

    }

}
