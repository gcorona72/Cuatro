package org.example.Features;

import org.example.Database.SupabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ActualizarInvitado {
    public static void update(int id, String newNombre, boolean newAcompanante) {
        String sql = "UPDATE guests SET nombre = ?, acompañante = ? WHERE id = ?";
        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, newNombre);
            pstmt.setBoolean(2, newAcompanante);
            pstmt.setInt(3, id);
            pstmt.executeUpdate();
            System.out.println("✅ Invitado actualizado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
