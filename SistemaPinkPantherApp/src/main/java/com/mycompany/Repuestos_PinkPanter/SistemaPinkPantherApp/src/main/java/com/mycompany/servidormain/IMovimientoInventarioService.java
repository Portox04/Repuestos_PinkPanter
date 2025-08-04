/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidormain;
import java.util.List;

/**
 *
 * @author leymanwu
 */
public interface IMovimientoInventarioService {
    void registrarMovimiento(MovimientoInventario movimiento);
    List<MovimientoInventario> obtenerTodosLosMovimientos();
    List<MovimientoInventario> obtenerMovimientosPorProducto(String codigoProducto);
}
