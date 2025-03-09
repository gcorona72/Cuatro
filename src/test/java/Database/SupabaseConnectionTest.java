package Database;

import org.example.Database.SupabaseConnection;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

public class SupabaseConnectionTest {
    @Test
    public void testConexionExitosa() {
        Connection conn = SupabaseConnection.getConnection();
        assertNotNull(conn, "La conexión a la base de datos debería ser exitosa.");
    }

    @Test
    public void testConexionCierre() {
        Connection conn = SupabaseConnection.getConnection();
        assertNotNull(conn, "La conexión a la base de datos no debería ser nula.");

        try {
            conn.close();
            assertTrue(conn.isClosed(), "La conexión debería cerrarse correctamente.");
        } catch (Exception e) {
            fail("Error al cerrar la conexión: " + e.getMessage());
        }
    }}
