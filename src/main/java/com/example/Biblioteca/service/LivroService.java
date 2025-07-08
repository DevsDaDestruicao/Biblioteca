package com.example.Biblioteca.service;

import com.example.Biblioteca.dto.LivroDTO;
import com.example.Biblioteca.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class LivroService {
}
@Autowired
private LivroRepository livroRepository;

private LivroDTO toDTO(Livro livro) {
    LivroDTO dto = new LivroDTO();
    dto.setId(livro.getTd());;
    dto.setTitulo(livro.getTitulo());
    dto.setAutor(livro.getAutor());
    dto.setAno(livro.getAno());
    return dto;
}

private Livro toEntity(LivroDTO dto) {
    Livro livro= new livro();
    livro .setId(dto.getId());
    livro.setTitulo(dto.getTitulo());
    livro.setAutor(dto.getAutor());
    livro.setAno(dto.getAno());
    return livro;
}

public list<