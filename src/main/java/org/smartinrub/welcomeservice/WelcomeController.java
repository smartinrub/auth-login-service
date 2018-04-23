package org.smartinrub.welcomeservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController("/welcome")
public class WelcomeController {

    @GetMapping
    public String welcome(HttpServletRequest request) {
        return "Welcome " + request.getAttribute("username");
    }

}

