package org.smartinrub.welcomeservice.controllers;

import org.smartinrub.welcomeservice.models.User;
import org.smartinrub.welcomeservice.repositories.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.Optional;

import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

@RestController("/login")
@CrossOrigin
public class LoginController {

    private static final String AUTH_URL =  "http://localhost:8090/add";

    private RestTemplate restTemplate = new RestTemplate();

    private final UserRepository userRepository;

    public LoginController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        Optional<User> dbUser = userRepository.findById(email);

        if (!dbUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }

        if (!password.equals(dbUser.get().getPassword())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Wrong password!");
        }

        return ResponseEntity.ok()
                .body(restTemplate.postForEntity(AUTH_URL, email + "-" + dbUser.get().getProfession(),
                String.class).getHeaders().getFirst(AUTHORIZATION_HEADER_NAME));
    }
}
