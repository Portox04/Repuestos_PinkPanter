/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.servidormain;
import java.io.*;
import java.net.*;
/**
 *
 * @author leymanwu
 */
public class ServidorCentral implements Runnable {
    private Socket cliente;

    public ServidorCentral(Socket cliente) {
        this.cliente = cliente;
    }
    
    @Override
    public void run(){
        try(
            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter salida = new PrintWriter(cliente.getOutputStream(),true);
                ){
            String mensaje;
            while((mensaje = entrada.readLine()) != null){
                System.out.println("Mensaje recibido del cliente"+mensaje);
                salida.println("Eco desde el servidor: "+mensaje);
            }
    }catch(IOException e){
        System.out.println("Error en la conexion de cliente: " + e.getMessage());
    }
    
    }
}
