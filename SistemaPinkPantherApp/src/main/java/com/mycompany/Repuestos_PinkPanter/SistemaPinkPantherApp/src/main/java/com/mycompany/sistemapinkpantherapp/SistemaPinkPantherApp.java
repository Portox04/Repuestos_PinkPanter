/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemapinkpantherapp;

import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class SistemaPinkPantherApp {

    public static BaseDeDatos bd;

    public static void main(String[] args) {
        // Cargar la base de datos
        bd = BaseDeDatos.cargar();

        // Conectar al servidor
        ClienteSucursal cliente = new ClienteSucursal();
        cliente.conectarAlServidor();

        //Mostrar ventana principal 
        VentanaPrincipalGUI ventana = new VentanaPrincipalGUI();
        ventana.setVisible(true);

    }
}
