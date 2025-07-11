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
public class VentasGUI extends JFrame {

    private JTable tablaProductos;
    private JTextField txtCantidad, txtCodigoDescuento;
    private JLabel lblSubtotal, lblIVA, lblTotal;
    private JButton btnAgregar, btnConfirmar, btnCancelar;

    public VentasGUI() {
        setTitle("Registro de Ventas");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Productos Disponibles:");
        lblTitulo.setBounds(20, 20, 200, 25);
        add(lblTitulo);

        tablaProductos = new JTable(); //Debe llenarse con el modelo de productos
        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBounds(20, 50, 550, 150);
        add(scroll);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(120, 210, 100, 25);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(120, 210, 100, 25);
        add(txtCantidad);

        btnAgregar = new JButton("Agregar al carrito");
        btnAgregar.setBounds(240, 210, 150, 25);
        add(btnAgregar);

        JLabel lblDescuento = new JLabel("Codigo de descuento:");
        lblDescuento.setBounds(20, 250, 150, 25);
        add(lblDescuento);

        txtCodigoDescuento = new JTextField();
        txtCodigoDescuento.setBounds(180, 250, 150, 25);
        add(txtCodigoDescuento);

        lblSubtotal = new JLabel("Subtotal: ₡0.00");
        lblSubtotal.setBounds(20, 290, 200, 25);
        add(lblSubtotal);

        lblIVA = new JLabel("IVA: ₡0.00");
        lblIVA.setBounds(20, 320, 200, 25);
        add(lblIVA);

        lblTotal = new JLabel("Total: ₡0.00");
        lblTotal.setBounds(20, 350, 200, 25);
        add(lblTotal);

        btnConfirmar = new JButton("Confirmar Venta");
        btnConfirmar.setBounds(150, 400, 150, 30);
        add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(320, 400, 100, 30);
        add(btnCancelar);

    }

}
