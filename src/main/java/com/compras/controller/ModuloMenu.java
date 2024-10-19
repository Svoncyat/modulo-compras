package com.compras.controller;

import java.util.Scanner;

import com.compras.model.Usuario;
import com.compras.service.UsuarioServicio;

public class ModuloMenu {
    private static Scanner sc = new Scanner(System.in);
    private static UsuarioServicio servicio = new UsuarioServicio();

    public void ejecutar() {
        System.out.println("Menú: ");
            System.out.println("1. Iniciar Sesión");
            System.out.println("2. Restablecer mi contraseña");
            System.out.println("3. Registrarse");
            System.out.println("4. Salir");
            System.out.print("---> ");
            int eleccion = sc.nextInt();
            sc.nextLine();

            switch (eleccion) {
                case 1:
                    iniciarSesion();
                    break;
                case 2:
                    restablecerContrasena();
                    break;
                case 3:
                    registrarse();
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Opción no válida");
            }
    }

    public static void iniciarSesion() {
        System.out.println("Ingrese el nombre de usuario:");
        String usuario = sc.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        servicio.iniciarSesionUsuario(usuario, contrasena);
    }

    public static void restablecerContrasena() {
        System.out.println("Ingrese el nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.println("Ingrese la nueva contraseña: ");
        String nuevaContrasena = sc.nextLine();

        boolean contrasenaActualizada = servicio.restablecerContrasena(usuario, nuevaContrasena);

        if (contrasenaActualizada) {
            System.out.println("Contraseña actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar la contraseña. Verifique el nombre de usuario.");
        }
    }

    public static void registrarse() {
        System.out.println("Ingrese el nombre de usuario:");
        String usuario = sc.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        Usuario usuariobd = new Usuario(0, usuario, contrasena);
        servicio.registrarUsuario(usuariobd);
    }
}
