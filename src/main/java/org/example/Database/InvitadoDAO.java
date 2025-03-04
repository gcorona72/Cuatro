package org.example.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class InvitadoDAO {

    // Método para agregar un invitado a la base de datos
    public void agregarInvitado(String nombre) {
        String sql = "INSERT INTO invitados (nombre) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("✅ Invitado agregado con éxito: " + nombre);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al agregar invitado.");
        }
    }

    // Método para obtener todos los invitados
    public List<String> obtenerInvitados() {
        List<String> listaInvitados = new ArrayList<>();
        String sql = "SELECT * FROM invitados";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                listaInvitados.add(rs.getInt("id") + " - " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("❌ Error al obtener la lista de invitados.");
        }
        return listaInvitados;
    }

    public static void main(String[] args) {
        InvitadoDAO dao = new InvitadoDAO();

        // Agregar un invitado de prueba
        dao.agregarInvitado("Juan Pérez");

        // Mostrar la lista de invitados
        System.out.println("📋 Lista de invitados:");
        for (String invitado : dao.obtenerInvitados()) {
            System.out.println(invitado);
        }

    }
}
