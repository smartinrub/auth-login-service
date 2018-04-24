package org.smartinrub.welcomeservice;

import org.apache.tomcat.websocket.Constants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;

import static org.smartinrub.welcomeservice.SecurityConstants.HEADER_STRING;

@CrossOrigin(origins = "*")
@RestController("/login")
public class LoginController {

    private static final String EMAIL = "email@domain.com";
    private static final String PASSWORD = "Password1";
    private static final String USERNAME = "Sergio";
    private static final String AUTH_URL =  "http://localhost:8090/add";

    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    @ResponseBody
    public ResponseEntity<String> login(@RequestBody @Valid User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        if (!EMAIL.equals(email) || !PASSWORD.equals(password)) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect Email and/or password!");
        }

        HttpHeaders headers = new HttpHeaders();
        headers.set(Constants.AUTHORIZATION_HEADER_NAME, restTemplate.postForEntity(AUTH_URL, USERNAME,
                String.class).getHeaders().getFirst(HEADER_STRING));
        System.out.println(ResponseEntity.ok().headers(headers).body("Successful Login"));

        return ResponseEntity.ok().headers(headers).body("Successful Login");
    }
}
