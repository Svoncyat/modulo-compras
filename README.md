**M2: Compras**

**Menú:** - PARCIALMENTE COMPLETADO
- Seguridad -NO HECHO
- Usuarios - HECHO
- Restablecer contraseña - HECHO

**Configuración / Maestros** - NO COMPLETADO
- Artículos / Productos 
    - Stock (campo, no tabla) - HECHO
- Proveedores
- Compradores
- Transacciones
- Orden de compra _(De quien y a quien le estoy comprando)_
    - Estado _(*emitido / *recibido / *backorder o parcialmente recibido)_
- Ingreso (*depende de la orden de compra) **+ Stock**
- Devolución (indicar proveedor) **- Stock**

**Consultas** - NO EMPEZADO
- Stock (mostrar lista de artículos y campo de stock)
- Ordenes

**Reportes (Imprimir)** - NO EMPEZADO
- Stock (sin filtro)
- Ordenes (Rango de fechas)

**Consulta en ChatGPT**

Intenta ayudar en nuestro programa que es un modulo de compras que trata de emular de manera simplificada el proceso de compras de una tienda online, este es la estructura que hemos decidido (mostrar estructura)

La estructura de codigo y carpetas del source (esta estructura puede variar segun sea conveniente, nuestro proyecto esta en una fase muy temprana de desarrollo) es la siguiente:

src\main\java\com\compras\... {

carpeta controller: {
    ModuloConfiguracion.java
    ModuloMenu.java
}

carpeta DAO: {
    MaestroDAO.java
    UsuarioDAO.java
}

carpeta db: {
ConexionDB.java}

carpeta model:{
Articulo.java
Comprador.java
Devolucion.java
OrdenCompra.java
Proveedor.java
Usuario.java}

carpeta service:{
ArticuloServicio.java
OrdenCompraServicio.java
UsuarioServicio.java}

Main.java}

Actualmente tengo completados los archivos, UsuarioServicio.java, todo el paquete model con getters, setters y constructores, ModuloMenu.java, Usuario.java. Es decir la parte del Menu esta completado a excepcion de la seguridad pero eso lo quiero hacer luego.

Mi nivel actual es trainee, por lo que no entiendo algunos conceptos y es por eso que tal vez mi estructura no la plasme correctamente, hazmelo saber. Ademas, segun tengo pensado todas las clases de la carpeta o package model deben contener unicamente el constructor, los getters y los setter, verdad. O es un sesgo por parte de mi propia inexperiencia.

Ahora estoy centrandome en la seccion Configuracion/Maestros, este es mi avance: 

Mi clase ConexionDB.java contiene lo siguiente:

package com.compras.db; //Define el paquete donde se encuentra esta clase

import java.sql.Connection; //Sirve para manejar la conexion a la BD
import java.sql.DriverManager; //Sirve para gestionar las conexiones a la base de datos
import java.sql.SQLException; //Sirve para manejar posibles errores de conexion

public class ConexionDB {
    //Clase privada solo usada en la clase Conexión DB y Final porque el valor no va cambiar
    private static final String url = "jdbc:sqlserver://26.224.151.27:1433;"//Se define la URL de conexión BD utilizando el protocolo JDBC para SQL Server
            + "database=BD_ModuloCompra;"
            + "user=sa;"
            + "password=75859114;"
            + "trustServerCertificate=true;";
 
    public static Connection conectarbd() {//'Connection' tipo de dato propio del Sql
        Connection con = null;
        try {//Captura errores BD, intenta establecer una conexión con la base de datos utilizando la URL proporcionada
            con = DriverManager.getConnection(url);//Se llama la conexxiOn, o en otras palabras se accede a la BD
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("La conexión a la base de datos ha fallado...");
            e.printStackTrace();
        }
        return con;
    }
}

Mi clase ArticuloServicio.java aun no la inicio, pero te muestro mi clase usuario servicio para como una guia:

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

Este es el avance de mi clase DAO de la configuracion/maestros llamada MaestrosDAO.java:

package com.compras.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.compras.db.ConexionDB;
import	com.compras.model.Usuario;
import	com.compras.model.Comprador;
import	com.compras.model.Proveedor;
import	com.compras.model.Articulo;
import	com.compras.model.OrdenCompra;

public class MaestroDAO {
    private Connection con = ConexionDB.conectarbd();

    public String listaArticulos() {
        if (con != null) {
            try {
                String sql = "SELECT * FROM Articulos";
                PreparedStatement query = con.prepareStatement(sql);
                
                query.
            } catch (SQLException e) {
            }
        }
    }
}

Esta es mi clase ModuloConfiguracion.java ligada a ya sabes...

package com.compras.controller;

import java.util.Scanner;

import com.compras.service.UsuarioServicio;

public class ModuloConfiguracion {
    private Scanner sc = new Scanner(System.in);
    private static UsuarioServicio servicio = new UsuarioServicio();

    public void ejecutar() {
        System.out.println("Acciones (Usuario): ");
        System.out.println("1. Ver lista de artículos");
        System.out.println("2. Buscar artículo");
        System.out.println("3. Compra");
        System.out.println("4. Devolucion");
        System.out.println("5. Reporte");
        System.out.println("6. Salir");
        System.out.print("---> ");
        int eleccion = sc.nextInt();
        sc.nextLine();

        switch (eleccion) {
            case 1:
                verListaDeArticulos();
                break;
            case 2:
                buscarArticulo();
                break;
            case 3:
                compra();
                break;
            case 4:
                devolucion();
                break;
            case 5:
                reporte();
                break;
            case 6:
                break;
            default:
                System.out.println("Opción no valida");
                break;
        }
    }

    public void verListaDeArticulos() {

    }

    public void buscarArticulo() {

    }

    public void compra() {

    }

    public void devolucion() {

    }

    public void reporte() {
        
    }
}


Finalmente, mi clase Main.java, que por el momento solo ejecuta la logica del Menu (ModuloMenu.java) pero luego se implementará el ModuloConfiguracion:

package com.compras;

import com.compras.controller.ModuloMenu;

public class Main {
    public static void main(String[] args) {
        ModuloMenu modulo = new ModuloMenu();
        modulo.ejecutar();
    }
}

Dicho esto es solo un avance lo que te muestro, probablemente no este alineado al estandar es por eso que necesito tus opiniones para ayudarme a apegarme al estandar, tal vez mi clase main sea incorrecta o tal vez otras cosas. Hazme saber si necesitas mas ejemplos del modulo casi completo Menu... Te escucho...