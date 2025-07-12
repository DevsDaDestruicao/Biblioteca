package com.example.Biblioteca.controller;

//Importações das classes necessárias
import com.example.Biblioteca.dto.AutorDTO;
import com.example.Biblioteca.model.Autor;
import com.example.Biblioteca.service.AutorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Indica que essa classe é um controlador REST que responderá requisições HTTP
@RestController

//Define o caminho base para todos os endpoints dessa classe
@RequestMapping("/autores")
public class AutorController {

    //Injeta automaticamente a dependência do service que contém a lógica de negócio
    @Autowired
    private AutorService autorService;

    //GET /autores
    //Esse metodo retorna uma lista de autores cadastrados
    @GetMapping
    public ResponseEntity<List<AutorDTO>> listarAutores() {
        //Retorna a lista de autores com status 200(OK)
        return ResponseEntity.ok(autorService.listarTodos());
    }

    //GET /autores/{id}
    //Esse metodo retorna um autor cadastrado pelo id
    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO> buscarAutorPorId(@PathVariable Long id){
        AutorDTO autorDTO=autorService.buscarPorId(id);
        return ResponseEntity.ok(autorDTO);
    }

    //POST /autores
    //Esse metodo cadastra um novo autor com base nos dados enviados no corpo da requisição
    @PostMapping
    public ResponseEntity<AutorDTO> cadastrarAutor(@Valid @RequestBody AutorDTO autorDTO){
        //Chama o serviço para salvar e retorna a resposta com status 201(CREATED)
        AutorDTO salvo=autorService.salvar(autorDTO);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    //PUT /autores/{id}
    //Esse metodo atualiza um autor com base no id
    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO> atualizarAutor(@PathVariable Long id, @RequestBody AutorDTO autorDTO){
        AutorDTO atualizado=autorService.atualizar(id, autorDTO);
        return ResponseEntity.ok(atualizado);
    }

    //DELETE /autores/{id}
    //Esse metodo exclui um autor com base no id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirAutor(@PathVariable Long id){
        autorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}