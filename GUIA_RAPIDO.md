# 🚀 GUIA RÁPIDO DE INÍCIO

## ⚡ Início em 3 Passos

### 1️⃣ Compilar
```bash
cd /workspaces/AgendaTarefas
javac -d bin Agendamento/**/*.java
```

### 2️⃣ Executar
```bash
java -cp bin view.Menu
```

### 3️⃣ Usar o Menu
```
Escolha uma opção:
1 - Cadastrar Usuário
2 - Agendar Horário
3 - Ver Horários Disponíveis
4 - Cadastrar Doutor
5 - Listar Meus Agendamentos
6 - Cancelar Agendamento
0 - Sair
```

---

## 📋 Exemplo Completo de Uso

### Passo 1: Cadastro do Usuário
```
Opção: 1
Nome: Maria Silva
CPF: 123.456.789-00
Telefone: 11988776655
Email: maria@email.com
Endereço: Av. Paulista, 1000

✓ Usuário cadastrado com sucesso! ID: 1
```

### Passo 2: Visualizar Doutores
```
Opção: 3

Doutores disponíveis:
[ID: 1] Dr. João Silva - CRP: 12345 - Especialidade: Dentista Geral
[ID: 2] Dra. Maria Santos - CRP: 54321 - Especialidade: Ortodontia
[ID: 3] Dr. Carlos Oliveira - CRP: 67890 - Especialidade: Endodontia

Selecione o ID do doutor: 1

Horários ocupados para Dr. João Silva:
(nenhum horário ocupado ainda)

✓ Doutor Dr. João Silva tem todos os horários disponíveis!
```

### Passo 3: Agendar Horário
```
Opção: 2

Doutores disponíveis:
[ID: 1] Dr. João Silva - CRP: 12345 - Especialidade: Dentista Geral
[ID: 2] Dra. Maria Santos - CRP: 54321 - Especialidade: Ortodontia
[ID: 3] Dr. Carlos Oliveira - CRP: 67890 - Especialidade: Endodontia

Selecione o ID do doutor: 1

Serviços disponíveis:
[ID: 1] Avaliação - R$ 100.00 (30 min)
[ID: 2] Limpeza - R$ 150.00 (45 min)
[ID: 3] Extração de Dente - R$ 200.00 (30 min)
[ID: 4] Canal - R$ 500.00 (90 min)
[ID: 5] Colocar Aparelho Dentário - R$ 800.00 (60 min)
[ID: 6] Remover Aparelho - R$ 400.00 (45 min)
[ID: 7] Manutenção no Aparelho - R$ 200.00 (30 min)

Selecione o ID do serviço: 2
Digite a data (DD/MM/YYYY): 15/06/2024
Digite a hora (HH:MM): 14:30

✓ Agendamento realizado com sucesso! ID: 1
```

### Passo 4: Listar Meus Agendamentos
```
Opção: 5

========== MEUS AGENDAMENTOS ==========

[ID: 1]
  Doutor: Dr. João Silva
  Serviço: Limpeza
  Data/Hora: 2024-06-15T14:30
  Valor: R$ 150.00
  Status: AGENDADO
```

### Passo 5: Cancelar Agendamento
```
Opção: 6

ID do agendamento: 1

✓ Agendamento cancelado com sucesso!
```

---

## 🎯 Funcionalidades Principais

### ✨ Cadastro de Usuário
- Armazena nome, CPF, telefone, email e endereço
- Usuário é automaticamente "logado" após cadastro
- Necessário para agendar consultas

### ✨ Agendamento de Consultas
1. Selecione o doutor desejado
2. Selecione o serviço desejado
3. Digite data no formato DD/MM/YYYY
4. Digite hora no formato HH:MM
5. Sistema valida disponibilidade automaticamente

### ✨ Visualizar Disponibilidade
- Lista todos os doutores cadastrados
- Mostra horários já ocupados
- Indica quais horários estão livres

### ✨ Cadastro de Doutor
- Adicione novos dentistas ao sistema
- Cada doutor tem sua própria agenda
- Preencha: nome, CRP, especialidade, telefone, email

### ✨ Gerenciamento de Agendamentos
- Veja todos seus agendamentos
- Cancele consultas que não precisar mais
- Veja detalhes: doutor, serviço, data, hora, valor

---

## 🏥 Serviços Disponíveis

| # | Serviço | Valor | Duração |
|---|---------|-------|---------|
| 1 | Avaliação | R$ 100,00 | 30 min |
| 2 | Limpeza | R$ 150,00 | 45 min |
| 3 | Extração de Dente | R$ 200,00 | 30 min |
| 4 | Canal | R$ 500,00 | 90 min |
| 5 | Colocar Aparelho | R$ 800,00 | 60 min |
| 6 | Remover Aparelho | R$ 400,00 | 45 min |
| 7 | Manutenção Aparelho | R$ 200,00 | 30 min |

---

## 👨‍⚕️ Doutores Pré-cadastrados

| ID | Nome | Especialidade | CRP |
|----|------|----------------|-----|
| 1 | Dr. João Silva | Dentista Geral | 12345 |
| 2 | Dra. Maria Santos | Ortodontia | 54321 |
| 3 | Dr. Carlos Oliveira | Endodontia | 67890 |

---

## ⚙️ Requisitos Técnicos

- **Java**: 8 ou superior
- **Sistema Operacional**: Windows, Linux ou macOS
- **Espaço em Disco**: < 10 MB
- **Memória RAM**: 256 MB (mínimo)

---

## 🐛 Troubleshooting

### "ClassNotFoundException: view.Menu"
**Solução**: Verifique se compilou com `-d bin` e execute com `-cp bin`

### "Date format invalid"
**Solução**: Use o formato DD/MM/YYYY para datas (ex: 15/06/2024)

### "Time format invalid"
**Solução**: Use o formato HH:MM para horas (ex: 14:30)

### "Horário não disponível"
**Solução**: O doutor já tem uma consulta agendada neste horário

---

## 📞 Atalhos Úteis

| Ação | Comando |
|------|---------|
| Compilar Rápido | `javac -d bin Agendamento/**/*.java` |
| Executar | `java -cp bin view.Menu` |
| Limpar bin | `rm -rf bin` |
| Ver estrutura | `tree Agendamento/` |
| Contar linhas | `find Agendamento -name "*.java" -exec wc -l {} +` |

---

## 📚 Documentação Completa

Para mais informações, consulte:
- **README.md** - Visão geral do projeto
- **ARQUITETURA.md** - Diagramas e design
- **ESTRUTURA.md** - Detalhes técnicos
- **COMPILACAO.md** - Guia de compilação
- **RESUMO_PROJETO.md** - Resumo completo

---

## 🎓 Aprenda Mais

Este projeto demonstra:
- ✅ Arquitetura MVC
- ✅ Service Layer Pattern
- ✅ Collections Framework Java
- ✅ Interface e Implementação
- ✅ Boas práticas OOP
- ✅ Tratamento de exceções

---

**Boa sorte! Divirta-se com o sistema!** 🎉

Versão: 1.0.0
Última atualização: 2024
