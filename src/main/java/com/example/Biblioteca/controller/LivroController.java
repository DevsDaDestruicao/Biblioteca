package com.example.Biblioteca.controller;

import com.example.Biblioteca.dto.LivroDTO;
import com.example.Biblioteca.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;

    @GetMapping
    public ResponseEntity<List<LivroDTO>>listarLivros(){
        return ResponseEntity.ok(livroService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LivroDTO>buscarLivroPorId(@PathVariable Long id){
        return ResponseEntity.ok(livroService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<LivroDTO>cadastrarLivro(@RequestBody LivroDTO livroDTO){
        LivroDTO salvo=livroService.salvar(livroDTO);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
}