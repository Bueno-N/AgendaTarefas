package config;

/**
 * Classe responsável pela configuração geral da aplicação
 */
public class ConfiguradorAplicacao {
    
    private static final String VERSAO = "1.0.0";
    private static final String NOME_APLICACAO = "Agenda de Tarefas";
    
    public ConfiguradorAplicacao() {
    }
    
    /**
     * Inicializa as configurações da aplicação
     */
    public void inicializar() {
        System.out.println("Inicializando " + NOME_APLICACAO + " v" + VERSAO);
    }
    
    public String getVersao() {
        return VERSAO;
    }
    
    public String getNomeAplicacao() {
        return NOME_APLICACAO;
    }
}
