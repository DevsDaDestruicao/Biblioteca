package com.example.Biblioteca.service;

import com.example.Biblioteca.dto.LivroDTO;
import com.example.Biblioteca.model.Autor;
import com.example.Biblioteca.model.Editora;
import com.example.Biblioteca.model.Livro;
import com.example.Biblioteca.repository.AutorRepository;
import com.example.Biblioteca.repository.EditoraRepository;
import com.example.Biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private AutorRepository autorRepository;

    @Autowired
    private EditoraRepository editoraRepository;

    public List<LivroDTO>listarTodos(){
        return livroRepository.findAll().stream()
                .map(LivroDTO::new)
                .collect(Collectors.toList());
    }

    public LivroDTO buscarPorId(Long id){
        Livro livro=livroRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Livro não encontrado"));
        return new LivroDTO(livro);
    }

    public LivroDTO salvar(LivroDTO dto){
        Autor autor=autorRepository.findById(dto.getAutorId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Autor não encontrado"));

        Editora editora=editoraRepository.findById(dto.getEditoraId())
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Editora não encontrada"));

        Livro livro=new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAno(dto.getAno());
        livro.setDisponivel(dto.isDisponivel());
        livro.setAutor(autor);
        livro.setEditora(editora);

        Livro salvo=livroRepository.save(livro);
        return new LivroDTO(salvo);
    }
}