# ADR-001 — Escolha da stack tecnológica

- **Data:** 2026-03-21
- **Status:** Aceito

## Contexto

Precisamos definir a stack tecnológica para o desenvolvimento do sistema de gerenciamento de condomínio, considerando que a equipe já tem experiência em programação mas não nas tecnologias específicas escolhidas.

## Decisão

Adotamos a seguinte stack:

- **Back-end:** Java 21 com Spring Boot
- **Banco de dados:** PostgreSQL
- **Migrações:** Flyway
- **Front-end:** React.js

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
|---|---|
| Node.js + Express | Spring Boot oferece mais estrutura para APIs REST complexas |
| MySQL | PostgreSQL tem melhor suporte e é mais robusto |
| Liquibase | Flyway é mais simples para equipes iniciantes |

## Consequências

- A equipe precisará aprender Java e Spring Boot
- Spring Boot reduz configuração manual com suas convenções
- PostgreSQL exige instalação local para desenvolvimento