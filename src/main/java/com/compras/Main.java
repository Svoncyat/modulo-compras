package com.compras;

import com.compras.model.Usuario;
import com.compras.service.UsuarioServicio;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        UsuarioServicio servicio = new UsuarioServicio();

        System.out.println("Ingrese nombre de usuario:");
        String usuario = sc.nextLine();

        System.out.println("Ingrese contraseña: ");
        String contrasena = sc.nextLine();

        int id = 1;

        Usuario usuariobd = new Usuario(id, usuario, contrasena);
        servicio.agregarUsuario(usuariobd);
    }
}