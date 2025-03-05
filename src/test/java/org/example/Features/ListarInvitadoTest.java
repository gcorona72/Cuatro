package org.example.Features;

import org.example.Database.DatabaseConnection;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ListarInvitadoTest {

    private Connection connection;
    private int testInvitadoId1;
    private int testInvitadoId2;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() throws SQLException {
        connection = DatabaseConnection.getConnection();
        assertNotNull(connection, "La conexión a la base de datos no debe ser nula.");

        System.setOut(new PrintStream(outputStream));

        try (PreparedStatement pstmt = connection.prepareStatement(
                "INSERT INTO invitados (nombre) VALUES (?), (?)", PreparedStatement.RETURN_GENERATED_KEYS)) {
            pstmt.setString(1, "InvitadoTest1");
            pstmt.setString(2, "InvitadoTest2");
            pstmt.executeUpdate();

            try (ResultSet rs = pstmt.getGeneratedKeys()) {
                if (rs.next()) {
                    testInvitadoId1 = rs.getInt(1);
                }
                if (rs.next()) {
                    testInvitadoId2 = rs.getInt(1);
                }
            }
        }

        assertTrue(testInvitadoId1 > 0 && testInvitadoId2 > 0, "Los IDs de los invitados de prueba deben ser válidos.");
    }

    @AfterEach
    public void tearDown() throws SQLException {

        System.setOut(System.out);

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM invitados WHERE id IN (?, ?)")) {
            pstmt.setInt(1, testInvitadoId1);
            pstmt.setInt(2, testInvitadoId2);
            pstmt.executeUpdate();
        }
        connection.close();
    }

    @Test
    public void testListarInvitadosConDatos() {

        ListarInvitados.listarInvitados();

        String output = outputStream.toString().trim();

        assertTrue(output.contains("InvitadoTest1"), "La lista debe contener 'InvitadoTest1'.");
        assertTrue(output.contains("InvitadoTest2"), "La lista debe contener 'InvitadoTest2'.");
        assertTrue(output.contains("📜 Lista de Invitados"), "Debe mostrar el encabezado 'Lista de Invitados'.");
    }

    @Test
    public void testListarInvitadosSinDatos() throws SQLException {

        try (PreparedStatement pstmt = connection.prepareStatement("DELETE FROM invitados")) {
            pstmt.executeUpdate();
        }

        outputStream.reset();

        ListarInvitados.listarInvitados();

        String output = outputStream.toString().trim();

        assertTrue(output.contains("⚠ No hay invitados registrados."), "Debe mostrar mensaje de que no hay invitados.");
    }
}
