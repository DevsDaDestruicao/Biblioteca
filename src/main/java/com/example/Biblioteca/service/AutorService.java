// Define o pacote onde a classe está localizada
package com.example.Biblioteca.service;

// Importa o DTO usado para transferir dados entre camadas
import com.example.Biblioteca.dto.AutorDTO;
// Importa a entidade Autor, mapeada para o banco de dados
import com.example.Biblioteca.model.Autor;
// Importa o repositório responsável pela persistência
import com.example.Biblioteca.repository.AutorRepository;

// Importa classes para tratamento de status e exceções HTTP
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

// Indica que essa classe é um componente de serviço (lógica de negócio)
@Service
public class AutorService {

    // Injeta automaticamente o repositório para acesso ao banco
    @Autowired
    private AutorRepository autorRepository;

    // Lista todos os autores cadastrados e converte para DTOs
    public List<AutorDTO> listarTodos() {
        return autorRepository.findAll()
                .stream()
                .map(AutorDTO::new) // Converte cada Autor para AutorDTO
                .collect(Collectors.toList());
    }

    // Busca um autor pelo ID e retorna um DTO
    public AutorDTO buscarPorId(Long id) {
        // Reutiliza método privado com tratamento de erro
        Autor autor = buscarEntidadeOuErro(id);
                return new AutorDTO(autor);
    }

    // Cadastra um novo autor com base nos dados do DTO
    public AutorDTO salvar(AutorDTO dto) {
        Autor autor = new Autor(); // Cria novo objeto Autor
        autor.setNome(dto.getNome()); // Define o nome
        Autor salvo = autorRepository.save(autor); // Salva no banco
        return new AutorDTO(salvo); // Retorna o DTO correspondente
    }

    // Método privado reutilizável que busca o autor ou lança erro 404
    public Autor buscarEntidadeOuErro(Long id) {
        return autorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: " + id));
    }

    // Atualiza os dados de um autor existente
    public AutorDTO atualizar(Long id, AutorDTO dto) {
        Autor autorExistente = buscarEntidadeOuErro(id); // Verifica se o autor existe
        autorExistente.setNome(dto.getNome()); // Atualiza o nome
        Autor atualizado = autorRepository.save(autorExistente); // Salva as alterações
        return new AutorDTO(atualizado); // Retorna o DTO atualizado
    }

    // Exclui um autor pelo ID
    public void excluir(Long id) {
        if (!autorRepository.existsById(id)) {
            // Se o autor não existir, lança erro 404
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Autor não encontrado com o ID: " + id);
        }
        autorRepository.deleteById(id); // Caso exista, remove do banco
    }
}
