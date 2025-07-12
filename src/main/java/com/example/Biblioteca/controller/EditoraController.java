package com.example.Biblioteca.controller;

//Importa os objetos necessários
import com.example.Biblioteca.dto.EditoraDTO;
import com.example.Biblioteca.service.EditoraService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//Indica que essa classe é um controlador REST
@RestController

//Define o endpoint base para os metodos dessa classe
@RequestMapping("/editoras")
public class EditoraController {

    //Injeta a dependência da classe de serviço EditoraService
    @Autowired
    private EditoraService editoraService;

    //GET /editoras
    //Lista todas as editoras cadastradas no sistema
    @GetMapping
    public ResponseEntity<List<EditoraDTO>> listarEditoras(){
        //Retorna uma lista de editoras com status HTTP 200(OK)
        return ResponseEntity.ok(editoraService.listarTodos());
    }

    //GET /editoras/{id}
    //Busca uma editor cadastrada no sistema pela id
    @GetMapping("/{id}")
    public ResponseEntity<EditoraDTO> buscarEditoraPorId(@PathVariable Long id){
        EditoraDTO editoraDTO=editoraService.buscarPorId(id);
        return ResponseEntity.ok(editoraDTO);
    }

    //POST /editoras
    //Cadastra uma nova editora no sistema
    @PostMapping
    public ResponseEntity<EditoraDTO> cadastrarEditora(@Valid @RequestBody EditoraDTO editoraDTO){
        //Chama o service para salvar e retorna a nova editora com status 201(CREATED)
        EditoraDTO salva=editoraService.salvar(editoraDTO);
        return new ResponseEntity<>(salva, HttpStatus.CREATED);
    }

    //PUT /editoras/{id}
    //Atualiza uma editora cadastrada no sistema pelo id
    @PutMapping("/{id}")
    public ResponseEntity<EditoraDTO> atualizarEditora(@PathVariable Long id, @RequestBody EditoraDTO editoraDTO){
        EditoraDTO atualizada=editoraService.atualizar(id, editoraDTO);
        return ResponseEntity.ok(atualizada);
    }

    //DELETE /editoras/{id}
    //Exclui uma editora cadastrada no sistema por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirEditora(@PathVariable Long id){
        editoraService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}