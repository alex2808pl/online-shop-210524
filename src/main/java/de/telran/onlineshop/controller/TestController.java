package de.telran.onlineshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/test")  //localhost:8081/test
public class TestController {

    @GetMapping
    String testGet() {
        return "Мама мыла раму!";
    }

    @GetMapping(value = "/get_hello")
    String helloGet() {
        return "Hello Spring";
    }
}
