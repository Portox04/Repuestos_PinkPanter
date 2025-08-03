/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.servidormain;

import java.io.*;
import java.net.*;
import javax.net.ssl.SSLServerSocket;

/**
 *
 * @author leymanwu
 */
public class ServidorMain {

    public static void main(String[] args) {
        int puerto = 5000;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor se esta iniciando. Esperando clientes se conecte");

            while (true) {
                Socket cliente = servidor.accept();
                System.out.println("Cliente se esta conectando" + cliente.getInetAddress());

                new Thread(new ServidorCentral(cliente)).start();

            }

        } catch (IOException e) {
            System.out.println("Error de servidor: " + e.getMessage());

        }
    }
}
