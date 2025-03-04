package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/invitados_db";
    private static final String USER = "root";  // Cambia si tienes otro usuario
    private static final String PASSWORD = "";  // Si tu MySQL tiene contraseña, agrégala aquí

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa a la base de datos.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            System.out.println("Error al conectar a la base de datos.");
        }
        return connection;
    }

    public static void main(String[] args) {
        getConnection();  // Prueba la conexión
    }
}
