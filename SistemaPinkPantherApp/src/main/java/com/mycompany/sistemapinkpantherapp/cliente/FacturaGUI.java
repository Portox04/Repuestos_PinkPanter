/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.cliente;

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

        // Acción: Confirmar Factura
        btnConfirmar.addActionListener(e -> {
            String nombre = txtNombreCliente.getText();
            String cedula = txtCedula.getText();
            String codigo = txtCodigoFactura.getText();

            if (nombre.isEmpty() || cedula.isEmpty() || codigo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Debe llenar todos los campos.");
            } else {
                JOptionPane.showMessageDialog(this, "Factura confirmada para: " + nombre);
                // Aquí podrías enviar los datos al servidor 
            }
        });

        // Acción: Generar PDF (Momentáneamente simulado)
        btnGenerarPDF.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Factura PDF generada (simulación).");
            // Aquí podrías usar alguna librería para generar el PDF real 
        });

        // Acción: Buscar Historial (omentáneamente simulado)
        btnBuscarHistorial.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Mostrando historial de facturación (demo).");
            // Aquí podrías cargar una ventana o panel con facturas previas  
        });
    }

}
