package com.compras.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.compras.db.ConexionDB;
import com.compras.model.Articulo;
import com.compras.model.OrdenCompra;

public class MaestroDAO {
    private Connection con = ConexionDB.conectarbd();

    // Método para obtener la lista de artículos
    public List<Articulo> listaArticulos() {
        List<Articulo> lista = new ArrayList<>();

        if (con != null) {
            try {
                String sql = "SELECT * FROM Articulos";
                PreparedStatement query = con.prepareStatement(sql);
                ResultSet result = query.executeQuery();
                
                while (result.next()) {
                    Articulo articulo = new Articulo(result.getInt("idarticulo"), result.getString("nombrearticulo"), result.getInt("stockarticulo"), result.getString("proveedorarticulo"));
                    lista.add(articulo);
                }
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error");
                e.printStackTrace();
            }
        }
        return lista;
    }

    // Método para buscar un artículo por nombre
    public Articulo buscarArticulo(String nombre) {
        Articulo articulo = null;
        if (con != null) {
            try {
                String sql = "SELECT * FROM Articulos WHERE nombrearticulo = ? ";
                PreparedStatement query = con.prepareStatement(sql);
                query.setString(1, nombre);

                ResultSet result = query.executeQuery();
                
                if (result.next()) {
                    articulo = new Articulo(result.getInt("idarticulo"), result.getString("nombrearticulo"), result.getInt("stockarticulo"), result.getString("proveedorarticulo"));
                }

            } catch (Exception e) {
                System.out.println("Ha ocurrido un error");
                e.printStackTrace();
            }
        }
        return articulo;
    }

    // Método para actualizar el stock de un artículo
    public boolean actualizarStock(int idArticulo, int nuevoStock) {
        if (con != null) {
            try {
                String sql = "UPDATE Articulos SET stockarticulo = ? WHERE idarticulo = ?";
                PreparedStatement query = con.prepareStatement(sql);
                query.setInt(1, nuevoStock);
                query.setInt(2, idArticulo);

                int filasActualizadas = query.executeUpdate();
                return filasActualizadas > 0; // Retorna true si se actualizó el stock correctamente
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error al actualizar el stock");
                e.printStackTrace();
            }
        }
        return false;
    }

    // Método para registrar una nueva orden de compra
    public boolean registrarOrdenCompra(OrdenCompra orden) {
        if (con != null) {
            try {
                String sql = "INSERT INTO OrdenesCompra (idarticulo, proveedor, cantidad, estado) VALUES (?, ?, ?, ?)";
                PreparedStatement query = con.prepareStatement(sql);
                query.setInt(1, orden.getArticulo().getId());
                query.setString(2, orden.getProveedor());
                query.setInt(3, orden.getCantidad());
                query.setString(4, orden.getEstado());

                int filasInsertadas = query.executeUpdate();
                return filasInsertadas > 0; // Retorna true si se insertó la orden correctamente
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error al registrar la orden de compra");
                e.printStackTrace();
            }
        }
        return false;
    }

    // Método para actualizar el estado de una orden de compra
    public boolean actualizarOrdenCompra(OrdenCompra orden) {
        if (con != null) {
            try {
                String sql = "UPDATE OrdenesCompra SET estado = ? WHERE idarticulo = ? AND proveedor = ?";
                PreparedStatement query = con.prepareStatement(sql);
                query.setString(1, orden.getEstado());
                query.setInt(2, orden.getArticulo().getId());
                query.setString(3, orden.getProveedor());

                int filasActualizadas = query.executeUpdate();
                return filasActualizadas > 0; // Retorna true si se actualizó el estado de la orden correctamente
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error al actualizar la orden de compra");
                e.printStackTrace();
            }
        }
        return false;
    }

    // Método para obtener una orden de compra por artículo y proveedor
    public OrdenCompra obtenerOrdenCompraPorArticuloYProveedor(int idArticulo, String proveedor) {
        OrdenCompra orden = null;
        if (con != null) {
            try {
                String sql = "SELECT * FROM OrdenesCompra WHERE idarticulo = ? AND proveedor = ?";
                PreparedStatement query = con.prepareStatement(sql);
                query.setInt(1, idArticulo);
                query.setString(2, proveedor);

                ResultSet result = query.executeQuery();
                
                if (result.next()) {
                    Articulo articulo = buscarArticuloPorId(idArticulo); // Método para buscar artículo por ID
                    orden = new OrdenCompra(articulo, result.getString("proveedor"), result.getInt("cantidad"), result.getString("estado"));
                }
            } catch (SQLException e) {
                System.out.println("Ha ocurrido un error al obtener la orden de compra");
                e.printStackTrace();
            }
        }
        return orden;
    }

    // Método para buscar artículo por ID 
    public Articulo buscarArticuloPorId(int idArticulo) {
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
                System.out.println("Ha ocurrido un error al buscar el artículo");
                e.printStackTrace();
            }
        }
        return articulo;
    }
}