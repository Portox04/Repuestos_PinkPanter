/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class FacturaGUI extends JFrame {

    private JTextField txtNombreCliente, txtCedula, txtCodigoFactura;
    private JLabel lblProductos, lblTotal;
    private JButton btnGenerarPDF, btnConfirmar, btnBuscarHistorial;

    public FacturaGUI() {
        setTitle("Generacion de Factura");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        JLabel lblNombre = new JLabel("Nombre Cliente:");
        lblNombre.setBounds(20, 20, 120, 25);
        add(lblNombre);

        txtNombreCliente = new JTextField();
        txtNombreCliente.setBounds(150, 20, 200, 25);
        add(txtNombreCliente);

        JLabel lblCedula = new JLabel("Cedula:");
        lblCedula.setBounds(20, 60, 120, 25);
        add(lblCedula);

        txtCedula = new JTextField();
        txtCedula.setBounds(150, 60, 200, 25);
        add(txtCedula);

        JLabel lblCodigo = new JLabel("Codigo Factura:");
        lblCodigo.setBounds(20, 100, 120, 25);
        add(lblCodigo);

        txtCodigoFactura = new JTextField();
        txtCodigoFactura.setBounds(150, 100, 200, 25);
        add(txtCodigoFactura);

        JLabel lblProductos = new JLabel("Productos [detalle aqui]:");
        lblProductos.setBounds(20, 140, 330, 25);
        add(lblProductos);

        JLabel lblTotal = new JLabel("Total: ₡0.00");
        lblTotal.setBounds(20, 170, 200, 25);
        add(lblTotal);

        btnConfirmar = new JButton("Confirmar Venta");
        btnConfirmar.setBounds(50, 220, 140, 30);
        add(btnConfirmar);

        btnGenerarPDF = new JButton("Generar PDF");
        btnGenerarPDF.setBounds(180, 220, 120, 30);
        add(btnGenerarPDF);

        btnBuscarHistorial = new JButton("Buscar Historial");
        btnBuscarHistorial.setBounds(180, 220, 120, 30);
        add(btnBuscarHistorial);

        // Acción del botón Buscar Historial
        btnBuscarHistorial.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String cedula = txtCedula.getText().trim();
                if (cedula.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor ingresa la cédula del cliente.");
                } else {
                    // Simulación básica — luego puedes conectar con la clase Cliente
                    JOptionPane.showMessageDialog(null,
                            "Historial de facturas para cédula " + cedula + ":\n(No implementado aún)");
                }
            }
        });
    }

}
