package com.br.sistema_emprestimo_livros.repository;

import com.br.sistema_emprestimo_livros.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    List<Livro> findByDisponivelTrue();

}
