package com.compras.service;

import java.util.List;

import com.compras.DAO.OrdenCompraDAO;
import com.compras.model.OrdenCompra;

public class OrdenCompraServicio {
    private OrdenCompraDAO ordenCompraDAO = new OrdenCompraDAO();

    // Método para obtener todas las órdenes de compra
    public List<OrdenCompra> obtenerTodasLasOrdenesCompra() {
        return ordenCompraDAO.obtenerOrdenesCompra();
    }
}
