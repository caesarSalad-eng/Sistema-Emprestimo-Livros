package com.br.sistema_emprestimo_livros.service;

import com.br.sistema_emprestimo_livros.model.*;
import com.br.sistema_emprestimo_livros.repository.EmprestimoRepository;
import com.br.sistema_emprestimo_livros.repository.LivroRepository;
import com.br.sistema_emprestimo_livros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    private final UsuarioRepository usuarioRepository;

    private final LivroRepository livroRepository;

    public EmprestimoService(LivroRepository livroRepository, Scanner sc, EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository){

        this.emprestimoRepository = emprestimoRepository;

        this.usuarioRepository = usuarioRepository;

        this.livroRepository = livroRepository;

    }

    public String emprestarLivro(Long usuarioId, Long livroId){

        Usuario usuario;

        Livro livro;

        Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);

        if (optUsuario.isPresent()){

            usuario = optUsuario.get();


        } else{

            return "Usuário não encontrado";

        }

        Optional<Livro> optLivro = livroRepository.findById(livroId);

        if (optLivro.isPresent()){

            livro = optLivro.get();

            if (livro.getStatusLivro() == StatusLivro.DISPONIVEL){

                Emprestimo emprestimo =  new Emprestimo();

                emprestimo.setUsuario(usuario);

                emprestimo.setLivro(livro);

                emprestimo.setStatusEmprestimo(StatusEmprestimo.ATIVO);

                emprestimo.setDataEmprestimo(LocalDate.now());

                livro.setStatusLivro(StatusLivro.INDISPONIVEL);

                livroRepository.save(livro);

                emprestimoRepository.save(emprestimo);

                return "Empréstimo realizado com sucesso!";

            } else{

                return "Livro indisponível";

            }

        } else {

            return "Livro não encontrado";

        }

    }

    public String devolverLivro(Long emprestimoId) {

        Emprestimo emprestimo;

        Optional<Emprestimo> optEmprestimo = emprestimoRepository.findById(emprestimoId);

        if (optEmprestimo.isPresent()) {

            emprestimo = optEmprestimo.get();

        } else {

            return "Empréstimo não encontrado";

        }


        if (emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATIVO && emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATRASADO) {

            emprestimo.setStatusEmprestimo(StatusEmprestimo.LIVRO_DEVOLVIDO);
            emprestimo.setDataDevolucao(LocalDate.now());

            Livro livro = emprestimo.getLivro();
            livro.setStatusLivro(StatusLivro.DISPONIVEL);

            livroRepository.save(livro);
            emprestimoRepository.save(emprestimo);

            return "Livro devolvido com sucesso!";

        }else{

            return "Este empréstimo já foi finalizado";

        }


    }


}
