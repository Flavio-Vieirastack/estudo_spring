package com.example.email.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.email.models.EmailModel;

public interface EmailRepository  extends JpaRepository<EmailModel, Long>{
    
}