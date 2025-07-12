// Define o pacote da classe
package com.example.Biblioteca.service;

// Importa o DTO usado na troca de dados com o cliente
import com.example.Biblioteca.dto.EditoraDTO;
// Importa a entidade Editora, que representa a tabela no banco
import com.example.Biblioteca.model.Editora;
// Importa o repositório que faz operações com a base de dados
import com.example.Biblioteca.repository.EditoraRepository;

// Importações para tratamento de exceções e status HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

// Marca esta classe como um componente de serviço do Spring
@Service
public class EditoraService {

    // Injeta o repositório de editora
    @Autowired
    private EditoraRepository editoraRepository;

    // Retorna todas as editoras do banco como uma lista de DTOs
    public List<EditoraDTO> listarTodos() {
        return editoraRepository.findAll()
                .stream()
                .map(EditoraDTO::new) // Converte cada entidade para um DTO
                .collect(Collectors.toList());
    }

    // Retorna uma editora específica pelo ID
    public EditoraDTO buscarPorId(Long id) {
        Editora editora = buscarEntidadeOuErro(id); // Busca e trata erro 404 se não existir
        return new EditoraDTO(editora);
    }

    // Salva uma nova editora com os dados recebidos via DTO
    public EditoraDTO salvar(EditoraDTO dto) {
        Editora editora = new Editora(); // Cria nova instância
        editora.setNome(dto.getNome()); // Define nome da editora
        Editora salva = editoraRepository.save(editora); // Salva no banco
        return new EditoraDTO(salva); // Retorna o DTO criado
    }

    // Método privado reutilizável para buscar editora ou lançar erro 404
    public Editora buscarEntidadeOuErro(Long id) {
        return editoraRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Editora não encontrada com o ID: " + id));
    }

    // Atualiza os dados de uma editora já existente
    public EditoraDTO atualizar(Long id, EditoraDTO dto) {
        Editora editoraExistente = buscarEntidadeOuErro(id); // Busca existente
        editoraExistente.setNome(dto.getNome()); // Atualiza nome
        Editora atualizada = editoraRepository.save(editoraExistente); // Salva no banco
        return new EditoraDTO(atualizada); // Retorna DTO atualizado
    }

    // Exclui uma editora por ID
    public void excluir(Long id) {
        if (!editoraRepository.existsById(id)) {
            // Se não existir, lança erro 404
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Editora não encontrada com o ID: " + id);
        }
        editoraRepository.deleteById(id); // Remove do banco
    }
}
