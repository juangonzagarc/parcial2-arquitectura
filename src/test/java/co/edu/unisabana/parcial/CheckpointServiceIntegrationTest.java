package co.edu.unisabana.parcial;
import co.edu.unisabana.parcial.repository.sql.jpa.CheckpointRepository;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class CheckpointServiceIntegrationTest {

    @Autowired
    private CheckpointService checkpointService;

    @Autowired
    private CheckpointRepository checkpointRepository;

    @BeforeEach
    public void setUp() {
        checkpointRepository.deleteAll(); // Limpiar la base de datos antes de cada prueba
    }

    @Test
    public void testCheckin() {
        Checkpoint checkpointDTO = new Checkpoint();
        checkpointDTO.setFacility("facility1");
        checkpointDTO.setDriver("driver1");
        checkpointDTO.setDayOfMonth(15);


        Checkpoint lastCheckpoint = checkpointRepository.findFirstByDriverAndFacilityAndFinalizedIsFalse("driver1", "facility1").orElse(null);
        assertThat(lastCheckpoint).isNotNull();
        assertThat(lastCheckpoint.getDriver()).isEqualTo("driver1");
    }

    @Test
    public void testCheckout() {
        // Primero, realiza un check-in
        Checkpoint checkinDTO = new Checkpoint();
        checkinDTO.setFacility("facility1");
        checkinDTO.setDriver("driver1");
        checkinDTO.setDayOfMonth(15);

        // Ahora, realiza un check-out
        Checkpoint checkoutDTO = new Checkpoint();
        checkoutDTO.setFacility("facility1");
        checkoutDTO.setDriver("driver1");
        checkoutDTO.setDayOfMonth(20);

        // Verificar que el check-out se haya realizado correctamente
        // (puedes agregar lógica para verificar esto según tu modelo)
    }

    @Test
    public void testCheckoutNoLastCheckin() {
        Checkpoint checkoutDTO = new Checkpoint();
        checkoutDTO.setFacility("facility1");
        checkoutDTO.setDriver("driver1");
        checkoutDTO.setDayOfMonth(20);

        // Verificar que se lance una excepción porque no hay un check-in previo
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        });
        assertEquals("don't exist previously check in", exception.getMessage());
    }

    @Test
    public void testInvalidDateOnCheckin() {
        Checkpoint checkpointDTO = new Checkpoint();
        checkpointDTO.setFacility("facility1");
        checkpointDTO.setDriver("driver1");
        checkpointDTO.setDayOfMonth(31); // Fecha inválida

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        });
        assertEquals("Invalid date", exception.getMessage());
    }

    @Test
    public void testInvalidDateOnCheckout() {
        Checkpoint checkpointDTO = new Checkpoint();
        checkpointDTO.setFacility("facility1");
        checkpointDTO.setDriver("driver1");
        checkpointDTO.setDayOfMonth(31); // Fecha inválida

        // Realiza un check-in primero
        Checkpoint checkinDTO = new Checkpoint();
        checkinDTO.setFacility("facility1");
        checkinDTO.setDriver("driver1");
        checkinDTO.setDayOfMonth(15);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        });
        assertEquals("Invalid date", exception.getMessage());
    }
}

