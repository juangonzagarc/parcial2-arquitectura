package co.edu.unisabana.parcial.service.model;

import org.springframework.stereotype.Service;

import java.util.Optional;


import co.edu.unisabana.parcial.repository.sql.jpa.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
