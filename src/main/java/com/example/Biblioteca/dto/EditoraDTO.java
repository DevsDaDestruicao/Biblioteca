package com.example.Biblioteca.dto;

import lombok.Data;

@Data
public class EditoraDTO {
    private Long id;
    private String nome;

    public EditoraDTO(){}

    public EditoraDTO(com.example.Biblioteca.model.Editora editora){
        this.id= editora.getId();
        this.nome= editora.getNome();
    }
}