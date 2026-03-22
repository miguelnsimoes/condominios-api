# ADR-001 — Escolha da stack tecnológica

- **Data:** 2026-03-21
- **Status:** Aceito

## Contexto

O projeto consiste em uma API REST para gerenciamento de condomínio, com requisito acadêmico de uso obrigatório de Java e banco de dados relacional. A equipe optou por adotar ferramentas amplamente utilizadas no mercado para garantir qualidade, manutenibilidade e aderência às boas práticas de desenvolvimento de software.

## Decisão

Adotamos a seguinte stack:

- **Back-end:** Java 21 com Spring Boot
- **Banco de dados:** PostgreSQL
- **Migrações:** Flyway
- **Front-end:** React.js

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
|---|---|
| MySQL | PostgreSQL tem melhor suporte a tipos avançados e é mais robusto |
| Liquibase | Flyway é mais simples e usa SQL puro, ideal para a equipe |

## Restrições do projeto

- Linguagem obrigatória: **Java** (requisito da matéria de POO2)
- Banco de dados obrigatório: **relacional** (requisito da matéria de POO2)
- Spring Boot foi uma escolha da equipe por oferecer estrutura, convenções e produtividade para APIs REST