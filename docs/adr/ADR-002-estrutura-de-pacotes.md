# ADR-002 — Estrutura de pacotes por feature

- **Data:** 2026-03-21
- **Status:** Aceito

## Contexto

Com múltiplas entidades no sistema (Bloco, Apartamento, Morador, Encomenda, Pagamento, Reserva, Ocorrência), foi necessário definir uma estratégia de organização dos pacotes Java que facilitasse a manutenção, o trabalho paralelo entre membros do time e a localização rápida de arquivos relacionados a uma mesma funcionalidade.

## Decisão

Adotamos organização **por feature (domínio)**, não por camada técnica.

Cada funcionalidade agrupa seus próprios arquivos em um único pacote:
```
com.condominios.api/
├── bloco/
│   ├── Bloco.java
│   ├── BlocoRepository.java
│   ├── BlocoService.java
│   └── BlocoController.java
├── morador/
│   ├── Morador.java
│   ├── MoradorRepository.java
│   ├── MoradorService.java
│   └── MoradorController.java
└── ...
```

## Alternativas consideradas

| Alternativa | Motivo da rejeição |
|---|---|
| Por camada técnica (controllers/, services/, repositories/) | Dificulta localizar todos os arquivos de uma funcionalidade, que ficam espalhados em pastas diferentes |

## Consequências

- Fácil localização de todos os arquivos relacionados a uma funcionalidade
- Facilita o trabalho paralelo sem conflitos entre membros do time
- Escala bem conforme o projeto cresce com novas entidades
- Padrão amplamente adotado em projetos Spring Boot profissionais