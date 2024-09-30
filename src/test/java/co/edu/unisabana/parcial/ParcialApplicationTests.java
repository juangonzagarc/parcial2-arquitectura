package co.edu.unisabana.parcial;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")  // Esto garantiza que se use el perfil test

class ParcialApplicationTests {

	@Test
	void contextLoads() {
		// Aquí puedes verificar que el contexto carga correctamente
	}
}
