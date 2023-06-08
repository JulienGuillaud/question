package fr.gamedev.service;

import fr.gamedev.data.User;
import fr.gamedev.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Set;


public class AuthenticationService {

    private final UserRepository userRepository;

    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User removeSkills(long userId, Set<Long> skills) {
        User newUser = this.userRepository.findById(userId)
                .map(user -> user.removeSkills(skills))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cannot find user with id : " + userId));

        this.userRepository.save(newUser);

        return newUser;
    }

    public User addSkills(long userId, Set<Long> skills) {
        User newUser = this.userRepository.findById(userId)
                .map(user -> user.addSkills(skills))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "Cannot find user with id : " + userId));

        this.userRepository.save(newUser);

        return newUser;
    }

    public User connect(String lastName, String password) {
        return this.userRepository.findFirstByLastName(lastName)
                .filter(user -> user.getPassword().equals(password))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, "login failed"));
    }

    public User register(String lastName, String password, Set<Long> skillIds) {
        User user = new User()
                .setLastName(lastName)
                .setPassword(password)
                .addSkills(skillIds);

        this.userRepository.save(user);

        return user;
    }
}
