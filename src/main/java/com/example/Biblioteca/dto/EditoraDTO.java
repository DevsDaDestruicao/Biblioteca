/*
    Os DTOs são usados para transmitir dados de forma segura
    entre a API e o cliente, sem expor diretamente as entidades do banco de dados.
    Eles também permitem personalizar o que será enviado ou recebido.
 */

package com.example.Biblioteca.dto;

import lombok.Data;

@Data
public class EditoraDTO {
    private Long id;
    private String nome;

    public EditoraDTO(){}

    // Construtor que converte uma entidade Editora em um DTO
    public EditoraDTO(com.example.Biblioteca.model.Editora editora){
        this.id= editora.getId();
        this.nome= editora.getNome();
    }
}
