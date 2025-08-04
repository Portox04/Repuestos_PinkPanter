/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidormain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author leymanwu
 */
public class MovimientoInventarioServiceImpl implements IMovimientoInventarioService {

    private final List<MovimientoInventario> listaMovimientos = new ArrayList<>();

    public void registrarMovimiento(MovimientoInventario movimiento) {
        listaMovimientos.add(movimiento);
        System.out.println("Movimiento registrado: " + movimiento);
    }

    public List<MovimientoInventario> obtenerTodosLosMovimientos() {
        return listaMovimientos;
    }

    public List<MovimientoInventario> obtenerMovimientosPorProducto(String codigoProducto) {
        List<MovimientoInventario> resultado = new ArrayList<>();
        for (MovimientoInventario mov : listaMovimientos) {
            if (mov.getCodigoProducto().equals(codigoProducto)) {
                resultado.add(mov);
            }
        }
        return resultado;
    }
}
