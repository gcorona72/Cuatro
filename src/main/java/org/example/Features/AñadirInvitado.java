package org.example.Features;

import org.example.Database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AñadirInvitado {

    public static void agregarInvitado(String nombre) {

        String sql = "INSERT INTO invitados (nombre) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("✅ Invitado '" + nombre + "' agregado.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al agregar el invitado.");
        }

    }
}
