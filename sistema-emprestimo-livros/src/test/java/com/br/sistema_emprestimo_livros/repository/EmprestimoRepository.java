package com.br.sistema_emprestimo_livros.repository;

import com.br.sistema_emprestimo_livros.model.Emprestimo;
import com.br.sistema_emprestimo_livros.model.StatusEmprestimo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmprestimoRepository  extends JpaRepository<Emprestimo, Long> {

    List<Emprestimo> findByUsuarioId(Long usuarioId);

    List<Emprestimo> findByLivroId(Long livroId);

    List<Emprestimo> findByStatusEmprestimo(StatusEmprestimo status);


}
