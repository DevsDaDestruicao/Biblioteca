package com.example.Biblioteca.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

// Marca essa classe como em entidade JPA (tabela no banco)
@Entity
// Lombok cria getters, setters, construtores e toString automaticamente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Editora {

    // Define a chave primária da entidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_Incremento
    private Long id;

    // Define a coluna 'nome' da tabela editora
    private String nome;

    @OneToMany(mappedBy="editora")
    private List<Livro> livros;
}