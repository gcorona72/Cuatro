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

public class BuscarInvitadoTest {

    private Connection connection;
    private int testInvitadoId;

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        assertNotNull(connection, "La conexión a la base de datos no debe ser nula.");


        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO invitados (nombre) VALUES (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, "TestNombre");
            pstmt.executeUpdate();


            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    testInvitadoId = rs.getInt(1);
                }
            }
        }

        assertTrue(testInvitadoId > 0, "El ID del invitado de prueba debe ser válido.");
    }

    @AfterEach
    public void tearDown() throws SQLException {

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM invitados WHERE id = ?")) {
            pstmt.setInt(1, testInvitadoId);
            pstmt.executeUpdate();
        }
        connection.close();
    }

    @Test
    public void testBuscarInvitadoExistente() throws SQLException {

        BuscarInvitado.buscarInvitado(testInvitadoId);


        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM invitados WHERE id = ?")) {
            pstmt.setInt(1, testInvitadoId);
            try (ResultSet rs = pstmt.executeQuery()) {
                assertTrue(rs.next(), "El invitado debería existir en la base de datos.");
                assertEquals("TestNombre", rs.getString("nombre"), "El nombre del invitado no coincide.");
            }
        }
    }

    @Test
    public void testBuscarInvitadoNoExistente() throws SQLException {
        int idInvalido = 99999;


        BuscarInvitado.buscarInvitado(idInvalido);


        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM invitados WHERE id = ?")) {
            pstmt.setInt(1, idInvalido);
            try (ResultSet rs = pstmt.executeQuery()) {
                assertFalse(rs.next(), "No debería existir un invitado con este ID.");
            }
        }
    }
}
