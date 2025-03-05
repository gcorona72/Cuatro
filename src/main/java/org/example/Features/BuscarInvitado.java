package org.example.Features;

import org.example.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarInvitado {
    public static void buscarInvitado(int id) {
        String sql = "SELECT * FROM invitados WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                System.out.println("✅ Invitado encontrado:");
                System.out.println("🆔 ID: " + rs.getInt("id"));
                System.out.println("📛 Nombre: " + rs.getString("nombre"));
            } else {
                System.out.println("⚠ No se encontró un invitado con ID " + id);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al buscar el invitado.");
        }
    }
}
