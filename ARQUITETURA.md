# Diagrama de Classes e Relações

## Diagrama de Entidades

```
┌─────────────────┐
│    Usuario      │
├─────────────────┤
│ - id: int       │
│ - nome: String  │
│ - cpf: String   │
│ - telefone      │
│ - email         │
│ - endereco      │
└────────┬────────┘
         │
         │ 1
         │
         ├─────────────┐
         │             │
         ▼             ▼
   ┌──────────────┐  ┌──────────────┐
   │ Agendamento  │  │   Doutor     │
   ├──────────────┤  ├──────────────┤
   │ - id: int    │  │ - id: int    │
   │ - dataHora   │  │ - nome       │
   │ - status     │  │ - crp        │
   │ - usuario *  │  │ - especial.  │
   │ - doutor *   │  │ - telefone   │
   │ - servico *  │  │ - email      │
   └──────────────┘  └──────────────┘
         ▲
         │ *
         │
         └─────────────────┐
                           │
                    ┌──────┴──────┐
                    ▼             │
              ┌──────────────┐    │
              │   Servico    │    │
              ├──────────────┤    │
              │ - id: int    │    │
              │ - nome       │    │
              │ - descricao  │◄───┘
              │ - valor      │
              │ - duracao    │
              └──────────────┘
```

## Relacionamentos

### Usuario → Agendamento
- **Tipo**: 1 para N
- **Descrição**: Um usuário pode ter múltiplos agendamentos

### Doutor → Agendamento
- **Tipo**: 1 para N
- **Descrição**: Um doutor pode ter múltiplos agendamentos (agenda pessoal)

### Servico → Agendamento
- **Tipo**: 1 para N
- **Descrição**: Um serviço pode estar em múltiplos agendamentos

## Arquitetura MVC

```
┌──────────────────────────────────────────┐
│          VIEW (view)                     │
│                                          │
│         Menu.java (CLI)                  │
└────────────────┬─────────────────────────┘
                 │
                 │ entrada/saída
                 │
┌────────────────▼─────────────────────────┐
│       CONTROLLER (services)              │
│                                          │
│  ├─ AgendamentoService                   │
│  ├─ UsuarioService                       │
│  ├─ DoutorService                        │
│  └─ ServicoService                       │
└────────────────┬─────────────────────────┘
                 │
                 │ gerenciamento
                 │
┌────────────────▼─────────────────────────┐
│       MODEL (modulo)                     │
│                                          │
│  ├─ Usuario                              │
│  ├─ Doutor                               │
│  ├─ Servico                              │
│  └─ Agendamento                          │
│                                          │
│  ├─ IAgendamentoService (interface)      │
│  └─ ConfiguradorAplicacao                │
└──────────────────────────────────────────┘
```

## Fluxo de Dados - Agendamento

```
┌──────────────┐
│  Usuário     │
│  digita ops  │
└────────┬─────┘
         │
         ▼
    ┌─────────┐
    │  Menu   │
    └────┬────┘
         │ seleciona doutor
         ▼
  ┌──────────────┐
  │ DoutorService│
  │   buscarPorId│
  └──────┬───────┘
         │
         ▼
    ┌────────┐
    │ Doutor │
    └────┬───┘
         │ seleciona serviço
         ▼
  ┌──────────────┐
  │ServicoService│
  │  buscarPorId │
  └──────┬───────┘
         │
         ▼
   ┌──────────┐
   │ Servico  │
   └────┬─────┘
        │ seleciona data/hora
        ▼
  ┌───────────────────┐
  │AgendamentoService │
  │  estaDisponivel   │
  └────────┬──────────┘
           │
        ┌──┴──┐
        │     │
        ▼     ▼
   SIM    NÃO
   │       │
   ▼       ▼
 ┌───┐  ┌────────┐
 │✓  │  │⚠️ Erro│
 └───┘  └────────┘
   │
   ▼
┌──────────────────┐
│  criar()         │
│  Agendamento new │
└────────┬─────────┘
         │
         ▼
   ┌───────────────┐
   │ Confirmação ✓ │
   └───────────────┘
```

## Padrões de Design Utilizados

### 1. **MVC Pattern**
- Separação entre lógica, apresentação e dados

### 2. **Service Layer Pattern**
- Services encapsulam a lógica de negócio
- Controllers utilizam Services

### 3. **Repository Pattern** (implementado com List)
- Gerenciamento de coleções de dados
- Métodos CRUD básicos

### 4. **Strategy Pattern** (potencial)
- Interface IAgendamentoService define contrato

## Casos de Uso

```
┌────────────────────────────────────┐
│      SISTEMA DE AGENDAMENTOS       │
│                                    │
│  ┌─────────────────────────────┐   │
│  │    USUÁRIO/PACIENTE         │   │
│  ├─────────────────────────────┤   │
│  │  • Cadastrar                │   │
│  │  • Agendar Horário          │   │
│  │  • Ver Horários Disp.       │   │
│  │  • Listar Meus Agendamentos │   │
│  │  • Cancelar Agendamento     │   │
│  └─────────────────────────────┘   │
│                                    │
│  ┌─────────────────────────────┐   │
│  │   ADMINISTRADOR             │   │
│  ├─────────────────────────────┤   │
│  │  • Cadastrar Doutores       │   │
│  │  • Gerenciar Serviços       │   │
│  │  • Ver Todas as Agendas     │   │
│  └─────────────────────────────┘   │
│                                    │
└────────────────────────────────────┘
```

## Fluxo Principal da Aplicação

```
START
  │
  ▼
┌─────────────────────────────┐
│   MENU PRINCIPAL            │
│                             │
│ 1. Cadastrar Usuário        │
│ 2. Agendar Horário          │
│ 3. Ver Horários Disponíveis │
│ 4. Cadastrar Doutor         │
│ 5. Listar Meus Agendamentos │
│ 6. Cancelar Agendamento     │
│ 0. Sair                     │
└──────┬──────────────────────┘
       │
       ├──1──► Cadastro ─┐
       │                 │
       ├──2──► Agendamento
       │                 │
       ├──3──► Visualizar
       │                 │
       ├──4──► Cadastro Doutor
       │                 │
       ├──5──► Listar    │
       │                 │
       ├──6──► Cancelar  │
       │                 │
       │<────────────────┘
       │
       ├──0──► EXIT
       │
       ▼
     END
```

---

**Documentação Técnica v1.0**
