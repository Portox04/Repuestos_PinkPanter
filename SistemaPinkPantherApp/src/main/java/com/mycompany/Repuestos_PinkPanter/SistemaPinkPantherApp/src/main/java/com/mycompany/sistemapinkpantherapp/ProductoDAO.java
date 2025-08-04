/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistemapinkpantherapp;

import com.mycompany.sistemapinkpantherapp.Producto;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class ProductoDAO {

    private static final String FILE_PATH = "productos.dat";

    public void guardarProducto(Producto producto) {
        List<Producto> productos = obtenerProductos();
        productos.add(producto);
        guardarListaProductos(productos);
    }

    public List<Producto> obtenerProductos() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Producto>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    private void guardarListaProductos(List<Producto> productos) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            oos.writeObject(productos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Producto buscarPorCodigo(String codigo) {
        List<Producto> productos = obtenerProductos();
        for (Producto p : productos) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                return p;
            }
        }
        return null;
    }

    public boolean eliminarPorCodigo(String codigo) {
        List<Producto> productos = obtenerProductos();
        boolean eliminado = productos.removeIf(p -> p.getCodigo().equalsIgnoreCase(codigo));
        if (eliminado) {
            guardarListaProductos(productos);
        }
        return eliminado;
    }
}
