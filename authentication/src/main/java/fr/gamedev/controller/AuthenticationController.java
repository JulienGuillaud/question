package fr.gamedev.controller;

import fr.gamedev.data.User;
import fr.gamedev.dto.ConnectDTO;
import fr.gamedev.dto.UpsertDTO;
import fr.gamedev.service.AuthenticationService;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.net.URI;

/**
 * @author Swan
 *
 */
@RestController
public class AuthenticationController {

    /**
     * . linkBuilder
     */
    private final EntityLinks linkBuilder;

    /**
     * . authenticationService
     */
    private final AuthenticationService authenticationService;

    public AuthenticationController(EntityLinks linkBuilder, AuthenticationService authenticationService) {
        this.linkBuilder = linkBuilder;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody final UpsertDTO registerDTO) {
        User registeredUser = authenticationService.register(registerDTO.lastName(), registerDTO.password(), registerDTO.skills());

        URI uri = linkBuilder.linkFor(User.class, registeredUser).toUri();

        return ResponseEntity.created(uri).body(registeredUser);
    }

    @GetMapping("/connect")
    public ResponseEntity<User> connect(@RequestBody final ConnectDTO connectDTO) {
        User user = authenticationService.connect(connectDTO.lastName(), connectDTO.password());

        return ResponseEntity.accepted().body(user);
    }

    @PostMapping("{userId}/skills")
    public ResponseEntity<User> addSkills(@PathParam("userId")long userId, @RequestBody final UpsertDTO upsertDTO) {
        User newUser = authenticationService.addSkills(userId, upsertDTO.skills());

        URI uri = linkBuilder.linkFor(User.class, newUser).toUri();

        return ResponseEntity.created(uri).body(newUser);
    }

    @DeleteMapping("{userId}/skills")
    public ResponseEntity<User> removeSkills(@PathParam("userId")long userId, @RequestBody final UpsertDTO upsertDTO) {
        User newUser = authenticationService.removeSkills(userId, upsertDTO.skills());

        URI uri = linkBuilder.linkFor(User.class, newUser).toUri();

        return ResponseEntity.created(uri).body(newUser);
    }


}
