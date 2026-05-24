# 📁 Estrutura do Projeto - Sistema de Agendamentos

## 🎯 Visão Geral

```
AgendaTarefas/
│
├── 📁 Agendamento/                    # Módulo principal da aplicação
│   │
│   ├── 📁 config/                    # Configurações
│   │   └── ConfiguradorAplicacao.java
│   │
│   ├── 📁 modulo/                    # Entidades (Modelos)
│   │   ├── Usuario.java              # Pacientes
│   │   ├── Doutor.java               # Dentistas
│   │   ├── Servico.java              # Serviços odontológicos
│   │   └── Agendamento.java          # Agendamentos
│   │
│   ├── 📁 interface/                 # Contratos (Interfaces)
│   │   └── IAgendamentoService.java
│   │
│   ├── 📁 services/                  # Lógica de Negócio
│   │   ├── UsuarioService.java
│   │   ├── DoutorService.java
│   │   ├── ServicoService.java
│   │   └── AgendamentoService.java
│   │
│   ├── 📁 view/                      # Apresentação (Interface com Usuário)
│   │   └── Menu.java
│   │
│   └── README.md                     # Documentação do módulo
│
├── 📄 README.md                      # Documentação principal
├── 📄 ARQUITETURA.md                 # Diagramas e design
├── 📄 COMPILACAO.md                  # Guia de compilação
└── 📄 compile.sh                     # Script de compilação

```

## 📊 Detalhamento de Cada Componente

### 🔧 Configuração (config/)

| Arquivo | Responsabilidade |
|---------|-----------------|
| ConfiguradorAplicacao.java | Inicialização e configurações gerais |

### 📦 Modelos (modulo/)

| Classe | Atributos | Responsabilidade |
|--------|-----------|------------------|
| Usuario | id, nome, cpf, telefone, email, endereco | Representa um paciente |
| Doutor | id, nome, crp, especialidade, telefone, email | Representa um dentista |
| Servico | id, nome, descricao, valor, duracao | Representa um serviço odontológico |
| Agendamento | id, usuario, doutor, servico, dataHora, status | Representa uma consulta agendada |

### 🔌 Interfaces (interface/)

| Interface | Métodos |
|-----------|---------|
| IAgendamentoService | criar, buscarPorId, listarTodos, atualizar, deletar, estaDisponivel |

### ⚙️ Serviços (services/)

| Serviço | Métodos Principais |
|---------|-------------------|
| UsuarioService | criar, buscarPorId, buscarPorCpf, listarTodos, atualizar, deletar |
| DoutorService | criar, buscarPorId, listarTodos, atualizar, deletar |
| ServicoService | criar, buscarPorId, listarTodos, atualizar, deletar |
| AgendamentoService | criar, buscarPorId, listarTodos, listarPorDoutor, atualizar, deletar, estaDisponivel, cancelar |

### 🖥️ Interface com Usuário (view/)

| Componente | Função |
|-----------|--------|
| Menu.java | Apresentação CLI, interação com usuário, coordenação de fluxos |

## 🔄 Fluxos de Dados

### Fluxo 1: Cadastro de Usuário
```
Menu.cadastrarUsuario()
  ↓
UsuarioService.criar()
  ↓
Usuario armazenado em memória
  ↓
usuarioLogado atualizado
```

### Fluxo 2: Agendamento de Consulta
```
Menu.agendarHorario()
  ↓
Seleciona Doutor (DoutorService)
  ↓
Seleciona Serviço (ServicoService)
  ↓
Insere Data/Hora
  ↓
AgendamentoService.estaDisponivel()
  ↓
Se disponível: AgendamentoService.criar()
  ↓
Agendamento armazenado
```

### Fluxo 3: Listar Agendamentos
```
Menu.listarMeusAgendamentos()
  ↓
Filtra por usuarioLogado
  ↓
Exibe lista de Agendamentos
  ↓
Mostra detalhes: doutor, serviço, data, valor
```

## 📈 Dependências Entre Componentes

```
Menu (view/)
  ├── UsuarioService
  ├── DoutorService
  ├── ServicoService
  └── AgendamentoService
       ├── IAgendamentoService (interface)
       ├── Agendamento (modulo)
       ├── Usuario (modulo)
       ├── Doutor (modulo)
       └── Servico (modulo)
```

## 🎨 Padrões de Código

### 1. Service Layer
Cada serviço (UsuarioService, etc.) implementa:
- CRUD básico (Create, Read, Update, Delete)
- Validações
- Lógica de negócio específica

### 2. Repository Pattern
Cada service mantém uma `List<T>` em memória:
```java
private List<Usuario> usuarios;
private int proximoId;
```

### 3. Interface Segregation
Contratos definidos em interfaces:
```java
public interface IAgendamentoService { ... }
```

## 📝 Convenções de Nomenclatura

### Pacotes
- `config` - Configurações
- `modulo` - Modelos de dados
- `interface_` - Interfaces (note o underscore)
- `services` - Lógica de negócio
- `view` - Apresentação

### Classes
- `Usuario`, `Doutor`, `Servico` - Entidades (substantivos)
- `UsuarioService`, `DoutorService` - Serviços
- `IAgendamentoService` - Interfaces (prefixo I)

### Métodos
- `criar`, `buscar`, `listar`, `atualizar`, `deletar` - CRUD
- `estaDisponivel`, `estaOcupado` - Validações (prefixo esta/é)

## 🚀 Pontos de Entrada

**Entrada Principal**: `view.Menu.main(String[] args)`

1. Cria instância de Menu
2. Inicializa Services
3. Carrega dados de exemplo
4. Exibe menu interativo

## 📊 Capacidades do Sistema

✅ **Implementado**
- [x] CRUD de Usuários
- [x] CRUD de Doutores
- [x] CRUD de Serviços
- [x] CRUD de Agendamentos
- [x] Verificação de disponibilidade
- [x] Cancelamento de agendamentos
- [x] Filtragem por doutor
- [x] Filtragem por usuário

⏳ **Futuro**
- [ ] Persistência em banco de dados
- [ ] Autenticação com senha
- [ ] GUI (Interface Gráfica)
- [ ] Relatórios
- [ ] Notificações por email

## 📞 Como Usar Cada Módulo

### Para Criar um Novo Usuário
```java
UsuarioService service = new UsuarioService();
Usuario user = new Usuario("João", "123.456.789-00", "1133334444", "joao@mail.com", "Rua A");
service.criar(user);
```

### Para Agendar uma Consulta
```java
Agendamento agendamento = new Agendamento(usuario, doutor, servico, dataHora);
agendamentoService.criar(agendamento);
```

### Para Verificar Disponibilidade
```java
boolean disponivel = agendamentoService.estaDisponivel(doutorId, dataHora);
```

---

**Documento criado**: 2024
**Versão**: 1.0.0
**Status**: ✅ Funcional
