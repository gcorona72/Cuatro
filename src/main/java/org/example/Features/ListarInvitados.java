package org.example.Features;

import org.example.Database.SupabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListarInvitados {
    public static List<Map<String, Object>> getAll() {


        List<Map<String, Object>> invitados = new ArrayList<>();
        String sql = "SELECT id, nombre, acompañante FROM guests";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Map<String, Object> invitado = new HashMap<>();
                invitado.put("id", rs.getInt("id"));
                invitado.put("nombre", rs.getString("nombre"));
                invitado.put("acompanante", rs.getBoolean("acompañante"));
                invitados.add(invitado);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return invitados;
    }
}
