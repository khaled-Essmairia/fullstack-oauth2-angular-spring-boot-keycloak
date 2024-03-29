package com.backendkeycloak.backenkeycloak.controller;

import java.text.MessageFormat;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.backendkeycloak.backenkeycloak.jwt.CustomJwt;

@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*", methods = { RequestMethod.GET })
public class HelloController {

    @GetMapping("/hello")
    // @PreAuthorize("hasAuthority('role1')")
    public Message hello() {
        var jwt = (CustomJwt) SecurityContextHolder.getContext().getAuthentication();
        var message = MessageFormat
                .format("Hello fullstack master {0} {1}, how is it going today?",
                        jwt.getFirstname(), jwt.getLastname());
        return new Message(message);
    }

    record Message(String message) {
    }
}
