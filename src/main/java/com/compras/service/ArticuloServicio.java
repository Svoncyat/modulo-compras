package com.compras.service;

import java.util.List;

import com.compras.DAO.MaestroDAO;
import com.compras.model.Articulo;
import com.compras.model.OrdenCompra;

public class ArticuloServicio {
    private MaestroDAO maestroDAO = new MaestroDAO();

    public List<Articulo> obtenerListaArticulos() {
        return maestroDAO.listaArticulos();
    } 

    public Articulo buscarArticuloNombre(String nombre) {
        return maestroDAO.buscarArticulo(nombre);
    }

    public boolean comprarArticulo(int articuloId, int cantidad, String proveedor) {
        Articulo articulo = maestroDAO.buscarArticuloPorId(articuloId);
        if (articulo != null) {
            int nuevoStock = articulo.getStock() + cantidad;
            maestroDAO.actualizarStock(articuloId, nuevoStock);
            
            // Registra la orden de compra
            OrdenCompra orden = new OrdenCompra(articulo, proveedor, cantidad, "emitido");
            maestroDAO.registrarOrdenCompra(orden);
    
            return true;
        }
        return false;
    }
    
    public boolean devolverArticulo(int articuloId, int cantidad, String proveedor) {
        Articulo articulo = maestroDAO.buscarArticuloPorId(articuloId);
        if (articulo != null) {
            int nuevoStock = articulo.getStock() - cantidad;
            if (nuevoStock < 0) {
                nuevoStock = 0; // No puede haber stock negativo
            }
            maestroDAO.actualizarStock(articuloId, nuevoStock);
            
            // Actualiza o registra una devolución en la orden de compra
            OrdenCompra orden = maestroDAO.obtenerOrdenCompraPorArticuloYProveedor(articuloId, proveedor);
            if (orden != null) {
                if (nuevoStock == articulo.getStock()) {
                    orden.setEstado("recibido");
                } else {
                    orden.setEstado("parcialmente recibido");
                }
                maestroDAO.actualizarOrdenCompra(orden);
            }
    
            return true;
        }
        return false;
    }
}
