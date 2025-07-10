package com.example.Biblioteca.controller;

import com.example.Biblioteca.model.Livro;
import com.example.Biblioteca.repository.AutorRepository;
import com.example.Biblioteca.repository.EditoraRepository;
import com.example.Biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping
    public ResponseEntity<List<Livro>>listarLivros(){
        return ResponseEntity.ok(livroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro>buscarLivroPorId(@PathVariable Long id){
        return livroRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Livro não encontrado"));
    }

    @PostMapping
    public ResponseEntity<Livro>cadastrarLivro(@RequestBody Livro livro){
        Long idAutor=livro.getAutor().getId();
        Long idEditora=livro.getEditora().getId();

        if (!autorRepository.existsById(idAutor)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado");
        }

        if (!editoraRepository.existsById(idEditora)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Editora não encontrada");
        }

        Livro livroSalvo=livroRepository.save(livro);
        return new ResponseEntity<>(livroSalvo, HttpStatus.CREATED);
    }
}
