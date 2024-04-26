package edu.cs489.adssysem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/adsweb/api/v1")
public class HelloController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello";
    }
}
