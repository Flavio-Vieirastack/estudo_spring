package com.example.alga_works_1.aula1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PrimeiroController {
    @GetMapping("/ola")
    @ResponseBody
    public String hello() {
        return "Hello";
    }
}