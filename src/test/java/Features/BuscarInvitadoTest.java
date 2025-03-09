package Features;

import org.example.Features.BuscarInvitado;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BuscarInvitadoTest {

    @Test
    public void testBuscarInvitadoPorId() {
        String resultado = BuscarInvitado.findById(1);
        assertNotNull(resultado);
    }
}
