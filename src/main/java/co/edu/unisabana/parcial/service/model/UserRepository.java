package co.edu.unisabana.parcial.service.model;


import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    // Puedes agregar consultas personalizadas si las necesitas
}
