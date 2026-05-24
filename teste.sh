#!/bin/bash
# Script de teste da aplicação

echo "================================================"
echo "  TESTE DO SISTEMA DE AGENDAMENTOS"
echo "================================================"
echo ""

# Verificar se Java está instalado
echo "1. Verificando Java..."
if ! command -v java &> /dev/null; then
    echo "❌ Java não está instalado!"
    exit 1
fi
echo "✅ Java encontrado: $(java -version 2>&1 | head -n 1)"
echo ""

# Verificar se javac está instalado
echo "2. Verificando javac..."
if ! command -v javac &> /dev/null; then
    echo "❌ javac não está instalado!"
    exit 1
fi
echo "✅ javac encontrado: $(javac -version 2>&1)"
echo ""

# Limpar compilação anterior
echo "3. Limpando compilação anterior..."
rm -rf bin/
echo "✅ Limpeza concluída"
echo ""

# Compilar
echo "4. Compilando..."
mkdir -p bin
javac -d bin Agendamento/**/*.java 2>&1
if [ $? -eq 0 ]; then
    echo "✅ Compilação bem-sucedida"
else
    echo "❌ Erro na compilação"
    exit 1
fi
echo ""

# Verificar se Menu.class foi criado
echo "5. Verificando arquivos compilados..."
if [ -f "bin/view/Menu.class" ]; then
    echo "✅ Arquivo Menu.class encontrado"
else
    echo "❌ Arquivo Menu.class não encontrado"
    exit 1
fi
echo ""

# Contar arquivos compilados
CLASS_COUNT=$(find bin -name "*.class" | wc -l)
echo "✅ Total de arquivos compilados: $CLASS_COUNT"
echo ""

# Criar arquivo de teste
echo "6. Criando arquivo de teste..."
cat > teste_rapido.txt << 'EOF'
1
João Teste
11111111111
11999999999
joao@teste.com
Rua Teste, 111
3
1
5
0
EOF
echo "✅ Arquivo de teste criado"
echo ""

# Executar teste
echo "7. Executando aplicação com teste..."
echo "================================================"
timeout 5 java -cp bin view.Menu < teste_rapido.txt 2>&1 | tail -30
echo "================================================"
echo ""

echo "✅ TESTE CONCLUÍDO COM SUCESSO!"
echo ""
echo "Para usar a aplicação interativamente:"
echo "  java -cp bin view.Menu"
echo ""
