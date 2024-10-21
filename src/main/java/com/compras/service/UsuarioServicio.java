package com.compras.service;

import com.compras.model.Usuario;
import com.compras.DAO.UsuarioDAO;

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
