package com.example.Biblioteca.controller;

import com.example.Biblioteca.model.Editora;
import com.example.Biblioteca.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraRepository editoraRepository;

    @GetMapping
    public ResponseEntity<List<Editora>>listarEditoras(){
        return ResponseEntity.ok(editoraRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Editora>cadastrarEditora(@RequestBody Editora editora){
        Editora salva=editoraRepository.save(editora);
        return new ResponseEntity<>(salva, HttpStatus.CREATED);
    }
}