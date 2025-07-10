package com.example.Biblioteca.controller;

import com.example.Biblioteca.model.Autor;
import com.example.Biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorRepository autorRepository;

    @GetMapping
    public ResponseEntity<List<Autor>>listarAutores(){
        return ResponseEntity.ok(autorRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Autor>cadastrarAutor(@RequestBody Autor autor){
        Autor salvo=autorRepository.save(autor);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
}