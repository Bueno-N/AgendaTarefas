package modulo;

import java.time.LocalDateTime;

/**
 * Classe que representa um Agendamento
 */
public class Agendamento {
    
    private int id;
    private Usuario usuario;
    private Doutor doutor;
    private Servico servico;
    private LocalDateTime dataHora;
    private String status;
    
    public Agendamento() {
    }
    
    public Agendamento(Usuario usuario, Doutor doutor, Servico servico, LocalDateTime dataHora) {
        this.usuario = usuario;
        this.doutor = doutor;
        this.servico = servico;
        this.dataHora = dataHora;
        this.status = "AGENDADO";
    }
    
    // Getters e Setters
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Doutor getDoutor() {
        return doutor;
    }
    
    public void setDoutor(Doutor doutor) {
        this.doutor = doutor;
    }
    
    public Servico getServico() {
        return servico;
    }
    
    public void setServico(Servico servico) {
        this.servico = servico;
    }
    
    public LocalDateTime getDataHora() {
        return dataHora;
    }
    
    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    @Override
    public String toString() {
        return "Agendamento{" +
                "id=" + id +
                ", usuario=" + (usuario != null ? usuario.getNome() : "N/A") +
                ", doutor=" + (doutor != null ? doutor.getNome() : "N/A") +
                ", servico=" + (servico != null ? servico.getNome() : "N/A") +
                ", dataHora=" + dataHora +
                ", status='" + status + '\'' +
                '}';
    }
}
