**M2: Compras**

**Menú:**
- Seguridad
- Usuarios
- Restablecer contraseña

**Configuración / Maestros**
- Artículos / Productos 
    - Stock (campo, no tabla)
- Proveedores
- Compradores
- Transacciones
- Orden de compra _(De quien y a quien le estoy comprando)_
    - Estado _(*emitido / *recibido / *backorder o parcialmente recibido)_
- Ingreso (*depende de la orden de compra) **+ Stock**
- Devolución (indicar proveedor) **- Stock**

**Consultas**
- Stock (mostrar lista de artículos y campo de stock)
- Ordenes

**Reportes (Imprimir)**
- Stock (sin filtro)
- Ordenes (Rango de fechas)

**Consulta en ChatGPT**

Intenta ayudar en nuestro programa que es un modulo de compras que trata de emular de manera simplificada el proceso de compras de una tienda online, este es la estructura que hemos decidido (mostrar estructura)

La estructura de codigo y carpetas del source (esta estructura puede variar segun sea conveniente, nuestro proyecto esta en una fase muy temprana de desarrollo) es la siguiente:

src\main\java\com\compras\... {
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

Mi nivel actual es trainee, por lo que no entiendo algunos conceptos y es por eso que tal vez mi estructura no la plasme correctamente, hazmelo saber. Ademas, segun tengo pensado todas las clases de la carpeta o package model deben contener unicamente el constructor, los getters y los setter, verdad. O es un sesgo por parte de mi propia inexperiencia.

Mi clase ConexionDB.java contiene lo siguiente:

package com.compras.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String url = "jdbc:sqlserver://26.201.13.126:1433;"
            + "database=BD_ModuloCompra;"
            + "user=sa;"
            + "password=5284;"
            + "trustServerCertificate=true;";

    public static Connection conectarbd() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url);
            System.out.println("Conexión exitosa a la base de datos");
        } catch (SQLException e) {
            System.out.println("La conexión a la base de datos ha fallado...");
            e.printStackTrace();
        }
        return con;
    }
}

ademas mi clase UsuarioServicio.java tiene:

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

Y mi clase Main.java, que no se si la estoy sobrecargando de informacion o es correcta contiene lo siguiente:

package com.compras;

import com.compras.model.Usuario;
import com.compras.service.UsuarioServicio;

import java.util.Scanner;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static UsuarioServicio servicio = new UsuarioServicio();

    public static void main(String[] args) {
        System.out.println("Menú: ");
        System.out.println("1. Iniciar Sesion");
        System.out.println("2. Registrarse");
        System.out.print("---> ");
        int eleccion = sc.nextInt();

        switch (eleccion) {
            case 1:

                break;
            case 2:

                break;
            default:
                break;
        }
    }

    public static void IniciarSesion() {
        
    }

    public static void Registrarse() {
        System.out.println("Ingrese el nombre de usuario:");
        String usuario = sc.nextLine();

        System.out.println("Ingrese su contraseña: ");
        String contrasena = sc.nextLine();

        int id = 1;

        Usuario usuariobd = new Usuario(id, usuario, contrasena);
        servicio.agregarUsuario(usuariobd);
    }
}

Dicho esto es solo un avance lo que te muestro, probablemente no este alineado al estandar es por eso que necesito tus opiniones para ayudarme a apegarme al estandar, tal vez mi clase main sea incorrecta o tal vez otras cosas. Te escucho...