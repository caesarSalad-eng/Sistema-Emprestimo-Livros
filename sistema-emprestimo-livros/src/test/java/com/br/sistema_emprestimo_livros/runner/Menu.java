package com.br.sistema_emprestimo_livros.runner;

import com.br.sistema_emprestimo_livros.model.Usuario;
import com.br.sistema_emprestimo_livros.service.EmprestimoService;
import com.br.sistema_emprestimo_livros.service.LivroService;
import com.br.sistema_emprestimo_livros.service.UsuarioService;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.naming.BinaryRefAddr;
import java.util.List;
import java.util.Scanner;

@Component
public class Menu implements CommandLineRunner {

    private final UsuarioService usuarioService;

    private final LivroService livroService;

    private final EmprestimoService emprestimoService;

    private final Scanner sc = new Scanner(System.in);


    public Menu(UsuarioService usuarioService, LivroService livroService, EmprestimoService emprestimoService){

        this.emprestimoService = emprestimoService;

        this.livroService = livroService;

        this.usuarioService = usuarioService;

    }

    public void run(String... args){

        while (true){

            menuPrincipal();

        }

    }

    public void menuPrincipal(){

        System.out.println("\n== SISTEMA DE EMPRÉSTIMO DE LIVROS ==");
        System.out.println("\n== QUAL MENU DESEJA ACESSAR? ==");
        System.out.println("\n1 - Usuários");
        System.out.println("\n2 - Livros");
        System.out.println("\n3 - Empréstimos");
        System.out.println("\n4 - Sair");

        int opcao = sc.nextInt();
        sc.nextLine();

        switch (opcao){

            case 1:

                menuUsuario();

                break;

            case 2:

                menuLivros();

                break;

            case 3:

                menuEmprestimos();

                break;

            case 4:

                System.out.println("Saindo...");

                System.exit(0);

                break;

            default:

                System.out.println("Opção incorreta. Tente Novamente");


        }

    }

    public void menuUsuario(){

        System.out.println("\n== MENU USUÁRIO ==");
        System.out.println("\n1 - Cadastrar Usuário");
        System.out.println("\n2 - Listar Usuários");
        System.out.println("\n3 - Buscar Usuário por ID");
        System.out.println("\n4 - Busca Usuário por EMAIL");
        System.out.println("\n5 - Atualizar Usuário");
        System.out.println("\n6 -  Deletar Usuário");
        System.out.println("\n0 - Voltar para MENU PRINCIPAL");

        int opcaoMenuUsuario =  sc.nextInt();
        sc.nextLine();

        switch (opcaoMenuUsuario){

            case 1:

                cadastroUsuario();

                break;

            case 2:

                listarUsuarios();

                break;

            case 3:

                buscarUsuarioPorId();

                break;

            case 4:

                buscarUsuarioPorEmail();

                break;

            case 5:

                atualizarUsuario();

                break;

            case 6:

                deletarUsuario();

                break;

            case 0:



                break;

            default:

                System.out.println("Opção inválida. Tente Novamente");


        }


    }

    public void cadastroUsuario(){

        System.out.println("\n== CADASTRAMENTO DE USUÁRIO ==");

        System.out.println("\nDigite seu Nome: ");
        String nomeUsuario = sc.nextLine();

        System.out.println("\nDigite seu Email: ");
        String emailUsuario = sc.nextLine();

        String resultadoCadastrarUsuario = usuarioService.cadastrarUsuario(nomeUsuario, emailUsuario);

        System.out.println(resultadoCadastrarUsuario);

    }

    public void listarUsuarios(){

        System.out.println("\n== LISTA DE USUÁARIOS ==");

        String listaUsuarios = usuarioService.listarUsuarios();

        System.out.println(listaUsuarios);


    }

    public void buscarUsuarioPorId(){

        System.out.println("\n== BUSCA DE USUÁRIO POR ID");

        System.out.println("\nDigite o ID do Usuário que deseja procurar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        String resultadoBuscaPorId = usuarioService.buscarUsuarioPorId(id);

        System.out.println(resultadoBuscaPorId);

    }

    public void buscarUsuarioPorEmail(){

        System.out.println("\n== BUSCA DE USUÁRIO POR EMAIL");

        System.out.println("\nDigite o Email do Usuário que deseja procurar: ");
        String emailProcurarUsuario = sc.nextLine();

       String resultadoBuscaPorEmail =  usuarioService.buscarUsuarioPorEmail(emailProcurarUsuario);

        System.out.println(resultadoBuscaPorEmail);

    }

    public void atualizarUsuario(){

        System.out.println("\n== ATUALIZAÇÃO DE USUÁRIO ==");

        System.out.println("\nDigite o ID do Usuário que deseja Atualizar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        System.out.println("\nDigite o Novo Nome do Usuário: ");
        String novoNome = sc.nextLine();

        System.out.println("\nDigite o Nome Email do Usuário: ");
        String novoEmail = sc.nextLine();

        String resultadoAtualizacao = usuarioService.atualizarUsuario(id, novoNome, novoEmail);

        System.out.println(resultadoAtualizacao);

    }

    public void deletarUsuario(){

        System.out.println("\n== DELETAR USUÁRIO");

        System.out.println("\nDigite o ID do Usuário que deseja Deletar: ");
        Long id = sc.nextLong();
        sc.nextLine();

       String resultadoDeletar =  usuarioService.deletarUsuario(id);

        System.out.println(resultadoDeletar);

    }

    public void menuLivros(){

        System.out.println("\n== MENU LIVROS ==");
        System.out.println("\n1 - Cadastrar Novo Livro");
        System.out.println("\n2 - Listar Livros");
        System.out.println("\n3 - Buscar Livro por ID");
        System.out.println("\n4 - Atualizar Livro");
        System.out.println("\n5 - Deletar Livro");
        System.out.println("\n0 - Voltar para o MENU PRINCIPAL ");

        int opcaoMenuLivros = sc.nextInt();
        sc.nextLine();

        switch (opcaoMenuLivros){

            case 1:

                cadastrarLivro();

                break;

            case 2:

                listarLivros();

                break;

            case 3:

                buscarLivrosPorId();

                break;

            case 4:

                atualizarLivro();

                break;

            case 5:

                deletarLivro();

                break;

            case 0:

                break;

            default:

                System.out.println("Opção inválida. Tente Novamente");

        }


    }

    public void cadastrarLivro(){

        System.out.println("\n== CADASTRO DE LIVROS ==");

        System.out.println("Digite o Título do Livro: ");
        String titulo = sc.nextLine();

        System.out.println("Digite o Autor do Livro: ");
        String autor = sc.nextLine();

        String resultadoCadastroLivro = livroService.cadastrarLivro(titulo, autor);

        System.out.println(resultadoCadastroLivro);

    }

    public void listarLivros(){

        System.out.println("\n== LISTA DE LIVROS CADASTRADOS ==");

        String resultadoListaLivros = livroService.listarLivros();

        System.out.println(resultadoListaLivros);

    }

    public void buscarLivrosPorId(){

        System.out.println("\n== BUSCA DE LIVROS POR ID ==");

        System.out.println("\nDigite o Id do Livro que deseja procurar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        String resultadoBuscarLivroPorId = livroService.bucarLivroPorId(id);

        System.out.println(resultadoBuscarLivroPorId);

    }

    public void atualizarLivro(){

        System.out.println("\n== ATUALIZAR LIVRO ==");

        System.out.println("\nDigite o Id do Livro que deseja atualizar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        System.out.println("\nDigite o novo Título do Livro: ");
        String novoTitulo = sc.nextLine();

        System.out.println("Digite o novo Autor do Livro: ");
        String novoAutor = sc.nextLine();

        String resultadoAtualizarLivro = livroService.atualizarLivro(id, novoAutor, novoTitulo);

        System.out.println(resultadoAtualizarLivro);

    }

    public void deletarLivro(){

        System.out.println("\n== DELETAR LIVRO ==");

        System.out.println("\nDigite o Id do Livro que deseja deletar: ");
        Long id = sc.nextLong();
        sc.nextLine();

        String resultadoDeletarLivro = livroService.deletarLivro(id);

        System.out.println(resultadoDeletarLivro);

    }

    public void menuEmprestimos(){
        
    }

}
