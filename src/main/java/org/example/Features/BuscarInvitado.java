package org.example.Features;

import org.example.Database.SupabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BuscarInvitado {
    public static String findById(int id) {
        String sql = "SELECT id, nombre, acompañante FROM guests WHERE id = ?";
        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return "ID: " + rs.getInt("id") + " - " + rs.getString("nombre") +
                            " (Acompañante: " + rs.getBoolean("acompañante") + ")";
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invitado no encontrado.";
    }
}
