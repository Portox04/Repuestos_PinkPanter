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
public class InventarioGUI extends JFrame {

    private JTable tablaInventario;
    private JTextField txtBuscar;
    private JButton btnBuscar, btnNuevo, btnEliminar, btnEditar, btnActualizar;

    public InventarioGUI() {
        setTitle("Gestion de Inventario");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Inventario de Productos");
        lblTitulo.setBounds(20, 20, 300, 25);
        add(lblTitulo);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(20, 60, 200, 25);
        add(txtBuscar);

        btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(230, 60, 100, 25);
        add(btnBuscar);

        tablaInventario = new JTable(); //Se llena con el modelo del producto
        JScrollPane scroll = new JScrollPane(tablaInventario);
        scroll.setBounds(20, 100, 640, 250);
        add(scroll);

        btnNuevo = new JButton("Nuevo Producto");
        btnNuevo.setBounds(20, 370, 150, 30);
        add(btnNuevo);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(180, 370, 100, 30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(290, 370, 100, 30);
        add(btnEliminar);

        btnActualizar = new JButton("Actualizar Stock");
        btnActualizar.setBounds(400, 370, 150, 30);
        add(btnActualizar);

    }

}
