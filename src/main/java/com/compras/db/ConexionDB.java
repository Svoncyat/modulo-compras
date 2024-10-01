package com.compras.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String url = "jdbc:sqlserver://localhost:1433;"
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
