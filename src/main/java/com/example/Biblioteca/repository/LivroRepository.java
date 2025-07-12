/*
   Na arquitetura Spring, os repositórios são interfaces
   que estendem o JpaRepository ou CrudRepository.
   Com isso, o Spring gera automaticamente métodos prontos para
   salvar, buscar, atualizar e deletar dados, como:
   */

package com.example.Biblioteca.repository;

import com.example.Biblioteca.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Interface de repositório para persistencia da entidade Livro
@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {
    // Você pode adcionar métodos personalizados aqui, como:
    // List<Livro> findByTituloContaining(String palavra);

}
