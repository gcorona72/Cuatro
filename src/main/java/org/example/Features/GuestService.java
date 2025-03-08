package org.example.Features;

import org.example.Database.SupabaseConnection;
import org.example.Model.Guest;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GuestService {

    // ============= 1. CREATE =============
    public static void createGuest(String nombre, boolean acompanante) {
        String sql = "INSERT INTO guests (nombre, acompañante) VALUES (?, ?)";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.setBoolean(2, acompanante);

            pstmt.executeUpdate();
            System.out.println("✅ Invitado creado exitosamente.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al crear invitado.");
        }
    }

    // ============= 2. READ (todos) =============
    public static List<Guest> getAllGuests() {
        List<Guest> guests = new ArrayList<>();
        String sql = "SELECT id, nombre, acompañante FROM guests ORDER BY id ASC";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                Guest g = new Guest(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getBoolean("acompañante")
                );
                guests.add(g);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener invitados.");
        }
        return guests;
    }

    // ============= 3. READ (por ID) =============
    public static Guest getGuestById(int id) {
        Guest guest = null;
        String sql = "SELECT id, nombre, acompañante FROM guests WHERE id = ?";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    guest = new Guest(
                            rs.getInt("id"),
                            rs.getString("nombre"),
                            rs.getBoolean("acompañante")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener invitado por ID.");
        }
        return guest;
    }

    // ============= 4. UPDATE =============
    public static void updateGuest(int id, String newNombre, boolean newAcompanante) {
        String sql = "UPDATE guests SET nombre = ?, acompañante = ? WHERE id = ?";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, newNombre);
            pstmt.setBoolean(2, newAcompanante);
            pstmt.setInt(3, id);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Invitado actualizado exitosamente.");
            } else {
                System.out.println("⚠️ No se encontró invitado con ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al actualizar invitado.");
        }
    }

    // ============= 5. DELETE =============
    public static void deleteGuest(int id) {
        String sql = "DELETE FROM guests WHERE id = ?";

        try (Connection conn = SupabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);
            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("✅ Invitado eliminado exitosamente.");
            } else {
                System.out.println("⚠️ No se encontró invitado con ID " + id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al eliminar invitado.");
        }
    }
}
