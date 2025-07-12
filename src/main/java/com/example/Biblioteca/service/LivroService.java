// Define o pacote da classe
package com.example.Biblioteca.service;

// Importa o DTO usado para entrada/saída de dados da API
import com.example.Biblioteca.dto.LivroDTO;

// Importa as entidades relacionadas à tabela no banco
import com.example.Biblioteca.model.Autor;
import com.example.Biblioteca.model.Editora;
import com.example.Biblioteca.model.Livro;

// Importa os repositórios usados para persistência no banco
import com.example.Biblioteca.repository.AutorRepository;
import com.example.Biblioteca.repository.EditoraRepository;
import com.example.Biblioteca.repository.LivroRepository;

// Importações para tratamento de exceções e status HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

// Define esta classe como um componente de serviço do Spring
@Service
public class LivroService {

    // Injeta o repositório do livro
    @Autowired
    private LivroRepository livroRepository;

    // Injeta o repositório do autor (necessário para relacionar ao livro)
    @Autowired
    private AutorRepository autorRepository;

    // Injeta o repositório da editora (também relacionada ao livro)
    @Autowired
    private EditoraRepository editoraRepository;

    // Retorna todos os livros cadastrados, convertendo para DTOs
    public List<LivroDTO> listarTodos() {
        return livroRepository.findAll()
                .stream()
                .map(LivroDTO::new) // Converte cada entidade Livro em LivroDTO
                .collect(Collectors.toList());
    }

    // Retorna um único livro pelo ID, já convertido em DTO
    public LivroDTO buscarPorId(Long id) {
        Livro livro = buscarLivroOuErro(id); // Busca ou lança erro 404
        return new LivroDTO(livro);
    }

    // Salva um novo livro no banco com base no DTO recebido
    public LivroDTO salvar(LivroDTO dto) {
        // Busca autor e editora pelo ID passado no DTO
        Autor autor = buscarAutorOuErro(dto.getAutorId());
        Editora editora = buscarEditoraOuErro(dto.getEditoraId());

        // Cria o livro e preenche os dados
        Livro livro = new Livro();
        livro.setTitulo(dto.getTitulo());
        livro.setAno(dto.getAno());
        livro.setDisponivel(dto.isDisponivel());
        livro.setAutor(autor);       // Define o autor vinculado
        livro.setEditora(editora);   // Define a editora vinculada

        // Salva no banco e retorna como DTO
        Livro salvo = livroRepository.save(livro);
        return new LivroDTO(salvo);
    }

    // Atualiza os dados de um livro existente
    public LivroDTO atualizar(Long id, LivroDTO dto) {
        Livro livroExistente = buscarLivroOuErro(id); // Verifica existência
        Autor autor = buscarAutorOuErro(dto.getAutorId());
        Editora editora = buscarEditoraOuErro(dto.getEditoraId());

        // Atualiza os campos com os dados do DTO
        livroExistente.setTitulo(dto.getTitulo());
        livroExistente.setAno(dto.getAno());
        livroExistente.setDisponivel(dto.isDisponivel());
        livroExistente.setAutor(autor);
        livroExistente.setEditora(editora);

        // Salva a atualização e retorna como DTO
        Livro atualizado = livroRepository.save(livroExistente);
        return new LivroDTO(atualizado);
    }

    // Exclui um livro pelo ID
    public void excluir(Long id) {
        if (!livroRepository.existsById(id)) {
            // Se não existir, lança erro 404
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Livro não encontrado com o ID: " + id);
        }
        livroRepository.deleteById(id); // Caso exista, remove do banco
    }

    // 🔽 Métodos auxiliares privados reutilizáveis 🔽

    // Busca um livro ou lança erro 404
    private Livro buscarLivroOuErro(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Livro não encontrado com o ID: " + id));
    }

    // Busca um autor ou lança erro 404
    private Autor buscarAutorOuErro(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: " + id));
    }

    // Busca uma editora ou lança erro 404
    private Editora buscarEditoraOuErro(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Editora não encontrada com o ID: " + id));
    }
}
