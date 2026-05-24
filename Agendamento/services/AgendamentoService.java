package services;

import interface_.IAgendamentoService;
import modulo.Agendamento;
import modulo.Doutor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serviço responsável pela lógica de negócio dos Agendamentos
 */
public class AgendamentoService implements IAgendamentoService {
    
    private List<Agendamento> agendamentos;
    private int proximoId;
    
    public AgendamentoService() {
        this.agendamentos = new ArrayList<>();
        this.proximoId = 1;
    }
    
    @Override
    public Agendamento criar(Agendamento agendamento) {
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não pode ser nulo");
        }
        
        // Verifica se o horário já está ocupado para este doutor
        if (estaOcupado(agendamento.getDoutor().getId(), agendamento.getDataHora())) {
            throw new IllegalArgumentException("Este horário já está ocupado para este doutor");
        }
        
        agendamento.setId(proximoId++);
        agendamentos.add(agendamento);
        System.out.println("Agendamento criado com sucesso!");
        return agendamento;
    }
    
    @Override
    public Agendamento buscarPorId(int id) {
        Optional<Agendamento> resultado = agendamentos.stream()
                .filter(a -> a.getId() == id)
                .findFirst();
        return resultado.orElse(null);
    }
    
    @Override
    public List<Agendamento> listarTodos() {
        return new ArrayList<>(agendamentos);
    }
    
    /**
     * Lista agendamentos por doutor
     */
    public List<Agendamento> listarPorDoutor(int doutorId) {
        return agendamentos.stream()
                .filter(a -> a.getDoutor() != null && a.getDoutor().getId() == doutorId)
                .collect(Collectors.toList());
    }
    
    /**
     * Verifica se um horário está disponível para um doutor
     */
    public boolean estaDisponivel(int doutorId, LocalDateTime dataHora) {
        return !estaOcupado(doutorId, dataHora);
    }
    
    /**
     * Verifica se um horário está ocupado para um doutor
     */
    private boolean estaOcupado(int doutorId, LocalDateTime dataHora) {
        return agendamentos.stream()
                .anyMatch(a -> a.getDoutor() != null && 
                             a.getDoutor().getId() == doutorId &&
                             a.getDataHora().equals(dataHora) &&
                             !a.getStatus().equals("CANCELADO"));
    }
    
    @Override
    public Agendamento atualizar(Agendamento agendamento) {
        if (agendamento == null) {
            throw new IllegalArgumentException("Agendamento não pode ser nulo");
        }
        Agendamento existente = buscarPorId(agendamento.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Agendamento com ID " + agendamento.getId() + " não encontrado");
        }
        
        existente.setUsuario(agendamento.getUsuario());
        existente.setDoutor(agendamento.getDoutor());
        existente.setServico(agendamento.getServico());
        existente.setDataHora(agendamento.getDataHora());
        existente.setStatus(agendamento.getStatus());
        
        System.out.println("Agendamento atualizado com sucesso");
        return existente;
    }
    
    @Override
    public boolean deletar(int id) {
        boolean removido = agendamentos.removeIf(a -> a.getId() == id);
        if (removido) {
            System.out.println("Agendamento com ID " + id + " deletado com sucesso");
        } else {
            System.out.println("Agendamento com ID " + id + " não encontrado");
        }
        return removido;
    }
    
    /**
     * Cancela um agendamento
     */
    public void cancelar(int id) {
        Agendamento agendamento = buscarPorId(id);
        if (agendamento != null) {
            agendamento.setStatus("CANCELADO");
            System.out.println("Agendamento cancelado com sucesso");
        }
    }
}
