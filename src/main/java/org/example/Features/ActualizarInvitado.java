package org.example.Features;

import org.example.Database.SupabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarInvitado {
    public static void update(int id, String newNombre, boolean newAcompanante) {

        if (newNombre == null || newNombre.trim().isEmpty()) {
            System.out.println("❌ ERROR: El nuevo nombre no puede ser nulo o vacío.");
            return;
        }

        String sql = "UPDATE guests SET nombre = ?, acompañante = ? WHERE id = ?";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newNombre);
            pstmt.setBoolean(2, newAcompanante);
            pstmt.setInt(3, id);


            int filasActualizadas = pstmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("✅ Invitado actualizado correctamente.");
            } else {
                System.out.println("⚠ No se encontró el invitado con ID: " + id);
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
