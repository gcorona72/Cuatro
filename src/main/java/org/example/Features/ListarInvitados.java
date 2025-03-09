package org.example.Features;

import org.example.Database.SupabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ListarInvitados {
    public static List<String> getAll() {
        List<String> guests = new ArrayList<>();
        String sql = "SELECT id, nombre, acompañante FROM guests";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                guests.add("#" + rs.getInt("id") + " - " + rs.getString("nombre") +
                        " (Acompañante: " + rs.getBoolean("acompañante") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guests;
    }
}
