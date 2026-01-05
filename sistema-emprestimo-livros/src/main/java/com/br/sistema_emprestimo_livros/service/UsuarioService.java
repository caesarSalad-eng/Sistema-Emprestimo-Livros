package com.br.sistema_emprestimo_livros.service;

import com.br.sistema_emprestimo_livros.model.Emprestimo;
import com.br.sistema_emprestimo_livros.model.StatusEmprestimo;
import com.br.sistema_emprestimo_livros.model.Usuario;
import com.br.sistema_emprestimo_livros.repository.EmprestimoRepository;
import com.br.sistema_emprestimo_livros.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final EmprestimoRepository emprestimoRepository;

    public UsuarioService(UsuarioRepository usuarioRepository, EmprestimoRepository emprestimoRepository){

        this.usuarioRepository = usuarioRepository;

        this.emprestimoRepository = emprestimoRepository;

    }

    public String cadastrarUsuario(String nome, String email){

        Usuario usuario = new Usuario();

        if (nome == null || nome.isBlank()){

            return "Nome inválido. Por favor tente novamente";

        }

        if (email == null || email.isBlank()){

            return "Email inválido. Por favor tente novamente";

        }

        Optional<Usuario> optUsuarioCadastro = usuarioRepository.findByEmail(email);

        if (optUsuarioCadastro.isPresent()){

            return "Este email já está cadastrado. Digite um novo email";

        }

        usuario.setNome(nome.toUpperCase());
        usuario.setEmail(email.toLowerCase().trim());

        usuarioRepository.save(usuario);

        return "Usuário cadastrado com Sucesso!!";

    }

    public String listarUsuarios(){

        List<Usuario> usuarioList = usuarioRepository.findAll();

        if (usuarioList.isEmpty()){

            return "Não tem usuário cadastrado";

        }

        StringBuilder resultadoLista = new StringBuilder();

        for (Usuario usuariosFor : usuarioList){

        resultadoLista.append("\nId: ").append(usuariosFor.getId());
        resultadoLista.append("\nNome: ").append(usuariosFor.getNome());
        resultadoLista.append("\nEmail: ").append(usuariosFor.getEmail());
        resultadoLista.append("\n-----------------------------------------------");

    }

        return resultadoLista.toString();

    }

    public String buscarUsuarioPorId(Long id){

        Optional<Usuario> optUsuarioBuscaId = usuarioRepository.findById(id);

    if (optUsuarioBuscaId.isEmpty()){

        return "Usuário não encontrado";

    }

    StringBuilder resultadoBuscaId = new StringBuilder();

    Usuario usuario = optUsuarioBuscaId.get();

    resultadoBuscaId.append("\nId: ").append(usuario.getId());
    resultadoBuscaId.append("\nNome: ").append(usuario.getNome());
    resultadoBuscaId.append("\nEmail: ").append(usuario.getEmail());
    resultadoBuscaId.append("\n----------------------------------------------");

    return resultadoBuscaId.toString();


    }

    public String buscarUsuarioPorEmail(String email){

        if (email.isBlank()){

            return "Email Inválido";

        }

        Optional<Usuario> optUsuarioBuscaEmail = usuarioRepository.findByEmail(email.toLowerCase().trim());

        if (optUsuarioBuscaEmail.isEmpty()){

            return "Usuário não encontrado";

        }

        StringBuilder resultadoBuscaEmail = new StringBuilder();

        Usuario usuario = optUsuarioBuscaEmail.get();

        resultadoBuscaEmail.append("\nId: ").append(usuario.getId());
        resultadoBuscaEmail.append("\nNome: ").append(usuario.getNome());
        resultadoBuscaEmail.append("\nEmail: ").append(usuario.getEmail());
        resultadoBuscaEmail.append("\n----------------------------------------------");

        return resultadoBuscaEmail.toString();

    }

    public String atualizarUsuario(Long id, String nome, String email){

        if (id == null){

            return "ID inválido";

        }

        Optional<Usuario> optUsuarioAtualizar = usuarioRepository.findById(id);

        if (optUsuarioAtualizar.isEmpty()){

            return "Usuário não encontrado";

        }

        Usuario usuario = optUsuarioAtualizar.get();

        if (nome != null && !nome.isBlank()){

            usuario.setNome(nome.toUpperCase());

        }

        if (email != null && !email.isBlank()){

            String novoEmail = email.toLowerCase().trim();

            Optional<Usuario> optEmailExistente = usuarioRepository.findByEmail(novoEmail);

            if (optEmailExistente.isPresent() && !optEmailExistente.get().getId().equals(usuario.getId())){

                return "Esse email já está cadastrado em outro usuário";

            }

            usuario.setEmail(novoEmail);

            usuarioRepository.save(usuario);

        }

        return "Usuário Atualizado com Sucesso!!";

    }

    public String deletarUsuario(Long id){

        if(id == null){

            return "Id inválido";

        }

        Optional<Usuario> optUsuarioDeletar = usuarioRepository.findById(id);

        if (optUsuarioDeletar.isEmpty()){

            return "Usuário não encontrado";

        }

        Usuario usuario = optUsuarioDeletar.get();

        List<Emprestimo> optEmprestimoBuscarUsuarioId = emprestimoRepository.findByUsuarioId(id);

        for (Emprestimo emprestimo : optEmprestimoBuscarUsuarioId){

            if (emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATIVO || emprestimo.getStatusEmprestimo() == StatusEmprestimo.ATRASADO){

                return "O usuário ainda tem empréstimo ativo ou atrasado. Não foi possível deletar o usuáario";

            }

        }

        usuarioRepository.delete(usuario);

        return "Usuário deletado com Sucesso!!";

    }


}
