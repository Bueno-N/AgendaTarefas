# Clínica Odontológica - Sistema de Agendamentos

## 📋 Descrição
Sistema de agendamento de consultas para uma clínica odontológica desenvolvido em Java. Permite o cadastro de usuários (pacientes), doutores (dentistas) e agendamento de serviços odontológicos.

## 🏗️ Estrutura do Projeto

```
Agendamento/
├── config/
│   └── ConfiguradorAplicacao.java       # Configurações da aplicação
├── modulo/
│   ├── Agendamento.java                 # Entidade de Agendamento
│   ├── Usuario.java                     # Entidade de Usuário (Paciente)
│   ├── Doutor.java                      # Entidade de Doutor
│   └── Servico.java                     # Entidade de Serviço Odontológico
├── interface/
│   └── IAgendamentoService.java         # Interface de Agendamentos
├── services/
│   ├── AgendamentoService.java          # Lógica de agendamentos
│   ├── UsuarioService.java              # Lógica de usuários
│   ├── DoutorService.java               # Lógica de doutores
│   └── ServicoService.java              # Lógica de serviços
└── view/
    └── Menu.java                        # Interface com usuário (CLI)
```

## 🎯 Funcionalidades Principais

### 1. **Cadastrar Usuário**
   - Registro de novos pacientes
   - Armazenamento de informações: nome, CPF, telefone, email, endereço
   - Autenticação automática após cadastro

### 2. **Agendar Horário**
   - Seleção de doutor disponível
   - Seleção de serviço odontológico
   - Escolha de data e hora
   - Validação de conflitos de horário

### 3. **Ver Horários Disponíveis**
   - Visualização de horários ocupados por doutor
   - Verificação de disponibilidade

### 4. **Cadastrar Doutor**
   - Registro de novos dentistas
   - Informações: nome, CRP, especialidade, telefone, email
   - Cada doutor possui sua própria agenda

### 5. **Listar Agendamentos**
   - Visualização de todos os agendamentos do usuário
   - Detalhes: doutor, serviço, data/hora, valor

### 6. **Cancelar Agendamento**
   - Cancelamento de consultas agendadas
   - Validação de permissão do usuário

## 🏥 Serviços Odontológicos Disponíveis

| Serviço | Duração | Valor |
|---------|---------|-------|
| Avaliação | 30 min | R$ 100,00 |
| Limpeza | 45 min | R$ 150,00 |
| Extração de Dente | 30 min | R$ 200,00 |
| Canal | 90 min | R$ 500,00 |
| Colocar Aparelho Dentário | 60 min | R$ 800,00 |
| Remover Aparelho | 45 min | R$ 400,00 |
| Manutenção no Aparelho | 30 min | R$ 200,00 |

## 👨‍⚕️ Doutores Pré-configurados

1. **Dr. João Silva** - Dentista Geral (CRP: 12345)
2. **Dra. Maria Santos** - Ortodontia (CRP: 54321)
3. **Dr. Carlos Oliveira** - Endodontia (CRP: 67890)

## 🚀 Como Executar

### Compilação
```bash
javac view/Menu.java modulo/*.java services/*.java interface/*.java config/*.java
```

### Execução
```bash
java view.Menu
```

## 💡 Fluxo de Uso

1. **Primeiro acesso**: Cadastre-se como usuário
2. **Visualizar disponibilidade**: Veja horários livres com doutores
3. **Agendar**: Escolha doutor, serviço, data e hora
4. **Gerenciar**: Visualize ou cancele seus agendamentos

## 📝 Exemplo de Uso

```
1. Selecione "Cadastrar Usuário"
2. Preencha os dados: nome, CPF, telefone, etc.
3. Selecione "Agendar Horário"
4. Escolha um doutor na lista
5. Selecione um serviço desejado
6. Insira a data (DD/MM/YYYY) e hora (HH:MM)
7. Confirmação do agendamento
```

## 🔧 Tecnologias Utilizadas

- **Linguagem**: Java 8+
- **Paradigma**: Orientado a Objetos
- **Padrões**: MVC (Model-View-Controller), Service Layer
- **Interface**: Command Line Interface (CLI)

## 📦 Pacotes

- `modulo`: Entidades do domínio
- `services`: Lógica de negócio
- `interface_`: Contratos e interfaces
- `view`: Apresentação e interação com usuário
- `config`: Configurações da aplicação

## ✅ Validações Implementadas

- Verificação de horários conflitantes
- Validação de entrada de usuário (datas, horas)
- Controle de permissão (apenas o próprio usuário pode cancelar seus agendamentos)
- Verificação de entidades nulas

## 🔐 Segurança

- Cada usuário só pode ver seus próprios agendamentos
- Apenas o usuário que agendou pode cancelar sua consulta
- Validação de dados na entrada

## 📈 Melhorias Futuras

- Integração com banco de dados
- Interface gráfica (GUI)
- Sistema de autenticação com senha
- Envio de confirmações por email/SMS
- Relatórios de agendamentos
- Sistema de avaliação de doutores
- Histórico de atendimentos

## 👥 Autor

Desenvolvido como sistema de agendamento para clínica odontológica.

---

**Versão**: 1.0.0
