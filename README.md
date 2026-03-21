# Condomínios API

API REST para sistema de gerenciamento de condomínio, desenvolvida com Java Spring Boot e PostgreSQL.

## Tecnologias

- Java 21
- Spring Boot
- PostgreSQL
- Flyway
- Maven

## Pré-requisitos

- Java 21+
- Maven 3.8+
- PostgreSQL 15+
- Git

## Configuração local

### 1. Clone o repositório
```bash
git clone https://github.com/miguelnsimoes/condominios-api.git
cd condominios-api
```

### 2. Configure o banco de dados
Crie um banco PostgreSQL local:
```sql
CREATE DATABASE condominios_db;
```

### 3. Configure as variáveis de ambiente
Copie o arquivo de exemplo e preencha com suas credenciais:
```bash
cp .env.example .env
```

### 4. Rode o projeto
```bash
./mvnw spring-boot:run
```

A API estará disponível em `http://localhost:8080`


## Convenção de commits
```
feat: adiciona nova funcionalidade
fix: corrige um bug
refactor: refatora sem mudar comportamento
docs: atualiza documentação
test: adiciona ou corrige testes
```

## Fluxo de contribuição

1. Crie uma branch a partir de `develop`: `git checkout -b feature/nome-da-feature`
2. Faça commits seguindo a convenção acima
3. Abra um Pull Request para `develop`
4. Aguarde revisão e aprovação
5. Após aprovado, faça o merge

## Documentação

- [Levantamento de requisitos](docs/requisitos.md)
