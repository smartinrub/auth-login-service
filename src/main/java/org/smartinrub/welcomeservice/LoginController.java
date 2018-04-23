package org.smartinrub.welcomeservice;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;

import static org.smartinrub.welcomeservice.SecurityConstants.HEADER_STRING;

@RestController("/login")
public class LoginController {

    private static final String EMAIL = "email@domain.com";
    private static final String PASSWORD = "Password1";
    private static final String USERNAME = "Sergio";
    private static final String AUTH_URL =  "http://localhost:8090/add";

    private RestTemplate restTemplate = new RestTemplate();

    @PostMapping
    public String login(@RequestBody @Valid User user, HttpServletResponse response) throws UnsupportedEncodingException {

        String email = user.getEmail();
        String password = user.getPassword();

        if (!EMAIL.equals(email) || !PASSWORD.equals(password)) {
            return "Incorrect Email and/or password!";
        }

        return restTemplate.postForEntity(AUTH_URL, USERNAME,
                String.class).getHeaders().getFirst(HEADER_STRING);
    }
}
