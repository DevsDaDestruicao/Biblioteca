package com.example.Biblioteca.controller;

import com.example.Biblioteca.dto.AutorDTO;
import com.example.Biblioteca.model.Autor;
import com.example.Biblioteca.service.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping
    public ResponseEntity<List<AutorDTO>>listarAutores() {
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AutorDTO>buscarAutorPorId(@PathVariable Long id){
        AutorDTO autorDTO=autorService.buscarPorId(id);
        return ResponseEntity.ok(autorDTO);
    }

    @PostMapping
    public ResponseEntity<AutorDTO>cadastrarAutor(@RequestBody AutorDTO autorDTO){
        AutorDTO salvo=autorService.salvar(autorDTO);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AutorDTO>atualizarAutor(@PathVariable Long id, @RequestBody AutorDTO autorDTO){
        AutorDTO atualizado=autorService.atualizar(id, autorDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirAutor(@PathVariable Long id){
        autorService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}