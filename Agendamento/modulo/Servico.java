package modulo;

/**
 * Classe que representa um Serviço Odontológico
 */
public class Servico {
    
    private int id;
    private String nome;
    private String descricao;
    private double valor;
    private int duracao; // em minutos
    
    public Servico() {
    }
    
    public Servico(String nome, String descricao, double valor, int duracao) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
        this.duracao = duracao;
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getDescricao() {
        return descricao;
    }
    
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public double getValor() {
        return valor;
    }
    
    public void setValor(double valor) {
        this.valor = valor;
    }
    
    public int getDuracao() {
        return duracao;
    }
    
    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }
    
    @Override
    public String toString() {
        return "[ID: " + id + "] " + nome + " - R$ " + String.format("%.2f", valor) + 
               " (" + duracao + " min)";
    }
}
