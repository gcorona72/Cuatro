package Features;

import org.example.Features.ListarInvitados;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class ListarInvitadoTest {

    @Test
    public void testListarInvitados() {
        List<Map<String, Object>> invitados = ListarInvitados.getAll();

        assertNotNull(invitados, "La lista de invitados no debería ser nula");
        assertTrue(invitados.size() >= 0, "La lista de invitados debe tener al menos 0 elementos");

        // Si la lista tiene elementos, verificamos que cada invitado tenga los datos correctos
        if (!invitados.isEmpty()) {
            for (Map<String, Object> invitado : invitados) {
                assertNotNull(invitado.get("id"), "El ID del invitado no debe ser nulo");
                assertNotNull(invitado.get("nombre"), "El nombre del invitado no debe ser nulo");
                assertNotNull(invitado.get("acompanante"), "El estado de acompañante no debe ser nulo");
                assertTrue(invitado.get("nombre") instanceof String, "El nombre debe ser un String");
                assertTrue(invitado.get("acompanante") instanceof Boolean, "El campo acompañante debe ser un Boolean");
            }
        }
    }
}
