package com.compras.service;

import com.compras.db.ConexionDB;
import com.compras.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UsuarioServicio {
    public void agregarUsuario(Usuario usuario) {
        Connection con = ConexionDB.conectarbd();

        if (con != null) {
            try {
                String sql = "INSERT INTO Usuarios (id, usuario, contrasena) VALUES (?, ?, ?)";
                PreparedStatement query = con.prepareStatement(sql);

                query.setInt(1, usuario.getId());
                query.setString(2, usuario.getUsuario());
                query.setString(3, usuario.getContrasena());
                query.executeUpdate();
                System.out.println("Datos agregados correctamente");
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
