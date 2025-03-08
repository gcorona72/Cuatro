package org.example;

import org.example.Database.SupabaseConnection;
import java.awt.Desktop;
import java.net.URI;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        // Inicia tu SparkServer (asegúrate de que sirva la carpeta /public)
        SparkServer.start();

        // Verifica la conexión a Supabase
        Connection conn = SupabaseConnection.getConnection();
        if (conn != null) {
            System.out.println("✅ Conexión exitosa a Supabase!");
        } else {
            System.out.println("❌ Error al conectar con la base de datos.");
        }

        // Fuerza la apertura del navegador
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI("http://localhost:4567"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
