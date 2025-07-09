package com.example.Biblioteca.dto;

import lombok.Data;

@Data
public class AutorDTO {
    private Long id;
    private String nome;

    public AutorDTO(){}

    public AutorDTO(com.example.Biblioteca.model.Autor autor){
        this.id= autor.getId();
        this.nome= autor.getNome();
    }
}
