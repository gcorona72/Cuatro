package org.example.Features;

import org.example.Database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EliminarInvitadoTest {

    private Connection connection;
    private int testInvitadoId;

    @BeforeEach
    public void setUp() throws SQLException {

        connection = DatabaseConnection.getConnection();
        assertNotNull(connection, "La conexión a la base de datos no debe ser nula.");


        String insertSQL = "INSERT INTO invitados (nombre) VALUES ('TestInvitado')";
        try (PreparedStatement pstmt = connection.prepareStatement(insertSQL, PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                testInvitadoId = rs.getInt(1);
            }
        }
    }

    @AfterEach
    public void tearDown() throws SQLException {

        String deleteSQL = "DELETE FROM invitados WHERE nombre = 'TestInvitado'";
        try (PreparedStatement pstmt = connection.prepareStatement(deleteSQL)) {
            pstmt.executeUpdate();
        }
        connection.close();
    }

    @Test
    public void testEliminarInvitadoExistente() throws SQLException {

        EliminarInvitado.eliminarInvitado(testInvitadoId);

        String checkSQL = "SELECT * FROM invitados WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(checkSQL)) {
            pstmt.setInt(1, testInvitadoId);
            ResultSet rs = pstmt.executeQuery();
            assertFalse(rs.next(), "El invitado debería haber sido eliminado.");
        }
    }

    @Test
    public void testEliminarInvitadoInexistente() {
        int idInexistente = 999999;
        EliminarInvitado.eliminarInvitado(idInexistente);
    }
}
