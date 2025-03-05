package org.example.Features;

import org.example.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarInvitado {
    public static void actualizarInvitado(int id, String nuevoNombre) {
        String sql = "UPDATE invitados SET nombre = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nuevoNombre);
            pstmt.setInt(2, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Invitado con ID " + id + " actualizado correctamente a '" + nuevoNombre + "'.");
            } else {
                System.out.println("⚠ No se encontró un invitado con ID " + id + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al actualizar el invitado.");
        }
    }
}
