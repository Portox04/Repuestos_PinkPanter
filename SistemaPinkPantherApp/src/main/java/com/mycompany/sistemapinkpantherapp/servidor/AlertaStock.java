/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.servidor;

import java.time.LocalDate;

/**
 *
 * @author HP
 */
public class AlertaStock {

    private int idProducto;
    private LocalDate fechaGenerada;
    private String estado;

    public boolean verificarAlerta(int stockActual, int stockMinimo) {
        return stockActual <= stockMinimo;
    }

}
