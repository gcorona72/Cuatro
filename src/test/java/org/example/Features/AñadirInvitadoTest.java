package org.example.Features;

import org.example.Features.AñadirInvitado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AñadirInvitadoTest {

    @Test
    public void testAñadirInvitado() {
        assertDoesNotThrow(() -> AñadirInvitado.add("Carlos", true));
    }
}
