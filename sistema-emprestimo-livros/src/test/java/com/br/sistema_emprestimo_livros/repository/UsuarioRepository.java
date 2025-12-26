package com.br.sistema_emprestimo_livros.repository;

import com.br.sistema_emprestimo_livros.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
