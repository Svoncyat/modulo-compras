package com.compras.model;

public class OrdenCompra {
    private Articulo articulo;    // El artículo que se está comprando
    private String proveedor;     // El proveedor del artículo
    private int cantidad;         // Cantidad de artículos comprados
    private String estado;        // Estado de la orden (emitido, recibido, backorder, etc.)

    // Constructor
    public OrdenCompra(Articulo articulo, String proveedor, int cantidad, String estado) {
        this.articulo = articulo;
        this.proveedor = proveedor;
        this.cantidad = cantidad;
        this.estado = estado;
    }

    // Getters y Setters
    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }

    public String getProveedor() {
        return proveedor;
    }

    public void setProveedor(String proveedor) {
        this.proveedor = proveedor;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    // Método para mostrar la información de la orden
    @Override
    public String toString() {
        return "Orden de Compra: Artículo: " + articulo.getNombre() +
               ", Proveedor: " + proveedor +
               ", Cantidad: " + cantidad +
               ", Estado: " + estado;
    }
}