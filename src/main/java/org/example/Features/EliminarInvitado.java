package org.example.Features;

import org.example.Database.SupabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EliminarInvitado {
    public static void delete(int id) {
        String sql = "DELETE FROM guests WHERE id = ?";
        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            System.out.println("✅ Invitado eliminado.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
