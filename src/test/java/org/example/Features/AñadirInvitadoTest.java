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

public class AñadirInvitadoTest {

    private Connection connection;

    @BeforeEach
    public void setUp() {

        connection = DatabaseConnection.getConnection();
        assertNotNull(connection, "La conexión a la base de datos no debe ser nula.");
    }

    @AfterEach
    public void tearDown() throws SQLException {

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM invitados WHERE nombre = 'TestNombre'")) {
            pstmt.executeUpdate();
        }
        connection.close();
    }

    @Test
    public void testAgregarInvitado() throws SQLException {

        AñadirInvitado.agregarInvitado("TestNombre");

        try (PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM invitados WHERE nombre = 'TestNombre'");
             ResultSet rs = pstmt.executeQuery()) {

            assertTrue(rs.next(), "El invitado no fue agregado correctamente.");
            assertEquals("TestNombre", rs.getString("nombre"), "El nombre no coincide.");
        }
    }
}
