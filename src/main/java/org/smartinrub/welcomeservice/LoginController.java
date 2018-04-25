package org.smartinrub.welcomeservice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static org.apache.tomcat.websocket.Constants.AUTHORIZATION_HEADER_NAME;

@RestController("/login")
@CrossOrigin
public class LoginController {

    private static final String EMAIL = "a@a.com";
    private static final String PASSWORD = "a";
    private static final String USERNAME = "Sergio";
    private static final String AUTH_URL =  "http://localhost:8090/add";

    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public ResponseEntity<String> login(@RequestBody @Valid User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        if (!EMAIL.equals(email) || !PASSWORD.equals(password)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect Email and/or password!");
        }

        return ResponseEntity.ok().body(restTemplate.postForEntity(AUTH_URL, USERNAME,
                String.class).getHeaders().getFirst(AUTHORIZATION_HEADER_NAME));
    }
}
