/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.cliente;

import com.mycompany.sistemapinkpantherapp.servidor.ClienteDAO;
import com.mycompany.sistemapinkpantherapp.shared.Cliente;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.*;

/**
 *
 * @author HP
 */
public class ClientesGUI extends JFrame {

    private JTextField txtNombre, txtCedula, txtTelefono, txtCorreo, txtCredito;
    private JButton btnRegistrar, btnCancelar;
    private ClienteDAO dao;

    public ClientesGUI(JFrame owner, ClienteDAO dao) {
        super("Agregar Clientes");
        this.dao = dao;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        txtNombre = new JTextField(20);
        txtCedula = new JTextField(20);
        txtTelefono = new JTextField(20);
        txtCorreo = new JTextField(20);
        txtCredito = new JTextField(20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nombre: "), gbc);
        gbc.gridx = 1;
        add(txtNombre, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Cedula: "), gbc);
        gbc.gridx = 1;
        add(txtCedula, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Telefono: "), gbc);
        gbc.gridx = 1;
        add(txtTelefono, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("Correo: "), gbc);
        gbc.gridx = 1;
        add(txtCorreo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Credito: "), gbc);
        gbc.gridx = 1;
        add(txtCredito, gbc);

        btnRegistrar = new JButton("Registrar");
        btnCancelar = new JButton("Cancelar");

        btnRegistrar.addActionListener(e -> {
            try {
                String nombre = txtNombre.getText();
                String cedula = txtCedula.getText();
                String telefono = txtTelefono.getText();
                String correo = txtCorreo.getText();
                double credito = Double.parseDouble(txtCredito.getText());

                if (nombre.isEmpty() || cedula.isEmpty() || telefono.isEmpty() || correo.isEmpty()) {
                    JOptionPane.showMessageDialog(this,
                            "Todos los campos deben estar completos.",
                            "ERROR",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                Cliente nuevoCliente = new Cliente(cedula, telefono, credito, 0, nombre, "Cliente", correo, "1234");
                boolean exito = dao.insertar(nuevoCliente);

                if (exito) {
                    JOptionPane.showMessageDialog(this, "Cliente registrado correctamente.");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "No se pudo registrar el cliente.", "ERROR", JOptionPane.ERROR_MESSAGE);
                }

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this,
                        "Por favor, digita números válidos en el campo de crédito.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this,
                        "Error al acceder a la base de datos.",
                        "ERROR",
                        JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        btnCancelar.addActionListener(e -> dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnCancelar);
        buttonPanel.add(btnRegistrar);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        add(buttonPanel, gbc);

        pack();
        setLocationRelativeTo(owner);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
