package com.compras.controller;

import java.util.List;
import java.util.Scanner;

import com.compras.model.Articulo;
import com.compras.model.OrdenCompra;
import com.compras.service.ArticuloServicio;
import com.compras.service.OrdenCompraServicio;

public class ModuloConfiguracion {
    private Scanner sc = new Scanner(System.in);
    private static ArticuloServicio servicio = new ArticuloServicio();
    private static OrdenCompraServicio ordenServicio = new OrdenCompraServicio();

    public void ejecutar() {
        int eleccion = -1;
        while (eleccion != 6) {
            System.out.println("========================================");
            System.out.println("Acciones: ");
            System.out.println("1. Ver lista de artículos de los proveedores");
            System.out.println("2. Buscar artículo");
            System.out.println("3. Comprar artículo");
            System.out.println("4. Devolver artículo");
            System.out.println("5. Ver ordenes de compra");
            System.out.println("6. Salir");
            System.out.print("---> ");
            eleccion = sc.nextInt();
            sc.nextLine();

            switch (eleccion) {
                case 1:
                    System.out.println("========================================");
                    verListaDeArticulos();
                    break;
                case 2:
                System.out.println("========================================");
                    buscarArticulo();
                    break;
                case 3:
                System.out.println("========================================");
                    compra();
                    break;
                case 4:
                System.out.println("========================================");
                    devolucion();
                    break;
                case 5:
                System.out.println("========================================");
                    verOrdenesCompra();
                    break;
                case 6:
                System.out.println("========================================");
                    break;
                default:
                System.out.println("========================================");
                    System.out.println("Opción no valida");
                    break;
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void verListaDeArticulos() {
        List<Articulo> articulos = servicio.obtenerListaArticulos();
        for (Articulo articulo : articulos) {
            System.out.println(articulo.getId() + ": " + articulo.getNombre() + " - Stock: " + articulo.getStock()
                    + " - Proveedor: " + articulo.getProveedor());
        }
    }

    public void buscarArticulo() {
        System.out.print("Ingrese el nombre del artículo a buscar: ");
        String nombre = sc.nextLine();

        Articulo articulo = servicio.buscarArticuloNombre(nombre);

        if (articulo != null) {
            System.out.println("Artículo encontrado:");
            System.out.println(articulo.getId() + " - Nombre: " + articulo.getNombre() + " - Stock: "
                    + articulo.getStock() + " - Proveedor: " + articulo.getProveedor());
        } else {
            System.out.println("No se encontró ningún artículo con ese nombre.");
        }
    }

    public void compra() {
        System.out.print("Ingrese el nombre del artículo que desea comprar: ");
        String nombre = sc.nextLine();

        Articulo articulo = servicio.buscarArticuloNombre(nombre);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.print("Ingrese la cantidad a comprar: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el nombre del proveedor: ");
        String proveedor = sc.nextLine();

        System.out.println("Registrando la compra...");

        boolean resultadoCompra = servicio.comprarArticulo(articulo.getId(), cantidad, proveedor);

        if (resultadoCompra) {
            System.out.println("Compra registrada exitosamente. Stock actualizado.");
        } else {
            System.out.println("Error en el proceso de compra.");
        }
    }

    public void devolucion() {
        System.out.print("Ingrese el nombre del artículo que desea devolver: ");
        String nombre = sc.nextLine();

        Articulo articulo = servicio.buscarArticuloNombre(nombre);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.print("Ingrese la cantidad a devolver: ");
        int cantidad = sc.nextInt();
        sc.nextLine();

        System.out.print("Ingrese el nombre del proveedor al cual devolver: ");
        String proveedor = sc.nextLine();

        System.out.println("Registrando la devolución...");

        boolean resultadoDevolucion = servicio.devolverArticulo(articulo.getId(), cantidad, proveedor);

        if (resultadoDevolucion) {
            System.out.println("Devolución registrada exitosamente. Stock actualizado.");
        } else {
            System.out.println("Error en el proceso de devolución.");
        }
    }

    private void verOrdenesCompra() {
        List<OrdenCompra> ordenes = ordenServicio.obtenerTodasLasOrdenesCompra();
        System.out.println("Órdenes de compra registradas:");
        for (OrdenCompra orden : ordenes) {
            System.out.println("Artículo: " + orden.getArticulo().getNombre() +
                    " | Proveedor: " + orden.getProveedor() +
                    " | Cantidad: " + orden.getCantidad() +
                    " | Estado: " + orden.getEstado());
        }
    }
}
