package org.example.Database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;
import static org.junit.jupiter.api.Assertions.*;

public class SupabaseConnectionTest {

    @Test
    public void testConexionExitosa() {
        Connection conn = SupabaseConnection.getConnection();
        assertNotNull(conn, "❌ La conexión a la base de datos debería ser exitosa.");
        System.out.println("✅ Conexión exitosa a la base de datos.");
    }

    @Test
    public void testConexionCierre() {
        Connection conn = SupabaseConnection.getConnection();
        assertNotNull(conn, "❌ La conexión no debería ser nula.");

        try {
            conn.close();
            assertTrue(conn.isClosed(), "❌ La conexión debería cerrarse correctamente.");
            System.out.println("✅ La conexión se cerró correctamente.");
        } catch (Exception e) {
            fail("❌ Error al cerrar la conexión: " + e.getMessage());
        }
    }
}
