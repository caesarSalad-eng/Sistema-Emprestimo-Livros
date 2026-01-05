# 📚 Sistema de Empréstimo de Livros

Projeto desenvolvido em **Java com Spring Boot**, simulando um sistema de empréstimo de livros via terminal (CLI), com foco em boas práticas de backend, organização em camadas e uso do Spring Data JPA.

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
