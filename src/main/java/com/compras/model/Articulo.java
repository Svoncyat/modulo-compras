package com.compras.model;

public class Articulo {

    private int id;
    private String nombre;
    private int stock;
    private String proveedor;

    public Articulo(int id, String nombre, int stock, String proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.proveedor = proveedor;
    }

    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public int getStock() {
        return stock;
    }

    public String getProveedor() {
        return proveedor;
    }

}
