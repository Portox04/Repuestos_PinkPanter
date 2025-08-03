/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class VentasGUI extends JFrame {

    private JTable tablaProductos;
    private DefaultTableModel modeloTabla;
    private JTextField txtCantidad, txtCedula;
    private JLabel lblSubtotal, lblIVA, lblTotal;
    private JButton btnAgregar, btnConfirmar, btnCancelar;

    public VentasGUI(JFrame owner, BaseDeDatos data) {
        super("Ventas");
        setSize(600, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);

        JLabel lblTitulo = new JLabel("Productos Disponibles:");
        lblTitulo.setBounds(20, 20, 200, 25);
        add(lblTitulo);

        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaProductos = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaProductos);
        scroll.setBounds(20, 50, 550, 150);
        add(scroll);
        cargarProductosEnTabla(data);

        JLabel lblCantidad = new JLabel("Cantidad:");
        lblCantidad.setBounds(20, 210, 100, 25);
        add(lblCantidad);

        txtCantidad = new JTextField();
        txtCantidad.setBounds(120, 210, 100, 25);
        add(txtCantidad);

        btnAgregar = new JButton("Agregar al carrito");
        btnAgregar.setBounds(240, 210, 150, 25);
        add(btnAgregar);

        JLabel lblCliente = new JLabel("Cedula del cliente:");
        lblCliente.setBounds(20, 250, 150, 25);
        add(lblCliente);

        txtCedula = new JTextField();
        txtCedula.setBounds(180, 250, 150, 25);
        add(txtCedula);

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

        btnAgregar.addActionListener(e -> {
            int filaSeleccionada = tablaProductos.getSelectedRow();

            if (filaSeleccionada == -1) {
                JOptionPane.showMessageDialog(this, "Seleccione un producto primero.");
                return;
            }

            try {
                int cantidad = Integer.parseInt(txtCantidad.getText());
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(this, "Ingrese una cantidad válida.");
                    return;
                }

                double precioUnitario = Double.parseDouble(modeloTabla.getValueAt(filaSeleccionada, 2).toString());
                double subtotalActual = extraerValor(lblSubtotal);
                double nuevoSubtotal = subtotalActual + (precioUnitario * cantidad);
                double iva = nuevoSubtotal * 0.13;
                double total = nuevoSubtotal + iva;
                lblSubtotal.setText(String.format("Subtotal: ₡%.2f", nuevoSubtotal));
                lblIVA.setText(String.format("IVA: ₡%.2f", iva));
                lblTotal.setText(String.format("Total: ₡%.2f", total));

            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ingrese un número válido en cantidad.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        btnConfirmar.addActionListener(e -> {
            double total = extraerValor(lblTotal);

            if (total <= 0) {
                JOptionPane.showMessageDialog(this, "La compra no puede ser de ₡0.00", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            String cedula = txtCedula.getText().trim();
            Cliente cliente = data.searchClientByCedula(cedula);
            if (cliente == null) {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado. Verifique la cédula.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Ventas nuevaVenta = new Ventas(cliente, total, java.time.LocalDate.now().toString());
            data.agregarVenta(nuevaVenta);
            int filaSeleccionada = tablaProductos.getSelectedRow();
            
            if (filaSeleccionada != -1) {
                int cantidad = Integer.parseInt(txtCantidad.getText());
                Producto p = data.getProductos().get(filaSeleccionada);

                if (p.getStockActual() < cantidad) {
                    JOptionPane.showMessageDialog(this, "Stock insuficiente para el producto seleccionado.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                p.setStockActual(p.getStockActual() - cantidad);
            }

            data.guardar();
            JOptionPane.showMessageDialog(this, "Venta registrada con éxito. Total: ₡" + total);

            txtCantidad.setText("");
            txtCedula.setText("");
            lblSubtotal.setText("Subtotal: ₡0.00");
            lblIVA.setText("IVA: ₡0.00");
            lblTotal.setText("Total: ₡0.00");

            cargarProductosEnTabla(data);
            this.dispose();
        });

        btnCancelar.addActionListener(e -> dispose());

    }
    // Cargar productos en la tabla usando BaseDeDatos

    private void cargarProductosEnTabla(BaseDeDatos data) {
        modeloTabla.setRowCount(0);

        for (Producto p : data.getProductos()) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecioVenta(),
                p.getStockActual()
            };
            modeloTabla.addRow(fila);
        }
    }

    private double extraerValor(JLabel label) {
        String texto = label.getText().replaceAll("[^0-9.]", "");
        if (texto.isEmpty()) {
            return 0.0;
        }
        return Double.parseDouble(texto);
    }

}
