package services;

import modulo.Doutor;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio dos Doutores
 */
public class DoutorService {
    
    private List<Doutor> doutores;
    private int proximoId;
    
    public DoutorService() {
        this.doutores = new ArrayList<>();
        this.proximoId = 1;
        inicializarDoutoresExemplo();
    }
    
    /**
     * Inicializa com alguns doutores exemplo
     */
    private void inicializarDoutoresExemplo() {
        criar(new Doutor("Dr. João Silva", "12345", "Dentista Geral", "1133334444", "joao@clinica.com"));
        criar(new Doutor("Dra. Maria Santos", "54321", "Ortodontia", "1133335555", "maria@clinica.com"));
        criar(new Doutor("Dr. Carlos Oliveira", "67890", "Endodontia", "1133336666", "carlos@clinica.com"));
    }
    
    /**
     * Cria um novo doutor
     */
    public Doutor criar(Doutor doutor) {
        if (doutor == null) {
            throw new IllegalArgumentException("Doutor não pode ser nulo");
        }
        doutor.setId(proximoId++);
        doutores.add(doutor);
        return doutor;
    }
    
    /**
     * Busca um doutor pelo ID
     */
    public Doutor buscarPorId(int id) {
        Optional<Doutor> resultado = doutores.stream()
                .filter(d -> d.getId() == id)
                .findFirst();
        return resultado.orElse(null);
    }
    
    /**
     * Lista todos os doutores
     */
    public List<Doutor> listarTodos() {
        return new ArrayList<>(doutores);
    }
    
    /**
     * Atualiza um doutor existente
     */
    public Doutor atualizar(Doutor doutor) {
        if (doutor == null) {
            throw new IllegalArgumentException("Doutor não pode ser nulo");
        }
        Doutor existente = buscarPorId(doutor.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Doutor com ID " + doutor.getId() + " não encontrado");
        }
        
        existente.setNome(doutor.getNome());
        existente.setCrp(doutor.getCrp());
        existente.setEspecialidade(doutor.getEspecialidade());
        existente.setTelefone(doutor.getTelefone());
        existente.setEmail(doutor.getEmail());
        
        System.out.println("Doutor atualizado com sucesso");
        return existente;
    }
    
    /**
     * Deleta um doutor
     */
    public boolean deletar(int id) {
        boolean removido = doutores.removeIf(d -> d.getId() == id);
        if (removido) {
            System.out.println("Doutor com ID " + id + " deletado com sucesso");
        } else {
            System.out.println("Doutor com ID " + id + " não encontrado");
        }
        return removido;
    }
}
