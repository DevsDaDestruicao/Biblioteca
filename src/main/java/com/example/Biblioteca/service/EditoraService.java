package com.example.Biblioteca.service;

import com.example.Biblioteca.dto.EditoraDTO;
import com.example.Biblioteca.model.Editora;
import com.example.Biblioteca.repository.EditoraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EditoraService {

    @Autowired
    private EditoraRepository editoraRepository;

    public List<EditoraDTO>listarTodos(){
        return editoraRepository.findAll()
                .stream()
                .map(EditoraDTO::new)
                .collect(Collectors.toList());
    }

    public EditoraDTO salvar(EditoraDTO dto){
        Editora editora=new Editora();
        editora.setNome(dto.getNome());
        Editora salva=editoraRepository.save(editora);
        return new EditoraDTO(salva);
    }

    public Editora buscarEntidadePorId(Long id){
        return editoraRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Editora não encontrada"));
    }
}