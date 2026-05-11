# Condomínios API (Work In Progress)

API REST robusta para o gerenciamento administrativo de condomínios, desenvolvida com **Java 21**, **Spring Boot 3** e **PostgreSQL**. Este sistema visa centralizar o controle de moradores, encomendas, áreas comuns e ocorrências em uma única plataforma.

> 🚧 **Status:** Projeto em desenvolvimento ativo na branch `develop`.

## 🛠️ Stack Tecnológica

*   **Linguagem:** Java 21
*   **Framework:** Spring Boot 3
*   **Banco de Dados:** PostgreSQL
*   **Migrações:** Flyway (Versionamento de banco de dados)
*   **Gerenciador de Dependências:** Maven
*   **Documentação:** Swagger/OpenAPI (em progresso)

## 📋 Planejamento de Requisitos

O desenvolvimento é guiado por um levantamento rigoroso de requisitos para garantir a integridade dos dados:

### Funcionalidades Core (RFs):
- **Gestão de Espaço:** Cadastro de blocos e apartamentos com validação de unicidade (Bloco + Número).
- **Controle de Moradores:** Gerenciamento completo de residentes e seus vínculos habitacionais.
- **Logística Interna:** Registro de recebimento e controle de retirada de encomendas na portaria.
- **Reservas de Áreas Comuns:** Sistema de agendamento para salão de festas e churrasqueiras com trava para reservas duplicadas.
- **Financeiro e Ocorrências:** Registro de pagamentos de taxas e fluxo de resolução de problemas internos.

## 🏗️ Entidades do Sistema
O domínio da aplicação é composto pelas seguintes entidades principais:
`Bloco` | `Apartamento` | `Morador` | `Encomenda` | `Pagamento` | `ReservaArea` | `Ocorrencia`

##  Como Executar e Testar

### Passo a Passo

1.  **Clone o repositório e acesse a branch de desenvolvimento:**
    ```bash
    git clone [https://github.com/miguelnsimoes/condominios-api.git](https://github.com/miguelnsimoes/condominios-api.git)
    cd condominios-api
    git checkout develop
