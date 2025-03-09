package Features;

import org.example.Features.ActualizarInvitado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ActualizarInvitadoTest {

    @Test
    public void testActualizarInvitado() {
        assertDoesNotThrow(() -> ActualizarInvitado.update(1, "Juan Pérez", false));
    }
}
