#!/bin/bash
# Script de compilação do Sistema de Agendamentos

echo "================================"
echo "Compilando Sistema de Agendamentos"
echo "================================"

# Cria diretório para classes compiladas
mkdir -p bin

# Compila todos os arquivos Java
echo "Compilando arquivos Java..."

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

if [ $? -eq 0 ]; then
    echo "✓ Compilação concluída com sucesso!"
    echo ""
    echo "Para executar a aplicação, use:"
    echo "  java -cp bin view.Menu"
else
    echo "✗ Erro durante a compilação!"
    exit 1
fi
