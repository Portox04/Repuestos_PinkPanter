/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.servidor;

import com.mycompany.sistemapinkpantherapp.shared.Usuario;
import java.sql.*;

/**
 *
 * @author HP
 */
public class UsuarioDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/pink_panther?useSSL=false&serverTimezone=UTC";
    private static final String USUARIO = "ProyectoBD";
    private static final String CLAVE = "s123";

    public boolean insertar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (nombre, correo, rol, clave) VALUES (?, ?, ?, ?)";

        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getClave());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al insertar usuario: " + e.getMessage());
            return false;
        }
    }

    public Usuario buscarPorId(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirUsuario(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por ID: " + e.getMessage());
        }

        return null;
    }

    public Usuario buscarPorNombre(String nombre) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ?";

        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return construirUsuario(rs);
            }

        } catch (SQLException e) {
            System.err.println("Error al buscar usuario por nombre: " + e.getMessage());
        }

        return null;
    }

    public boolean validarLogin(String nombre, String clave) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND clave = ?";

        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, nombre);
            ps.setString(2, clave);
            ResultSet rs = ps.executeQuery();

            return rs.next();

        } catch (SQLException e) {
            System.err.println("Error al validar login: " + e.getMessage());
            return false;
        }
    }

    public boolean actualizar(Usuario usuario) {
        String sql = "UPDATE usuarios SET nombre = ?, correo = ?, rol = ?, clave = ? WHERE id = ?";

        try (Connection con = DriverManager.getConnection(URL, USUARIO, CLAVE); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getCorreo());
            ps.setString(3, usuario.getRol());
            ps.setString(4, usuario.getClave());
            ps.setInt(5, usuario.getId());

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.err.println("Error al actualizar usuario: " + e.getMessage());
            return false;
        }
    }

    private Usuario construirUsuario(ResultSet rs) throws SQLException {
        return new Usuario(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("correo"),
                rs.getString("rol"),
                rs.getString("clave")
        );
    }
}
