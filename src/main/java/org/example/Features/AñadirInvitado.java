package org.example.Features;

import org.example.Database.SupabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AñadirInvitado {
    public static void add(String nombre, boolean acompanante) {
        String sql = "INSERT INTO guests (nombre, acompañante) VALUES (?, ?)";
        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setBoolean(2, acompanante);
            pstmt.executeUpdate();
            System.out.println("✅ Invitado añadido.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
