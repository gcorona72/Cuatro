package org.example.Features;

import org.example.Database.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarInvitado {

    public static void eliminarInvitado(int id) {
        String sql = "DELETE FROM invitados WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Invitado con ID " + id + " eliminado con éxito.");
            } else {
                System.out.println("⚠️ No se encontró un invitado con ID " + id + ".");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al eliminar el invitado.");
        }
    }
}

