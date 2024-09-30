package co.edu.unisabana.parcial.repository.sql.jpa;



import co.edu.unisabana.parcial.service.model.User;


import org.springframework.data.jpa.repository.JpaRepository;
import co.edu.unisabana.parcial.service.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    // Hereda los métodos de JpaRepository y de la interfaz UserRepositoryInterface.
}
