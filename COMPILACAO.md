# Guia de Compilação e Execução

## ⚙️ Pré-requisitos

- Java 8 ou superior instalado
- Terminal/CMD com acesso ao diretório do projeto

## 📝 Passo a Passo

### 1️⃣ Verificar Java Instalado

```bash
java -version
javac -version
```

### 2️⃣ Navegar para o Diretório do Projeto

```bash
cd /workspaces/AgendaTarefas
```

### 3️⃣ Compilar Manualmente

#### Windows (CMD):
```cmd
mkdir bin

javac -d bin ^
    Agendamento\config\ConfiguradorAplicacao.java ^
    Agendamento\modulo\Usuario.java ^
    Agendamento\modulo\Doutor.java ^
    Agendamento\modulo\Servico.java ^
    Agendamento\modulo\Agendamento.java ^
    Agendamento\interface\IAgendamentoService.java ^
    Agendamento\services\UsuarioService.java ^
    Agendamento\services\DoutorService.java ^
    Agendamento\services\ServicoService.java ^
    Agendamento\services\AgendamentoService.java ^
    Agendamento\view\Menu.java
```

#### Linux/Mac (Bash):
```bash
mkdir -p bin

javac -d bin \
    Agendamento/config/ConfiguradorAplicacao.java \
    Agendamento/modulo/Usuario.java \
    Agendamento/modulo/Doutor.java \
    Agendamento/modulo/Servico.java \
    Agendamento/modulo/Agendamento.java \
    Agendamento/interface/IAgendamentoService.java \
    Agendamento/services/UsuarioService.java \
    Agendamento/services/DoutorService.java \
    Agendamento/services/ServicoService.java \
    Agendamento/services/AgendamentoService.java \
    Agendamento/view/Menu.java
```

#### Com Script (Linux/Mac):
```bash
chmod +x compile.sh
./compile.sh
```

### 4️⃣ Executar a Aplicação

#### Windows:
```cmd
java -cp bin view.Menu
```

#### Linux/Mac:
```bash
java -cp bin view.Menu
```

## 📊 Resultado Esperado

Ao executar, você deverá ver:

```
======================================
       CLÍNICA ODONTOLÓGICA - MENU
======================================
1. Cadastrar Usuário
2. Agendar Horário
3. Ver Horários Disponíveis
4. Cadastrar Doutor
5. Listar Meus Agendamentos
6. Cancelar Agendamento
0. Sair
Escolha uma opção:
```

## 🧪 Teste Passo a Passo

### Teste 1: Cadastro de Usuário

1. Pressione `1` para cadastrar usuário
2. Preencha os dados:
   - Nome: João Silva
   - CPF: 123.456.789-00
   - Telefone: 11999999999
   - Email: joao@email.com
   - Endereço: Rua A, 123

### Teste 2: Ver Doutores Disponíveis

1. Pressione `3`
2. Selecione um doutor (1, 2 ou 3)
3. Veja a lista de horários ocupados

### Teste 3: Agendar Horário

1. Pressione `2`
2. Selecione um doutor
3. Selecione um serviço
4. Digite a data: 10/06/2024
5. Digite a hora: 10:00

### Teste 4: Listar Agendamentos

1. Pressione `5`
2. Visualize seus agendamentos

## ⚠️ Possíveis Problemas

### Erro: "javac: command not found"
**Solução**: Instalar Java Development Kit (JDK)

### Erro: "Cannot find symbol"
**Solução**: Verificar se todos os arquivos estão nos diretórios corretos

### Erro: "FileNotFoundException"
**Solução**: Navegar para o diretório correto do projeto

## 🔄 Limpeza

Para remover arquivos compilados:

#### Windows:
```cmd
rmdir /s bin
```

#### Linux/Mac:
```bash
rm -rf bin
```

## 📦 Distribuição

Para compartilhar a aplicação compilada:

1. Copiar a pasta `bin` inteira
2. Distribuir junto com um script de execução
3. Incluir README.md com instruções

## 🐛 Debug

Se encontrar erros, verifique:

1. **Nomes de pacotes**: Devem corresponder aos diretórios
2. **Nomes de arquivos**: Case-sensitive em Linux/Mac
3. **Classpath**: Deve apontar para `bin`
4. **Versão Java**: Compatibilidade de sintaxe

## 📚 Estrutura de Diretórios Final

```
AgendaTarefas/
├── Agendamento/
│   ├── config/
│   ├── modulo/
│   ├── interface/
│   ├── services/
│   ├── view/
│   └── README.md
├── bin/                    (criado após compilação)
│   ├── view/
│   ├── config/
│   ├── modulo/
│   ├── interface_/
│   └── services/
├── compile.sh
├── ARQUITETURA.md
├── COMPILACAO.md
└── README.md
```

---

**Última atualização**: 2024
**Versão**: 1.0.0
