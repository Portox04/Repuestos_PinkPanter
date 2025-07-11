/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemapinkpantherapp;

/**
 *
 * @author HP
 */
public class SistemaPinkPantherApp {

    public static void main(String[] args) {
        ClienteSucursal cliente = new ClienteSucursal();
        cliente.conectarAlServidor();
        VentanaPrincipalGUI ventana = new VentanaPrincipalGUI();
        ventana.setVisible(true);

    }
}
