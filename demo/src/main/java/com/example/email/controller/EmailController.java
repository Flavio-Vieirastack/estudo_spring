package com.example.email.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.email.dtos.EmailDTO;
import com.example.email.models.EmailModel;
import com.example.email.services.EmailService;
import jakarta.validation.Valid;

@RestController
public class EmailController {
    @Autowired
    EmailService emailService;

    @PostMapping("/sendEmail")
    public ResponseEntity<EmailModel> sendEmail(@RequestBody @Valid EmailDTO emailDTO) {
        //@Valid serve para dar efeito as validações que estão dentro do dto
        final EmailModel emailModel = new EmailModel();
        BeanUtils.copyProperties(emailDTO, emailModel); //Transforma um dto em um model
        emailService.sendEmail(emailModel);
        return new ResponseEntity<>(emailModel, HttpStatus.CREATED);
    }
}