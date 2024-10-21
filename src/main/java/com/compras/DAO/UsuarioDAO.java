package com.compras.DAO;

import java.sql.Connection;//Sirve para manejar la conexión a la base de datos
import java.sql.PreparedStatement;//Sirve para ejecutar consultas SQL con parámetros
import java.sql.ResultSet;//Sirve para manejar los resultados de las consultas SQL
import java.sql.SQLException;//Sirve para manejar errores de SQL

import com.compras.db.ConexionDB;//Clase que gestiona la conexión a la BD
import com.compras.model.Usuario;//Modelo usuario

public class UsuarioDAO {// Clase que contiene los métodos de acceso a los datos para la tabla "Usuarios"
    private Connection con = ConexionDB.conectarbd();

    public Usuario validarUsuario(String usuario, String contrasena) {//// Método para validar si un usuario existe en
                                                                      //// la base de datos con las credenciales
                                                                      //// correctas
        Usuario usuariovalidado = null;

        if (con != null) {// Verifica si la conexxión no es nula
            try {// Consulta SQL que busca un usuario con el nombre de usuario y la contraseña
                 // proporcionados
                String sql = "SELECT * FROM Usuarios WHERE usuario = ? AND contrasena = ?";
                PreparedStatement query = con.prepareStatement(sql);

                query.setString(1, usuario);// Asigna los valores de los parámetros en la consulta
                query.setString(2, contrasena);
                ResultSet resultado = query.executeQuery();

                if (resultado.next()) {// Si hay resultados (el usuario fue encontrado), se construye el objeto Usuario
                    int idusuario = resultado.getInt("id");
                    String nombreusuario = resultado.getString("usuario");
                    String contrasenausuario = resultado.getString("contrasena");

                    usuariovalidado = new Usuario(idusuario, nombreusuario, contrasenausuario);// Crea una instancia de
                                                                                               // Usuario con los datos
                                                                                               // obtenidos
                }
            } catch (SQLException e) {
                e.printStackTrace();// Muestra si ocurre algun error en la ejecucion en SQL
            } finally {
                try {
                    con.close();// Cierra la conecion a la BD
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return usuariovalidado;
    }

    // Metodo para agregar nuevos usuarios a la base de datos
    public void agregarUsuario(Usuario usuario) {
        if (con != null) {
            try {
                String sql = "INSERT INTO Usuarios (usuario, contrasena) VALUES (?, ?)";//Consulta SQL para insertar un nuevo usuario con su nombre y contraseña
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

    public boolean actualizarContrasena(String usuario, String nuevaContrasena) {//Método para actualizar la contraseña de un usuario en la BD
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
