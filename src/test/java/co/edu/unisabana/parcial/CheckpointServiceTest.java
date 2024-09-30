package co.edu.unisabana.parcial;
import co.edu.unisabana.parcial.repository.sql.entity.Checkpoint;
import co.edu.unisabana.parcial.service.CheckpointService;
import co.edu.unisabana.parcial.service.port.CheckpointPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import co.edu.unisabana.parcial.service.model.Checkin;
import co.edu.unisabana.parcial.service.model.Checkout;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CheckpointServiceTest {

    @Mock
    private CheckpointPort checkpointPort; // Mock de CheckpointPort

    @InjectMocks
    private CheckpointService checkpointService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCheckinValid() {
        // Crear un objeto Checkpoint
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setFacility("facility1");
        checkpoint.setDriver("driver1");
        checkpoint.setDayOfMonth(15);

        // No se espera excepción, simplemente verificar el comportamiento
        verify(checkpointPort).saveCheckin(any(Checkin.class)); // Verifica que se llame a saveCheckin
    }

    @Test
    void testCheckinInvalidDate() {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setFacility("facility1");
        checkpoint.setDriver("driver1");
        checkpoint.setDayOfMonth(31); // Fecha inválida

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        });

        assertEquals("Invalid date", exception.getMessage());
    }

    @Test
    void testCheckoutValid() {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setFacility("facility1");
        checkpoint.setDriver("driver1");
        checkpoint.setDayOfMonth(15);

        Checkin lastCheckin = new Checkin("facility1", "driver1", 10);
        when(checkpointPort.findLastCheckin("driver1", "facility1")).thenReturn(lastCheckin);

        verify(checkpointPort).saveCheckout(any(Checkout.class)); // Verifica que se llame a saveCheckout
    }

    @Test
    void testCheckoutNoLastCheckin() {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setFacility("facility1");
        checkpoint.setDriver("driver1");
        checkpoint.setDayOfMonth(15);

        when(checkpointPort.findLastCheckin("driver1", "facility1")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        });

        assertEquals("don't exist previously check in", exception.getMessage());
    }

    @Test
    void testCheckoutInvalidDate() {
        Checkpoint checkpoint = new Checkpoint();
        checkpoint.setFacility("facility1");
        checkpoint.setDriver("driver1");
        checkpoint.setDayOfMonth(31); // Fecha inválida

        Checkin lastCheckin = new Checkin("facility1", "driver1", 10);
        when(checkpointPort.findLastCheckin("driver1", "facility1")).thenReturn(lastCheckin);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
        });

        assertEquals("Invalid date", exception.getMessage());
    }
}
