package Features;

import org.example.Features.ListarInvitados;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class ListarInvitadoTest {

    @Test
    public void testListarInvitados() {
        List<String> invitados = ListarInvitados.getAll();
        assertNotNull(invitados);
        assertTrue(invitados.size() >= 0);
    }
}
