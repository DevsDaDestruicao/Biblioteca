/*
    Os DTOs são usados para transmitir dados de forma segura
    entre a API e o cliente, sem expor diretamente as entidades do banco de dados.
    Eles também permitem personalizar o que será enviado ou recebido.
 */

package com.example.Biblioteca.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LivroDTO {
    private Long id;
    @NotBlank(message = "O título é obrigatório")
    private String titulo;
    @NotNull(message = "O ano é obrigatório")
    @Min(value = 1000, message = "O ano deve ser maior que 1000")
    private int ano;

    private boolean disponivel;

    // Esses IDs representam os relacionamentos com autor e editora
    private Long autorId;
    private Long editoraId;

    public LivroDTO(){}

    // Construtor que transforma a entidade Livro em DTO
    public LivroDTO(com.example.Biblioteca.model.Livro livro){
        this.id= livro.getId();
        this.titulo= livro.getTitulo();
        this.ano= livro.getAno();
        this.disponivel= livro.isDisponivel();

        // Pega o ID do autor e da editora associados ao livro
        this.autorId=livro.getAutor().getId();
        this.editoraId=livro.getEditora().getId();
    }
}