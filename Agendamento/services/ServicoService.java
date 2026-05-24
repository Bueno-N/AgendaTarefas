package services;

import modulo.Servico;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio dos Serviços Odontológicos
 */
public class ServicoService {
    
    private List<Servico> servicos;
    private int proximoId;
    
    public ServicoService() {
        this.servicos = new ArrayList<>();
        this.proximoId = 1;
        inicializarServicos();
    }
    
    /**
     * Inicializa com os serviços odontológicos padrão
     */
    private void inicializarServicos() {
        criar(new Servico("Avaliação", "Avaliação completa e diagnóstico", 100.00, 30));
        criar(new Servico("Limpeza", "Limpeza profissional dos dentes", 150.00, 45));
        criar(new Servico("Extração de Dente", "Extração segura do dente", 200.00, 30));
        criar(new Servico("Canal", "Tratamento de canal endodôntico", 500.00, 90));
        criar(new Servico("Colocar Aparelho Dentário", "Instalação de aparelho ortodôntico", 800.00, 60));
        criar(new Servico("Remover Aparelho", "Remoção de aparelho ortodôntico", 400.00, 45));
        criar(new Servico("Manutenção no Aparelho", "Ajustes e manutenção do aparelho", 200.00, 30));
    }
    
    /**
     * Cria um novo serviço
     */
    public Servico criar(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo");
        }
        servico.setId(proximoId++);
        servicos.add(servico);
        return servico;
    }
    
    /**
     * Busca um serviço pelo ID
     */
    public Servico buscarPorId(int id) {
        Optional<Servico> resultado = servicos.stream()
                .filter(s -> s.getId() == id)
                .findFirst();
        return resultado.orElse(null);
    }
    
    /**
     * Lista todos os serviços
     */
    public List<Servico> listarTodos() {
        return new ArrayList<>(servicos);
    }
    
    /**
     * Atualiza um serviço existente
     */
    public Servico atualizar(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo");
        }
        Servico existente = buscarPorId(servico.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Serviço com ID " + servico.getId() + " não encontrado");
        }
        
        existente.setNome(servico.getNome());
        existente.setDescricao(servico.getDescricao());
        existente.setValor(servico.getValor());
        existente.setDuracao(servico.getDuracao());
        
        System.out.println("Serviço atualizado com sucesso");
        return existente;
    }
    
    /**
     * Deleta um serviço
     */
    public boolean deletar(int id) {
        boolean removido = servicos.removeIf(s -> s.getId() == id);
        if (removido) {
            System.out.println("Serviço com ID " + id + " deletado com sucesso");
        } else {
            System.out.println("Serviço com ID " + id + " não encontrado");
        }
        return removido;
    }
}
