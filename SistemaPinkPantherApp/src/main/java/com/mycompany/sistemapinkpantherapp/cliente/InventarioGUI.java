/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.cliente;

import com.mycompany.sistemapinkpantherapp.servidor.ProductoDAO;
import com.mycompany.sistemapinkpantherapp.shared.Producto;
import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author HP
 */
public class InventarioGUI extends JFrame {

    private JTable tablaInventario;
    private JTextField txtBuscar;
    private JButton btnBuscar, btnNuevo, btnEliminar, btnEditar, btnActualizar;

    public InventarioGUI() {
        setTitle("Gestion de Inventario Pink Panther");
        setSize(700, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
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

        String[] columnas = {
            "Código", "Nombre", "Precio Venta", "Stock Actual",
            "Categoría", "Descripción", "ID Proveedor"
        };

        Object[][] datos = {};
        tablaInventario = new JTable(new DefaultTableModel(datos, columnas));
        JScrollPane scroll = new JScrollPane(tablaInventario);
        scroll.setBounds(20, 100, 740, 280);
        add(scroll);

        btnNuevo = new JButton("Nuevo Producto");
        btnNuevo.setBounds(20, 400, 150, 30);
        add(btnNuevo);

        btnEditar = new JButton("Editar");
        btnEditar.setBounds(180, 400, 100, 30);
        add(btnEditar);

        btnEliminar = new JButton("Eliminar");
        btnEliminar.setBounds(290, 400, 100, 30);
        add(btnEliminar);

        btnActualizar = new JButton("Actualizar Stock");
        btnActualizar.setBounds(400, 400, 150, 30);
        add(btnActualizar);

        // Acción Buscar
        btnBuscar.addActionListener(e -> {
            String codigoBuscado = txtBuscar.getText().trim();
            if (codigoBuscado.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese un código para buscar.");
                return;
            }

            ProductoDAO dao = new ProductoDAO();
            Producto producto = dao.buscarPorCodigo(codigoBuscado);

            DefaultTableModel modelo = (DefaultTableModel) tablaInventario.getModel();
            modelo.setRowCount(0);

            if (producto != null) {
                modelo.addRow(new Object[]{
                    producto.getCodigo(),
                    producto.getNombre(),
                    producto.getPrecioVenta(),
                    producto.getStockActual(),
                    producto.getCategoria(),
                    producto.getDescripcion(),
                    producto.getIdProveedor()
                });
            } else {
                JOptionPane.showMessageDialog(this, "Producto no encontrado");
            }
        });

        // Acción Nuevo
        btnNuevo.addActionListener(e -> {
            JOptionPane.showMessageDialog(this, "Funcionalidad para agregar nuevo producto");
        });

        // Acción Editar
        btnEditar.addActionListener(e -> {
            int fila = tablaInventario.getSelectedRow();
            if (fila != -1) {
                String codigo = (String) tablaInventario.getValueAt(fila, 0);
                JOptionPane.showMessageDialog(this, "Editar producto: " + codigo);
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para editar.");
            }
        });

        // Acción Eliminar
        btnEliminar.addActionListener(e -> {
            int fila = tablaInventario.getSelectedRow();
            if (fila != -1) {
                String codigo = (String) tablaInventario.getValueAt(fila, 0);
                int confirm = JOptionPane.showConfirmDialog(
                        this,
                        "¿Eliminar el producto: " + codigo + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );
                if (confirm == JOptionPane.YES_OPTION) {
                    ProductoDAO dao = new ProductoDAO();
                    if (dao.eliminarPorCodigo(codigo)) {
                        ((DefaultTableModel) tablaInventario.getModel()).removeRow(fila);
                        JOptionPane.showMessageDialog(this, "Producto eliminado");
                    } else {
                        JOptionPane.showMessageDialog(this, "No se pudo eliminar el producto");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para eliminar.");
            }
        });

        // Acción Actualizar Stock
        btnActualizar.addActionListener(e -> {
            int fila = tablaInventario.getSelectedRow();
            if (fila != -1) {
                String codigo = (String) tablaInventario.getValueAt(fila, 0);
                String nuevoStock = JOptionPane.showInputDialog(this, "Nuevo stock para " + codigo + ":");
                if (nuevoStock != null && !nuevoStock.isEmpty()) {
                    try {
                        int stock = Integer.parseInt(nuevoStock);
                        if (stock < 0) {
                            JOptionPane.showMessageDialog(this, "El stock no puede ser negativo.");
                            return;
                        }

                        ProductoDAO dao = new ProductoDAO();
                        boolean actualizado = dao.actualizarStock(codigo, stock);

                        if (actualizado) {
                            ((DefaultTableModel) tablaInventario.getModel()).setValueAt(stock, fila, 3);
                            JOptionPane.showMessageDialog(this, "Stock actualizado");
                        } else {
                            JOptionPane.showMessageDialog(this, "No se pudo actualizar el stock");
                        }

                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(this, "Ingrese un número válido");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Seleccione un producto para actualizar.");
            }
        });

        cargarInventarioLocal();
    }

    private void cargarInventarioLocal() {
        ProductoDAO dao = new ProductoDAO();
        java.util.List<Producto> productos = dao.obtenerProductos();
        DefaultTableModel modelo = (DefaultTableModel) tablaInventario.getModel();

        for (Producto p : productos) {
            modelo.addRow(new Object[]{
                p.getCodigo(),
                p.getNombre(),
                p.getPrecioVenta(),
                p.getStockActual(),
                p.getCategoria(),
                p.getDescripcion(),
                p.getIdProveedor()
            });
        }
    }
}
