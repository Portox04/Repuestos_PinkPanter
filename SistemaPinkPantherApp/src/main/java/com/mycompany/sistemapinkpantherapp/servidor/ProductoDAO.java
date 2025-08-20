/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.servidor;

import com.mycompany.sistemapinkpantherapp.shared.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

/**
 *
 * @author HP
 */
public class ProductoDAO {

    public Producto buscarPorCodigo(String codigo) {
        String sql = "SELECT * FROM productos WHERE codigo = ?";
        try (Connection con = ConexionBaseDeDatos.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                p.setStockActual(rs.getInt("stock_actual"));
                p.setCategoria(rs.getString("categoria"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setIdProveedor(rs.getInt("id_proveedor"));
                return p;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarPorCodigo(String codigo) {
        String sql = "DELETE FROM productos WHERE codigo = ?";
        try (Connection con = ConexionBaseDeDatos.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean actualizarStock(String codigo, int nuevoStock) {
        String sql = "UPDATE productos SET stock_actual = ? WHERE codigo = ?";
        try (Connection con = ConexionBaseDeDatos.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, nuevoStock);
            ps.setString(2, codigo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Producto> obtenerProductos() {
        List<Producto> lista = new ArrayList<>();
        String sql = "SELECT * FROM productos";
        try (Connection con = ConexionBaseDeDatos.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setCodigo(rs.getString("codigo"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecioVenta(rs.getDouble("precio_venta"));
                p.setStockActual(rs.getInt("stock_actual"));
                p.setCategoria(rs.getString("categoria"));
                p.setDescripcion(rs.getString("descripcion"));
                p.setIdProveedor(rs.getInt("id_proveedor"));
                lista.add(p);
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    public boolean guardarProducto(Producto producto) {
        String sql = "INSERT INTO productos (codigo, nombre, precio_venta, stock_actual, categoria, descripcion, id_proveedor) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConexionBaseDeDatos.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, producto.getCodigo());
            ps.setString(2, producto.getNombre());
            ps.setDouble(3, producto.getPrecioVenta());
            ps.setInt(4, producto.getStockActual());
            ps.setString(5, producto.getCategoria());
            ps.setString(6, producto.getDescripcion());
            ps.setInt(7, producto.getIdProveedor());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
