# 📚 Sistema de Empréstimo de Livros

Este projeto foi criado com foco em **aprendizado prático de Spring Boot** e consolidação de conceitos fundamentais de backend.

Durante o desenvolvimento, percebi uma evolução clara na minha autonomia como desenvolvedor, conseguindo resolver cada vez mais problemas com menos dependência de ferramentas de IA, o que reforçou meu entendimento real da linguagem e do framework.

Além disso, o projeto foi essencial para consolidar práticas de **Git e GitHub**, como versionamento de código, organização de commits e estruturação de repositórios.

---

## 🚀 Funcionalidades

### 👤 Usuários
- Cadastrar usuário
- Listar usuários
- Buscar usuário por ID
- Buscar usuário por e-mail
- Atualizar usuário
- Deletar usuário

### 📖 Livros
- Cadastrar livro
- Listar livros
- Buscar livro por ID
- Atualizar livro
- Deletar livro

### 🔄 Empréstimos
- Emprestar livro para um usuário
- Devolver livro
- Listar todos os empréstimos
- Buscar empréstimo por ID
- Buscar empréstimos por usuário
- Listar empréstimos em atraso

---

## 🧠 Regras de Negócio
- Um livro não pode ser emprestado se já estiver emprestado
- Um empréstimo registra data de retirada e devolução
- O status do livro é atualizado automaticamente
- Validações para usuários e livros inexistentes

---

## 🛠️ Tecnologias Utilizadas
- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- Banco de dados relacional
- Maven
- CommandLineRunner (menu interativo no terminal)

---

## 🗂️ Arquitetura
O projeto segue uma separação clara de responsabilidades:
- **Model**: entidades do sistema
- **Repository**: acesso a dados com JPA
- **Service**: regras de negócio
- **Runner/Menu**: interação com o usuário via terminal

---

## 🎯 Objetivo do Projeto
Este projeto foi criado com foco em **aprendizado prático de Spring Boot**, reforçando conceitos como:
- CRUD
- Persistência com JPA
- Injeção de dependências
- Organização de código
- Regras de negócio no backend

---

Desenvolvido por **Kendall** 🚀
