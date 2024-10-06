package com.compras.model;

public class Articulo {

    protected  String codigo;
    protected  String nombre;
    protected  int stock;
    
    public Articulo(String codigo, String nombre, int stock) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
    }

    
    public void actualizarStock(int cantidad) {
        this.stock += cantidad;
    }

    
    public String getCodigo() { 
        return codigo;
     }
    public String getNombre() { 
        return nombre; 
     }
    public int getStock() { 
        return stock; 
     }
}
    
