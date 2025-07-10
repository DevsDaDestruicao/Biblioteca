package com.example.Biblioteca.controller;

import com.example.Biblioteca.dto.EditoraDTO;
import com.example.Biblioteca.service.EditoraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/editoras")
public class EditoraController {

    @Autowired
    private EditoraService editoraService;

    @GetMapping
    public ResponseEntity<List<EditoraDTO>>listarEditoras(){
        return ResponseEntity.ok(editoraService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<EditoraDTO>cadastrarEditora(@RequestBody EditoraDTO editoraDTO){
        EditoraDTO salva=editoraService.salvar(editoraDTO);
        return new ResponseEntity<>(salva, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EditoraDTO>atualizarEditora(@PathVariable Long id, @RequestBody EditoraDTO editoraDTO){
        EditoraDTO atualizada=editoraService.atualizar(id, editoraDTO);
        return ResponseEntity.ok(atualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void>excluirEditora(@PathVariable Long id){
        editoraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}