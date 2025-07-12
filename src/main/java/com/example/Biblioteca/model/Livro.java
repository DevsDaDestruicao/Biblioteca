/*
Cada classe representa uma tabela no banco de dados.

A anotação @Entity indica que é uma entidade persistente.

@Id e @GeneratedValue definem o ID autogerado.

@ManyToOne define o relacionamento com outras tabelas (neste caso, Livro com Autor e Editora).

O Lombok (@Data, @NoArgsConstructor, etc.) evita repetição de código.
 */

package com.example.Biblioteca.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Marca essa classe como em entidade JPA (tabela no banco)
@Entity
// Lombok cria getters, setters, construtores e toString automaticamente
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    // Define a chave primária da entidade
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto_Incremento
    private Long id;

    // Título do livro
    private String titulo;

    // Ano da publicação
    private int ano;

    // Se esta disponível ou não
    private boolean disponivel;

    // Relacionamento muitos-para-um: muitos livros podem ter a mesma editora
    @ManyToOne
    @JoinColumn(name = "editora_id")    // Nome da coluna no banco que referencia a editora
    private Editora editora;

    // Muitos livros podem ter o mesmo autor
    @ManyToOne
    @JoinColumn(name = "autor_id")  // Nome da coluna no banco que referencia o autor
    private Autor autor;
}