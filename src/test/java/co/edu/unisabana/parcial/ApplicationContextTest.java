package co.edu.unisabana.parcial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
class ApplicationContextTest {

    @Test
    void contextLoads() {
        // Esta prueba solo verifica si el contexto de la aplicación se carga con el perfil de test
    }
}
