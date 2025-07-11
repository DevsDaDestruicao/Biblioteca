package com.example.Biblioteca.service;

import com.example.Biblioteca.dto.AutorDTO;
import com.example.Biblioteca.model.Autor;
import com.example.Biblioteca.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AutorService {

    @Autowired
    private AutorRepository autorRepository;

    public List<AutorDTO> listarTodos(){
        return autorRepository.findAll()
                .stream()
                .map(AutorDTO::new)
                .collect(Collectors.toList());
    }

    public AutorDTO buscarPorId(Long id){
        Autor autor=autorRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Autor não encontrado com o ID: "+id));
        return new AutorDTO(autor);
    }

    public AutorDTO salvar(AutorDTO dto){
        Autor autor = new Autor();
        autor.setNome(dto.getNome());
        Autor salvo = autorRepository.save(autor);
        return new AutorDTO(salvo);
    }

    public Autor buscarEntidadePorId(Long id){
        return autorRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Autor não encontrado"));
    }

    public AutorDTO atualizar(Long id, AutorDTO dto){
        Autor autorExistente=autorRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: "+id));

        autorExistente.setNome(dto.getNome());

        Autor atualizado=autorRepository.save(autorExistente);
        return new AutorDTO(atualizado);
    }

    public void excluir(Long id){
        if(!autorRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"Autor não encontrado com o ID: "+id);
        }

        autorRepository.deleteById(id);
    }
}