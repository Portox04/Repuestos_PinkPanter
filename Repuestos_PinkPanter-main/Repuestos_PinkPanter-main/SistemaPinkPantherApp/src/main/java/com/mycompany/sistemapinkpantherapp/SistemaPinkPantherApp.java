/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemapinkpantherapp;

/**
 *
 * @author HP
 */
public class SistemaPinkPantherApp {
    
    public static BaseDeDatos bd;
    
    public static void main(String[] args) {
        // Cargar la base de datos
        bd = BaseDeDatos.cargar();
        
        ClienteSucursal cliente = new ClienteSucursal();
        cliente.conectarAlServidor();
        VentanaPrincipalGUI ventana = new VentanaPrincipalGUI();
        ventana.setVisible(true);

        //Mostrar ventana principal 
        new VentanaPrincipalGUI().setVisible(true);
        
    }
}
