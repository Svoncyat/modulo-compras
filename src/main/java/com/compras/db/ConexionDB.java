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
        } catch (SQLException e) {
            System.out.println("La conexión a la base de datos ha fallado...");
            e.printStackTrace();
        }
        return con;
    }
}