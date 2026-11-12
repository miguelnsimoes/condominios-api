# Condomínios API

API REST para o gerenciamento administrativo de condomínios, desenvolvida com **Java 21**, **Spring Boot 3** e **PostgreSQL**. O sistema centraliza o controle de moradores, encomendas, áreas comuns, pagamentos e ocorrências.

## Tecnologias

- Java 21
- Spring Boot 3
- PostgreSQL
- Flyway (versionamento do banco de dados)
- Maven
- Swagger/OpenAPI

## Funcionalidades

- **Gestão de espaços:** cadastro de blocos e apartamentos com validação de unicidade por bloco e número.
- **Controle de moradores:** gerenciamento de residentes e seus vínculos habitacionais.
- **Logística interna:** registro de recebimento e controle de retirada de encomendas na portaria.
- **Reservas de áreas comuns:** agendamento com controle de conflitos de data e horário.
- **Financeiro e ocorrências:** registro de pagamentos de taxas e acompanhamento de problemas internos.
- **Relatórios:** consultas gerenciais sobre moradores, pagamentos, reservas e encomendas.
- **Autenticação e autorização:** acesso com JWT e permissões para administradores e moradores.

## Entidades do sistema

`Bloco` | `Apartamento` | `Morador` | `Funcionario` | `Encomenda` | `Pagamento` | `AreaComum` | `ReservaArea` | `Ocorrencia` | `Usuario`

## Pré-requisitos

- JDK 21+
- PostgreSQL 15+
- Git

O Maven Wrapper está incluído na pasta `api`.

## Configuração local

### 1. Clone o repositório e acesse o projeto

```bash
git clone https://github.com/miguelnsimoes/condominios-api.git
cd condominios-api/api
```

### 2. Configure o banco de dados

Crie um banco PostgreSQL local:

```sql
CREATE DATABASE condominios_db;
```

### 3. Configure as variáveis de ambiente

Defina as variáveis no terminal ou na configuração de execução da sua IDE:

| Variável | Descrição |
| --- | --- |
| `DB_URL` | URL JDBC, por exemplo `jdbc:postgresql://localhost:5432/condominios_db` |
| `DB_USERNAME` | Usuário do PostgreSQL |
| `DB_PASSWORD` | Senha do PostgreSQL |
| `JWT_SECRET` | Segredo usado para assinar os tokens JWT |

### 4. Rode o projeto

No Linux/macOS:

```bash
./mvnw spring-boot:run
```

No Windows (PowerShell):

```powershell
.\mvnw.cmd spring-boot:run
```

A API estará disponível em `http://localhost:8080`.

### 5. Execute os testes

Com o PostgreSQL disponível e as variáveis de ambiente configuradas, execute na pasta `api`:

```bash
./mvnw test
```

No Windows (PowerShell):

```powershell
.\mvnw.cmd test
```

## Convenção de commits

```text
feat: adiciona nova funcionalidade
fix: corrige um bug
refactor: refatora sem mudar comportamento
docs: atualiza documentação
test: adiciona ou corrige testes
```

## Fluxo de contribuição

1. Crie uma branch a partir de `develop`: `git checkout -b feature/nome-da-feature`
2. Faça commits seguindo a convenção acima.
3. Abra um Pull Request para `develop`.
4. Aguarde revisão e aprovação.
5. Após aprovado, faça o merge.

## Documentação

- [Levantamento de requisitos](docs/requisitos.md)
- [Decisão da stack tecnológica](docs/adr/ADR-001-stack-tecnologica.md)
- [Estrutura de pacotes](docs/adr/ADR-002-estrutura-de-pacotes.md)
