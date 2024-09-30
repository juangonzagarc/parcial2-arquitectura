package co.edu.unisabana.parcial;



import co.edu.unisabana.parcial.service.model.UserService;
import co.edu.unisabana.parcial.service.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
@ActiveProfiles("test")  // Usar el perfil de pruebas
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void testCreateAndFindUser() {
        // Crear un nuevo usuario
        User user = new User();
        user.setName("Test User");

        // Guardar el usuario
        User savedUser = userService.saveUser(user);

        // Verificar que el usuario ha sido guardado
        assertNotNull(savedUser);
        assertNotNull(savedUser.getId());

        // Recuperar el usuario por ID
        User retrievedUser = userService.findUserById(savedUser.getId()).orElse(null);

        // Verificar que el usuario fue recuperado correctamente
        assertNotNull(retrievedUser);
        assertEquals("Test User", retrievedUser.getName());
    }
}
