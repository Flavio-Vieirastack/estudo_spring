package com.example.alga_works_1.aula1.models;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table
public class Restaurante {
    @Id
    private Long id;
    private String name;
    @Column(name = "taxa_frete")
    private BigDecimal taxaFrete;
}