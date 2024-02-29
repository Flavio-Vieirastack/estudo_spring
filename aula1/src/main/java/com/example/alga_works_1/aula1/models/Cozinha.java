package com.example.alga_works_1.aula1.models;

import io.micrometer.common.lang.NonNull;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.val;

@Entity
@Data
public class Cozinha {
    @Id
    @val
    @NonNull
    private Long id;
    @val
    @NonNull
    private String name;
}