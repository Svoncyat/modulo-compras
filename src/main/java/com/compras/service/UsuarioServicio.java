package com.compras.service;

import com.compras.db.ConexionDB;
import com.compras.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

class UsuarioDAO {
    private Connection con = ConexionDB.conectarbd();
    // Metodo para agregar nuevos usuarios a la base de datos
    public void agregarUsuario(Usuario usuario) {
        if (con != null) {
            try {
                String sql = "INSERT INTO Usuarios (usuario, contrasena) VALUES (?, ?)";
                PreparedStatement query = con.prepareStatement(sql);

                query.setString(1, usuario.getUsuario());
                query.setString(2, usuario.getContrasena());
                query.executeUpdate();
                System.out.println("Datos agregados correctamente");
            } catch (SQLException e) {
                System.out.println("Error al insertar usuario");
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

    public Usuario validarUsuario(String usuario, String contrasena) {
        Usuario usuariovalidado = null;

        if (con != null) {
            try{
                String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasena = ?";
                PreparedStatement query = con.prepareStatement(sql);

                query.setString(1, usuario);
                query.setString(2, contrasena);
                ResultSet resultado = query.executeQuery();

                if(resultado.next()) {
                    int idusuario = resultado.getInt("id");
                    String nombreusuario = resultado.getString("usuario"); 
                    String contrasenausuario = resultado.getString("contrasena");

                    usuariovalidado = new Usuario(idusuario, nombreusuario, contrasenausuario);
                }
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

        return usuariovalidado;
    }

    public boolean actualizarContrasena(String usuario, String nuevaContrasena) {
        Connection con = ConexionDB.conectarbd();
        boolean contrasenaActualizada = false;

        if (con != null) {
            try {
                String sql = "UPDATE Usuarios SET contrasena = ? WHERE usuario = ?";
                PreparedStatement query = con.prepareStatement(sql);
                query.setString(1, nuevaContrasena);
                query.setString(2, usuario);

                int filasActualizadas = query.executeUpdate();

                if (filasActualizadas > 0) {
                    contrasenaActualizada = true;
                    System.out.println("Contraseña actualizada correctamente");
                }
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
        return contrasenaActualizada;
    }
}

public class UsuarioServicio {
    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    // Metodo para validar que el usuario y la contraseña sean obligatorios
    public void registrarUsuario(Usuario usuario) {
        if (usuario.getUsuario().isEmpty() || usuario.getContrasena().isEmpty()) {
            System.out.println("El nombre de usuario o la contraseña no pueden estar vacíos.");
            return;
        }

        // Si la validacion ocurre, entonces se llama al metodo que agrega un usuario a la base de datos
        usuarioDAO.agregarUsuario(usuario);
    }

    public boolean iniciarSesionUsuario(String usuario, String contrasena) {
        Usuario usuariovalidado = usuarioDAO.validarUsuario(usuario, contrasena);

        if (usuariovalidado != null) {
            System.out.println("Inicio de sesion exitoso. Bienvenido al modulo de compras");
            return true;
        } else {
            System.out.println("Nombre de usuario o contraseña incorrectas");
            return false;
        }
    }

    public boolean restablecerContrasena(String usuario, String nuevaContrasena) {
        return usuarioDAO.actualizarContrasena(usuario, nuevaContrasena);
    }
}
