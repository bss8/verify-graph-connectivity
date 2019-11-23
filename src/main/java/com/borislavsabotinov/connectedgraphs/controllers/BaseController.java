package com.borislavsabotinov.connectedgraphs.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BaseController {
    @Value("${TARGET:World}")
    String message;

    @GetMapping("/hello")
    String hello() {
        return "Graphs says Hello " + message + "!";
    }
}
