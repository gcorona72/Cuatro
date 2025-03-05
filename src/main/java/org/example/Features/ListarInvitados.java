package org.example.Features;

import org.example.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ListarInvitados {
    public static void listarInvitados() {
        String sql = "SELECT * FROM invitados";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            System.out.println("\n📜 Lista de Invitados:");
            boolean hayInvitados = false;

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                System.out.println("🆔 ID: " + id + " | 📛 Nombre: " + nombre);
                hayInvitados = true;
            }

            if (!hayInvitados) {
                System.out.println("⚠ No hay invitados registrados.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al listar los invitados.");
        }
    }
}
