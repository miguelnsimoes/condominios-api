# Levantamento de Requisitos — Sistema de Gerenciamento de Condomínio

## 1. Objetivo do Sistema

Auxiliar na administração de um condomínio, permitindo o gerenciamento de blocos, apartamentos, moradores, encomendas, pagamentos, reservas de áreas comuns e ocorrências.

Stack definida: Java com Spring Boot e PostgreSQL.

---

## 2. Requisitos Funcionais

### RF01 — Cadastro de Blocos
Permitir cadastrar blocos do condomínio com identificador único e nome ou código.

Exemplos: Bloco A, Bloco B, Bloco C.

### RF02 — Cadastro de Apartamentos
Permitir cadastrar apartamentos vinculados a um bloco, contendo identificador, número e bloco ao qual pertence.

### RF03 — Identificação de Apartamento
O apartamento deve ser identificado pela combinação **Bloco + Número**. Não podem existir dois apartamentos com o mesmo número no mesmo bloco.

| Válido | Inválido |
|---|---|
| Bloco A - Ap 101 e Bloco B - Ap 101 | Bloco A - Ap 101 e Bloco A - Ap 101 |

### RF04 — Cadastro de Moradores
Permitir cadastrar moradores vinculados a um apartamento. Campos obrigatórios: nome, CPF, telefone e apartamento.

### RF05 — Gerenciamento de Moradores
Permitir cadastrar, atualizar e remover moradores, além de listar moradores por apartamento.

### RF06 — Registro de Encomendas
Permitir registrar encomendas recebidas na portaria. Campos: descrição, data e hora de chegada, apartamento destinatário e funcionário que recebeu.

### RF07 — Controle de Retirada de Encomendas
Permitir registrar a retirada da encomenda, armazenando data da retirada e status.

| Status |
|---|
| `PENDENTE` |
| `RETIRADA` |

### RF08 — Cadastro de Áreas Comuns
Permitir cadastrar áreas comuns do condomínio. Exemplos: salão de festas, churrasqueira, quadra, sala de reuniões.

### RF09 — Reserva de Áreas Comuns
Permitir que moradores realizem reservas de áreas comuns informando área, data, horário e morador responsável.

### RF10 — Controle de Conflito de Reservas
O sistema não deve permitir reservas duplicadas da mesma área no mesmo horário.

### RF11 — Registro de Ocorrências
Permitir registrar ocorrências no condomínio. Campos: descrição, data, morador relacionado e status.

| Status |
|---|
| `ABERTA` |
| `EM_ANALISE` |
| `RESOLVIDA` |

### RF12 — Controle de Pagamentos
Permitir registrar pagamentos de taxas condominiais. Campos: morador responsável, valor, data de pagamento e status.

| Status |
|---|
| `PENDENTE` |
| `PAGO` |
| `ATRASADO` |

### RF13 — Relatórios
Permitir gerar relatórios de encomendas pendentes, pagamentos realizados, reservas de áreas comuns e ocorrências registradas.

---

## 3. Requisitos Não Funcionais

| Código | Descrição |
|---|---|
| RNF01 | Interface web acessível por navegador, desenvolvida em React |
| RNF02 | Dados armazenados em banco relacional PostgreSQL |
| RNF03 | Autenticação de usuários para acesso às funcionalidades administrativas |
| RNF04 | Garantir que não existam apartamentos duplicados no mesmo bloco, que moradores estejam sempre vinculados a um apartamento e que encomendas estejam vinculadas a um apartamento existente |

---

## 4. Entidades do Sistema

`Bloco` `Apartamento` `Morador` `Encomenda` `Pagamento` `ReservaArea` `Ocorrencia`

---

## 5. Benefícios do Sistema

- Organizar o cadastro de moradores
- Controlar encomendas recebidas
- Gerenciar reservas de áreas comuns
- Registrar ocorrências do condomínio
- Acompanhar pagamentos de taxas