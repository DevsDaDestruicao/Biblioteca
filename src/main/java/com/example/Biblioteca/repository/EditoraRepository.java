/*
   Na arquitetura Spring, os repositórios são interfaces
   que estendem o JpaRepository ou CrudRepository.
   Com isso, o Spring gera automaticamente métodos prontos para
   salvar, buscar, atualizar e deletar dados, como:
   */

package com.example.Biblioteca.repository;

import com.example.Biblioteca.model.Editora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
// Interface de repositório para entidade Editora
@Repository
public interface EditoraRepository extends JpaRepository<Editora, Long> {
    // Sem necessidade de implementar nada manualmente
}
