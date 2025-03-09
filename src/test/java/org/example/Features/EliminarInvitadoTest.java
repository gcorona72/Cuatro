package org.example.Features;

import org.example.Features.EliminarInvitado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EliminarInvitadoTest {

    @Test
    public void testEliminarInvitado() {
        assertDoesNotThrow(() -> EliminarInvitado.delete(1));
    }
}
