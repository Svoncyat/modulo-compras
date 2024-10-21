package com.compras.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.compras.db.ConexionDB;
import com.compras.model.OrdenCompra;
import com.compras.model.Articulo;

public class OrdenCompraDAO {
    private Connection con = ConexionDB.conectarbd();

    // Método para obtener todas las órdenes de compra
    public List<OrdenCompra> obtenerOrdenesCompra() {
        List<OrdenCompra> listaOrdenes = new ArrayList<>();

        if (con != null) {
            try {
                String sql = "SELECT * FROM OrdenesCompra";
                PreparedStatement query = con.prepareStatement(sql);
                ResultSet result = query.executeQuery();

                while (result.next()) {
                    // Asumimos que ya existe un método para obtener un artículo por ID
                    Articulo articulo = buscarArticuloPorId(result.getInt("idarticulo"));
                    OrdenCompra orden = new OrdenCompra(
                        articulo, 
                        result.getString("proveedor"),
                        result.getInt("cantidad"),
                        result.getString("estado")
                    );
                    listaOrdenes.add(orden);
                }
            } catch (SQLException e) {
                System.out.println("Error al obtener las órdenes de compra.");
                e.printStackTrace();
            }
        }
        return listaOrdenes;
    }

    // Método para obtener artículo por ID (puedes moverlo a MaestroDAO si ya lo tienes allí)
    private Articulo buscarArticuloPorId(int idArticulo) {
        Articulo articulo = null;
        if (con != null) {
            try {
                String sql = "SELECT * FROM Articulos WHERE idarticulo = ?";
                PreparedStatement query = con.prepareStatement(sql);
                query.setInt(1, idArticulo);

                ResultSet result = query.executeQuery();

                if (result.next()) {
                    articulo = new Articulo(result.getInt("idarticulo"), result.getString("nombrearticulo"), result.getInt("stockarticulo"), result.getString("proveedorarticulo"));
                }
            } catch (SQLException e) {
                System.out.println("Error al buscar el artículo.");
                e.printStackTrace();
            }
        }
        return articulo;
    }
}