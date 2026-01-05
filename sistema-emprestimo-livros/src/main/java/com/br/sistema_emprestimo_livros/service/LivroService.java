package com.br.sistema_emprestimo_livros.service;

import com.br.sistema_emprestimo_livros.model.Emprestimo;
import com.br.sistema_emprestimo_livros.model.Livro;
import com.br.sistema_emprestimo_livros.model.StatusEmprestimo;
import com.br.sistema_emprestimo_livros.model.StatusLivro;
import com.br.sistema_emprestimo_livros.repository.EmprestimoRepository;
import com.br.sistema_emprestimo_livros.repository.LivroRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LivroService {

    private final LivroRepository livroRepository;

    private final EmprestimoRepository emprestimoRepository;

    public LivroService(LivroRepository livroRepository, EmprestimoRepository emprestimoRepositor){

        this.livroRepository = livroRepository;

        this.emprestimoRepository = emprestimoRepositor;

    }

    public String cadastrarLivro(String titulo, String autor){

        if (titulo == null || titulo.isBlank()){

            return "Titulo inválido. Tente novamente";

        }

        if (autor == null || autor.isBlank()){

            return "Autor inválido. Tente novamente";

        }

        Livro novoLivro = new Livro();

        novoLivro.setTitulo(titulo.toUpperCase().trim());
        novoLivro.setAutor(autor.toUpperCase().trim());
        novoLivro.setStatusLivro(StatusLivro.DISPONIVEL);

        livroRepository.save(novoLivro);

        return "Livro cadastrado com sucesso!!";

    }

    public String listarLivros(){

        List<Livro> livrosList = livroRepository.findAll();

        if (livrosList.isEmpty()){

            return "Lista de livros vazia";

        }

        StringBuilder resultadoLivrosLista = new StringBuilder();

        for (Livro livro : livrosList){

            resultadoLivrosLista.append("\nId: ").append(livro.getId());
            resultadoLivrosLista.append("\nTítulo: ").append(livro.getTitulo());
            resultadoLivrosLista.append("\nAutor: ").append(livro.getAutor());
            resultadoLivrosLista.append("\n-------------------------------------");

        }

        return resultadoLivrosLista.toString();

    }

    public String bucarLivroPorId(Long id){

        Livro livroBuscaId;

        if (id == null){

            return "Id inválido. Tente novamente";

        }

        Optional<Livro> optLivroBuscaId = livroRepository.findById(id);

        if (optLivroBuscaId.isEmpty()){

            return "Livro não encontrado";

        }

        StringBuilder resultadoBuscaLivroId = new StringBuilder();

        livroBuscaId = optLivroBuscaId.get();

        resultadoBuscaLivroId.append("\nId: ").append(livroBuscaId.getId());
        resultadoBuscaLivroId.append("\nTítulo:").append(livroBuscaId.getTitulo());
        resultadoBuscaLivroId.append("\nAutor: ").append(livroBuscaId.getAutor());
        resultadoBuscaLivroId.append("\n-------------------------------------");

        return resultadoBuscaLivroId.toString();

    }

    public String atualizarLivro(Long id, String autor, String titulo){


        Livro livroAtualizar;

        if (id == null){

            return "Id inválido.Tente novamente";

        }

        Optional<Livro> optLivroBuscaAtualizar = livroRepository.findById(id);

        if (optLivroBuscaAtualizar.isEmpty()){

            return "Livro não encontrado";

        }

        livroAtualizar = optLivroBuscaAtualizar.get();

        if (titulo != null && !titulo.isBlank()){

            livroAtualizar.setTitulo(titulo.toUpperCase());

        }

        if (autor != null && !autor.isBlank()) {

            livroAtualizar.setAutor(autor.toUpperCase());

        }

        livroRepository.save(livroAtualizar);

        return "Livro atualizado com sucesso";

    }

    public String deletarLivro(Long id){

        Livro livroDeletar;

        if(id == null){

            return "Id inválido";

        }

        Optional<Livro> optLivroBuscaDeletar = livroRepository.findById(id);

        if (optLivroBuscaDeletar.isEmpty()){

            return "Livro não encontrado";

        }

        livroDeletar = optLivroBuscaDeletar.get();

        if (livroDeletar.getStatusLivro() == StatusLivro.INDISPONIVEL){

            return "Não foi possível deletar o livro porque o livro está INDISPONÍVEL";

        }

        List<Emprestimo> optEmprestimoDeletar = emprestimoRepository.findByLivroId(id);

        for (Emprestimo emprestimoDeletar : optEmprestimoDeletar){

            if (emprestimoDeletar.getStatusEmprestimo() == StatusEmprestimo.ATIVO ||
                    emprestimoDeletar.getStatusEmprestimo() == StatusEmprestimo.ATRASADO) {

                return "Livro possui empréstimos ativos ou atrasados";
            }
        }

        livroRepository.delete(livroDeletar);

        return "Livro deletado com Sucesso!!";


    }

}
