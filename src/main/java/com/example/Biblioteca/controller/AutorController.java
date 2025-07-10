package com.example.Biblioteca.controller;

import com.example.Biblioteca.dto.AutorDTO;
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
    public ResponseEntity<List<AutorDTO>>listarAutores(){
        return ResponseEntity.ok(autorService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<AutorDTO>cadastrarAutor(@RequestBody AutorDTO autorDTO){
        AutorDTO salvo=autorService.salvar(autorDTO);
        return new ResponseEntity<>(salvo, HttpStatus.CREATED);
    }
}