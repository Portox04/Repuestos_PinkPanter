/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.servidor;

import com.mycompany.sistemapinkpantherapp.shared.Cliente;
import com.mycompany.sistemapinkpantherapp.shared.Ventas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author porto
 */
public class VentaDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/pink_panther?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "ProyectoBD";
    private static final String CLAVE = "s123";

    public boolean insertar(Ventas venta) {
        String sql = "INSERT INTO ventas (id_cliente, total_venta, fecha) VALUES (?, ?, ?)";
        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, venta.getCliente().getIdUsuario());
            ps.setDouble(2, venta.getTotalVenta());
            ps.setDate(3, Date.valueOf(venta.getFecha()));

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.err.println("Error al insertar venta: " + e.getMessage());
            return false;
        }
    }

    public Ventas buscarPorId(int id) {
        String sql = "SELECT v.id_venta, v.total_venta, v.fecha, c.* "
                + "FROM ventas v JOIN clientes c ON v.id_cliente = c.idUsuario "
                + "WHERE v.id_venta = ?";
        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Cliente cliente = construirCliente(rs);
                return new Ventas(
                        cliente,
                        rs.getDouble("total_venta"),
                        rs.getDate("fecha").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.err.println("Error al buscar venta: " + e.getMessage());
        }
        return null;
    }

    public List<Ventas> listarTodas() {
        List<Ventas> lista = new ArrayList<>();
        String sql = "SELECT v.id_venta, v.total_venta, v.fecha, c.* "
                + "FROM ventas v JOIN clientes c ON v.id_cliente = c.idUsuario";
        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql); ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Cliente cliente = construirCliente(rs);
                lista.add(new Ventas(
                        cliente,
                        rs.getDouble("total_venta"),
                        rs.getDate("fecha").toLocalDate()
                ));
            }
        } catch (SQLException e) {
            System.err.println("Error al listar ventas: " + e.getMessage());
        }
        return lista;
    }

    private Cliente construirCliente(ResultSet rs) throws SQLException {
        return new Cliente(
                rs.getString("cedula"),
                rs.getString("telefono"),
                rs.getDouble("creditoRestante"),
                rs.getInt("idUsuario"),
                rs.getString("nombre"),
                rs.getString("rol"),
                rs.getString("correo"),
                rs.getString("clave")
        );
    }
}
