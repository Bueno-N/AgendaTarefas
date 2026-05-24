package interface_;

import modulo.Agendamento;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interface que define os contratos para operações com Agendamentos
 */
public interface IAgendamentoService {
    
    /**
     * Cria um novo agendamento
     * @param agendamento o agendamento a ser criado
     * @return o agendamento criado
     */
    Agendamento criar(Agendamento agendamento);
    
    /**
     * Busca um agendamento pelo ID
     * @param id o ID do agendamento
     * @return o agendamento encontrado ou null
     */
    Agendamento buscarPorId(int id);
    
    /**
     * Lista todos os agendamentos
     * @return lista de agendamentos
     */
    List<Agendamento> listarTodos();
    
    /**
     * Atualiza um agendamento existente
     * @param agendamento o agendamento com dados atualizados
     * @return o agendamento atualizado
     */
    Agendamento atualizar(Agendamento agendamento);
    
    /**
     * Deleta um agendamento
     * @param id o ID do agendamento a ser deletado
     * @return true se deletado com sucesso
     */
    boolean deletar(int id);
    
    /**
     * Verifica se um horário está disponível para um doutor
     * @param doutorId o ID do doutor
     * @param dataHora a data e hora desejada
     * @return true se está disponível
     */
    boolean estaDisponivel(int doutorId, LocalDateTime dataHora);
}
