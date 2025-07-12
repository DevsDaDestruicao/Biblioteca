    /*
    Na arquitetura Spring, os repositórios são interfaces
    que estendem o JpaRepository ou CrudRepository.
    Com isso, o Spring gera automaticamente métodos prontos para
    salvar, buscar, atualizar e deletar dados, como:
    */

package com.example.Biblioteca.repository;

import com.example.Biblioteca.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

    // Marca essa interface como um repositório Soring
    @Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {
        // Esta interface herda todos os métodos do JpaRepository
        // com findAll(),
        // findById(),
        // save(),
        // deleteById()
        // etc.
}
