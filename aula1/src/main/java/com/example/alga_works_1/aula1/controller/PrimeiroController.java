package com.example.alga_works_1.aula1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.alga_works_1.aula1.core.contracts.BeanLifeCycle;
import com.example.alga_works_1.aula1.core.contracts.EventObserver;
import com.example.alga_works_1.aula1.core.helper.IfNull;
import com.example.alga_works_1.aula1.core.helper.Print;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

@Controller
public class PrimeiroController implements BeanLifeCycle, EventObserver<Integer> {
    @GetMapping("/hello")
    @ResponseBody
    public String hello() {
        Print.ln("Teste");
        return "Hello";
    }

    @Override
    @PreDestroy
    public void onDestroy() {
        Print.ln("Destroy");
    }

    @Override
    @PostConstruct
    public void onInit() {
        Print.ln("Init");
    }

    @Override
    public void onEvent(Integer event) {
        IfNull.execute(event, () -> {
            System.out.println("Executando operação nula...");
        }, () -> {
            System.out.println("Executando operação não nula...");
        });
    }
}