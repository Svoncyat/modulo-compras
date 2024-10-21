package com.compras.controller;//Paquete donde se encuentra la clase

import java.util.Scanner;

import com.compras.model.Usuario;//Importa la clase Usuario para representar a un usuario
import com.compras.service.UsuarioServicio;//Importa el servicio que maneja las operaciones relacionadas con usuarios

public class ModuloMenu {//Menu principal del modulo
    private static Scanner sc = new Scanner(System.in);
    private static UsuarioServicio servicio = new UsuarioServicio();//Instancia del servicio de usuario para gestionar la lógica relacionada con usuarios

    public void ejecutar() {//Método que ejecuta el menú y gestiona la interacción con el usuario
        System.out.println("========================================");
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
                System.out.println("========================================");
                    iniciarSesion();
                    break;
                case 2:
                System.out.println("========================================");
                    restablecerContrasena();
                    break;
                case 3:
                System.out.println("========================================");
                    registrarse();
                    break;
                case 4:
                System.out.println("========================================");
                    break;
                default:
                System.out.println("========================================");
                    System.out.println("Opción no válida");
            }
    }

    public static void iniciarSesion() {//Método estático que gestiona el inicio de sesión del usuario
        System.out.println("Ingrese el nombre de usuario:");
        String usuario = sc.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        ;// Llama al servicio para iniciar sesión con los datos ingresados
        ModuloConfiguracion configuracion = new ModuloConfiguracion();
        
        if (servicio.iniciarSesionUsuario(usuario, contrasena)) {
            configuracion.ejecutar();
        }
    }

    public static void restablecerContrasena() {//Método estático que gestiona el restablecimiento de la contraseña del usuario
        System.out.println("Ingrese el nombre de usuario: ");
        String usuario = sc.nextLine();

        System.out.println("Ingrese la nueva contraseña: ");
        String nuevaContrasena = sc.nextLine();

        boolean contrasenaActualizada = servicio.restablecerContrasena(usuario, nuevaContrasena);//// Llama al servicio para restablecer la contraseña y almacena el resultado
     
        if (contrasenaActualizada) {//// Para verificar si la contraseña fue actualizada correctamente e informar al usuario
            System.out.println("Contraseña actualizada correctamente.");
        } else {
            System.out.println("Error al actualizar la contraseña. Verifique el nombre de usuario.");
        }
    }

    public static void registrarse() {//Método estático que gestiona el registro de un nuevo usuario
        System.out.println("Ingrese el nombre de usuario:");
        String usuario = sc.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        Usuario usuariobd = new Usuario(0, usuario, contrasena);//// Crea un nuevo objeto Usuario con los datos que se han ingresados
        servicio.registrarUsuario(usuariobd);// Llama al servicio para registrar el nuevo usuario
    }
}
 