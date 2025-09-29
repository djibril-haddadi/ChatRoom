package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SalonController {

    @GetMapping("/addSalon")
    public String addSalon(@RequestParam(value = "name",
            defaultValue = "World") String name)
    {
        return "Hello, " + name + "!";
    }
}
