package org.example.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SupabaseConnection {

    // URL de conexión (incluye sslmode=require para conectarse con SSL)
    private static final String URL = "jdbc:postgresql://aws-0-us-east-1.pooler.supabase.com:5432/postgres?user=postgres.ndapktzfypmgnlqhtxcy&password=rubencio1997?";

    // Usuario de la base de datos (por defecto en Supabase es "postgres")
    private static final String USER = "postgres";

    // Contraseña de tu base de datos en Supabase
    private static final String PASSWORD = "rubencio1997?";

    public static Connection getConnection() {
        try {
            // Cargar el driver de PostgreSQL
            Class.forName("org.postgresql.Driver");

            // Establecer la conexión
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
