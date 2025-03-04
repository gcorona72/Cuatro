package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InvitadoDAO {
    public void agregarInvitado(String nombre) {
        String sql = "INSERT INTO invitados (nombre) VALUES (?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
            System.out.println("Invitado agregado con éxito.");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error al agregar invitado.");
        }
    }

    public static void main(String[] args) {
        InvitadoDAO dao = new InvitadoDAO();
        dao.agregarInvitado("Juan Pérez");
    }
}
