package com.example.Biblioteca.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Autor {
    @Id
    @GeneratedValue

    private Long id;
    private String nome;
}
