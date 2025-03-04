package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    private static final String URL = "jdbc:mysql://localhost:3306/";
    private static final String USER = "root";  // Cambia si tu usuario es otro
    private static final String PASSWORD = "";  // Agrega la contraseña si tu MySQL la tiene
    private static final String DB_NAME = "invitados_db";  // Nombre de la base de datos

    public static void main(String[] args) {
        crearBaseDeDatos();
        crearTabla();
    }

    public static void crearBaseDeDatos() {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + DB_NAME;
            stmt.executeUpdate(sql);
            System.out.println("✅ Base de datos '" + DB_NAME + "' creada o ya existente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al crear la base de datos.");
        }
    }

    public static void crearTabla() {
        String urlDB = URL + DB_NAME;
        try (Connection conn = DriverManager.getConnection(urlDB, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS invitados ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "nombre VARCHAR(100) NOT NULL"
                    + ")";
            stmt.executeUpdate(sql);
            System.out.println("✅ Tabla 'invitados' creada o ya existente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al crear la tabla.");
        }
    }
}

