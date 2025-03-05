package org.example.Database;

import org.junit.jupiter.api.Test;
import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DatabaseConnectionTest {

    @Test
    void testConnection() {
        Connection connection = DatabaseConnection.getConnection();
        assertNotNull(connection, "La conexión a la base de datos debería ser válida");
    }
}
