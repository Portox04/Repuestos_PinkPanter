/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp.servidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author HP
 */
public class ClienteSucursalTENTATIVABORRAR {

    private Socket socket;
    private BufferedReader entrada;
    private PrintWriter salida;

    //Método para conectar al servidor
    public void conectarAlServidor() {
        try {
            socket = new Socket("localhost", 5000);
            entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            salida = new PrintWriter(socket.getOutputStream(), true);
            System.out.println("Conectado al servidor.");
        } catch (IOException e) {
            System.err.println("Error al conectar con el servidor.");
            e.printStackTrace();
        }
    }

    //Método para pedir productos al servidor
    public String obtenerProductos() {
        try {
            salida.println("Obtener_productos"); // Comando que el servidor debe entender
            String respuesta = entrada.readLine(); //Recibe la lista como texto
            return respuesta;
        } catch (IOException e) {
            System.err.println("Error al obtener productos.");
            e.printStackTrace();
            return null;
        }
    }

    //Cerrar conexión
    public void cerrarConexion() {
        try {
            if (socket != null) {
                socket.close();
            }
            if (entrada != null) {
                entrada.close();
            }
            if (salida != null) {
                salida.close();
            }
        } catch (IOException e) {
            System.err.println("Error al cerrar la conexión.");
            e.printStackTrace();
        }
    }

}
