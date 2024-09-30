package co.edu.unisabana.parcial;


import co.edu.unisabana.parcial.service.model.User;
import co.edu.unisabana.parcial.service.model.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")  // Usar el perfil de pruebas
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void testSaveAndFindUser() {
        // Crear un nuevo usuario
        User user = new User();
        user.setName("Repository Test User");

        // Guardar el usuario en el repositorio
        User savedUser = userRepository.save(user);

        // Verificar que el usuario fue guardado correctamente
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        // Recuperar el usuario por ID
        User retrievedUser = userRepository.findById(savedUser.getId()).orElse(null);

        // Verificar que el usuario fue recuperado correctamente
        assertNotNull(retrievedUser);
        assertEquals("Repository Test User", retrievedUser.getName());
    }
}
