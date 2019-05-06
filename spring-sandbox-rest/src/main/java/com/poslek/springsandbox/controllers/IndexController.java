package com.poslek.springsandbox.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @GetMapping({"", "/"})
    public String home() {
        return "Spring Sandbox REST API. Access all superheroes at /superheroes.";
    }

}
