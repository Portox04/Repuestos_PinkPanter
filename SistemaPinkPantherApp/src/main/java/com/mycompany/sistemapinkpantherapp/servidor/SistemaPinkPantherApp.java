/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.sistemapinkpantherapp.cliente;

import com.mycompany.sistemapinkpantherapp.cliente.VentanaPrincipalGUI;
import com.mycompany.sistemapinkpantherapp.servidor.ConexionBaseDeDatos;
import com.mycompany.sistemapinkpantherapp.servidor.ClienteSucursalTENTATIVABORRAR;
import javax.swing.JOptionPane;

/**
 *
 * @author HP
 */
public class SistemaPinkPantherApp {

    public static ConexionBaseDeDatos bd;

    public static void main(String[] args) {
        // Cargar la base de datos
        bd = ConexionBaseDeDatos.cargar();

        // Conectar al servidor
        ClienteSucursalTENTATIVABORRAR cliente = new ClienteSucursalTENTATIVABORRAR();
        cliente.conectarAlServidor();

        //Mostrar ventana principal 
        VentanaPrincipalGUI ventana = new VentanaPrincipalGUI();
        ventana.setVisible(true);

    }
}
