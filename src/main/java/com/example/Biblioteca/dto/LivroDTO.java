package com.example.Biblioteca.dto;

import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    private String titulo;
    private int ano;
    private boolean disponivel;
    private Long autorId;
    private Long editoraId;

    public LivroDTO(){}

    public LivroDTO(com.example.Biblioteca.model.Livro livro){
        this.id= livro.getId();
        this.titulo= livro.getTitulo();
        this.ano= livro.getAno();
        this.disponivel= livro.isDisponivel();
        this.autorId=livro.getAutor().getId();
        this.editoraId=livro.getEditora().getId();
    }
}