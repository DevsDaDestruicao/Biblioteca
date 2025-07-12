/*
    Os DTOs são usados para transmitir dados de forma segura
    entre a API e o cliente, sem expor diretamente as entidades do banco de dados.
    Eles também permitem personalizar o que será enviado ou recebido.
 */

package com.example.Biblioteca.dto;

import lombok.Data;

@Data
public class AutorDTO {
    private Long id;
    private String nome;

    public AutorDTO(){}

    // Construtor que recebe um Autor (entidade) e cria DTO com seus dados
    public AutorDTO(com.example.Biblioteca.model.Autor autor){
        this.id= autor.getId();
        this.nome= autor.getNome();
    }
}
