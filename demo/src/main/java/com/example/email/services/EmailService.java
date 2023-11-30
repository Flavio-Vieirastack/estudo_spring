package com.example.email.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.email.repositories.EmailRepository;

@Service
public class EmailService {
    @Autowired
    EmailRepository emailRepository;
}