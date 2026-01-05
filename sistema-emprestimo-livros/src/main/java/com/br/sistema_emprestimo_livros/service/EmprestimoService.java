package com.br.sistema_emprestimo_livros.service;

import com.br.sistema_emprestimo_livros.model.*;
import com.br.sistema_emprestimo_livros.repository.EmprestimoRepository;
import com.br.sistema_emprestimo_livros.repository.LivroRepository;
import com.br.sistema_emprestimo_livros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;

    private final UsuarioRepository usuarioRepository;

    private final LivroRepository livroRepository;

    public EmprestimoService(LivroRepository livroRepository, EmprestimoRepository emprestimoRepository, UsuarioRepository usuarioRepository){

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


        if (emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATIVO || emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATRASADO) {

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

    public String listarEmprestimos(){

        List<Emprestimo> emprestimo = emprestimoRepository.findAll();

        if(emprestimo.isEmpty()){

            return "Não há empréstimos cadastrados. A lista está vazia";

        }

        StringBuilder resultadoLista = new StringBuilder();

            for (Emprestimo listaEmprestimos : emprestimo){

                resultadoLista.append("\nID Emprétimo: ").append(listaEmprestimos.getId());
                resultadoLista.append("\nUsuário: ").append(listaEmprestimos.getUsuario().getNome());
                resultadoLista.append("\nLivro: ").append(listaEmprestimos.getLivro().getTitulo());
                resultadoLista.append("\nStatus: ").append(listaEmprestimos.getStatusEmprestimo());
                resultadoLista.append("\n-------------------------------------------------------");

            }

        return resultadoLista.toString();

    }

    public String buscarEmprestimoPorId(Long id){

        Optional<Emprestimo> emprestimosList = emprestimoRepository.findById(id);

        if (emprestimosList.isEmpty()){

            return "Empréstimo não encontrado";

        }

        Emprestimo emprestimo = emprestimosList.get();

        StringBuilder resultadoBusca = new StringBuilder();

        resultadoBusca.append("\nID Empréstimo: ").append(emprestimo.getId());
        resultadoBusca.append("\nUsuário: ").append(emprestimo.getUsuario().getNome());
        resultadoBusca.append("\nLivro: ").append(emprestimo.getLivro().getTitulo());
        resultadoBusca.append("\nStatus: ").append(emprestimo.getStatusEmprestimo());
        resultadoBusca.append("\nData Empréstimo: ").append(emprestimo.getDataEmprestimo());

        return resultadoBusca.toString();

    }

    public String buscarEmprestimoPorUsuario(Long usuarioId){

        Optional<Usuario> optUsuario = usuarioRepository.findById(usuarioId);

        if (optUsuario.isEmpty()){

            return "Usuário não encontrado";

        }

        List<Emprestimo> emprestimoList = emprestimoRepository.findByUsuarioId(usuarioId);

        if (emprestimoList.isEmpty()){

            return "O usuário não pussui empréstimos";

        }

        StringBuilder resultadoEmprestimos = new StringBuilder();

        for (Emprestimo emprestimo : emprestimoList){

            resultadoEmprestimos.append("\n Id: ").append(emprestimo.getId());
            resultadoEmprestimos.append("\nNome: ").append(emprestimo.getUsuario().getNome());
            resultadoEmprestimos.append("\nStatus: ").append(emprestimo.getStatusEmprestimo());
            resultadoEmprestimos.append("\nData de Empréstimo: ").append(emprestimo.getDataEmprestimo());
            resultadoEmprestimos.append("\nData Devolução: ").append(emprestimo.getDataDevolucao());

        }

        return resultadoEmprestimos.toString();

    }

    public String emprestimosAtrasados (){

        List<Emprestimo> emprestimoListAtrasados = emprestimoRepository.findAll();

        if (emprestimoListAtrasados.isEmpty()){

            return "Não há empréstimos salvos";

        }
        StringBuilder resultadoEmprestimosAtrasados = new StringBuilder();

        for (Emprestimo emprestimosAtrasados : emprestimoListAtrasados){

            if (emprestimosAtrasados.getStatusEmprestimo() == StatusEmprestimo.ATRASADO){

                resultadoEmprestimosAtrasados.append("\nID: ").append(emprestimosAtrasados.getId());
                resultadoEmprestimosAtrasados.append("\nLivro: ").append(emprestimosAtrasados.getLivro().getTitulo());
                resultadoEmprestimosAtrasados.append("\nUsuário: ").append(emprestimosAtrasados.getUsuario().getNome());
                resultadoEmprestimosAtrasados.append("\nUsuário Id: ").append(emprestimosAtrasados.getUsuario().getId());
                resultadoEmprestimosAtrasados.append("\nUsuário Email: ").append(emprestimosAtrasados.getUsuario().getEmail());

            }


        }

        if (resultadoEmprestimosAtrasados.isEmpty()){

            return "Não há Empréstimos Atrasados";

        }

        return resultadoEmprestimosAtrasados.toString();

    }


}
