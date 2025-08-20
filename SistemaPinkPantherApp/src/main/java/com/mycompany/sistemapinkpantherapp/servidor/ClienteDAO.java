/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.servidor;

import com.mycompany.sistemapinkpantherapp.shared.Cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ClienteDAO {
    
    public boolean insertar(Cliente c) throws SQLException {
        Connection con = null;
        PreparedStatement psUsuario = null;
        PreparedStatement psCliente = null;
        ResultSet rs = null;

        try {
            con = ConexionBaseDeDatos.getConnection();
            con.setAutoCommit(false); // iniciar transacción

            // Validar rol
            String rol = c.getRol().toUpperCase();
            if (!rol.equals("ADMIN") && !rol.equals("CLIENTE") && !rol.equals("VENDEDOR")) {
                rol = "CLIENTE"; // valor por defecto
            }

            // 1️⃣ Insertar en usuarios
            String sqlUsuario = "INSERT INTO usuarios (nombre, rol, correo, clave) VALUES (?, ?, ?, ?)";
            psUsuario = con.prepareStatement(sqlUsuario, Statement.RETURN_GENERATED_KEYS);
            psUsuario.setString(1, c.getNombre());
            psUsuario.setString(2, rol);
            psUsuario.setString(3, c.getCorreo());
            psUsuario.setString(4, c.getClave());
            psUsuario.executeUpdate();

            rs = psUsuario.getGeneratedKeys();
            if (rs.next()) {
                c.setIdUsuario(rs.getInt(1)); // obtener id generado
            } else {
                con.rollback();
                return false;
            }

            // 2️⃣ Insertar en clientes
            String sqlCliente = "INSERT INTO clientes (id_cliente, cedula, telefono, credito_restante) VALUES (?, ?, ?, ?)";
            psCliente = con.prepareStatement(sqlCliente);
            psCliente.setInt(1, c.getIdUsuario());
            psCliente.setString(2, c.getCedula());
            psCliente.setString(3, c.getTelefono());
            psCliente.setDouble(4, c.getCreditoRestante());

            int filas = psCliente.executeUpdate();

            con.commit(); // confirmar transacción
            return filas > 0;

        } catch (SQLException e) {
            if (con != null) {
                con.rollback(); // revertir si hay error
            }
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (psUsuario != null) psUsuario.close();
            if (psCliente != null) psCliente.close();
            if (con != null) con.close();
        }
    }
}
