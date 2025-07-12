package com.example.Biblioteca.controller;

//Importa as classes necessárias para o funcionamento do controller
import com.example.Biblioteca.dto.LivroDTO;
import com.example.Biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Indica que essa classe é um controlador REST da API
@RestController

//Define o caminho base dos endpoints relacionados aos livros
@RequestMapping("/livros")
public class LivroController {

    //Injeta automaticamente a dependência da chamada de serviço
    @Autowired
    private LivroService livroService;

    //GET /livros
    //Lista todos os livros cadastrados
    @GetMapping
    public ResponseEntity<List<LivroDTO>>listarLivros(){
        //Chama o serviço para buscar todos os livros e retornar um status 200(OK)
        return ResponseEntity.ok(livroService.listarTodos());
    }

    //GET /livros/{id}
    //Lista todos os livros cadastrados por id
    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO>buscarLivroPorId(@PathVariable Long id){
        //Usa o service para buscar o livro pelo ID
        //Se não existir, o service lança uma exceção com status 404
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    //POST /livros
    //Cadastra um novo livro com base nas informações enviadas pelo cliente
    @PostMapping
    public ResponseEntity<LivroDTO>cadastrarLivro(@RequestBody LivroDTO livroDTO){
        //Chama o service para salvar o livro
        //Retorna o livro salvo com status 201(CREATED)
        LivroDTO salvo=livroService.salvar(livroDTO);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    //PUT /livros/{id}
    //Atualiza todos os livros cadastrados por id
    @PutMapping("/{id}")
    public ResponseEntity<LivroDTO>atualizarLivro(@PathVariable Long id, @RequestBody LivroDTO livroDTO){
        LivroDTO atualizado=livroService.atualizar(id,livroDTO);
        return ResponseEntity.ok(atualizado);
    }

    //DELETE /livros/{id}
    //Exclui um livro cadastrado por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirLivro(@PathVariable Long id){
        livroService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}