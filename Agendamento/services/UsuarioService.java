package services;

import modulo.Usuario;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Serviço responsável pela lógica de negócio dos Usuários
 */
public class UsuarioService {
    
    private List<Usuario> usuarios;
    private int proximoId;
    
    public UsuarioService() {
        this.usuarios = new ArrayList<>();
        this.proximoId = 1;
    }
    
    /**
     * Cria um novo usuário
     */
    public Usuario criar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        usuario.setId(proximoId++);
        usuarios.add(usuario);
        System.out.println("Usuário criado com sucesso: " + usuario.getNome());
        return usuario;
    }
    
    /**
     * Busca um usuário pelo ID
     */
    public Usuario buscarPorId(int id) {
        Optional<Usuario> resultado = usuarios.stream()
                .filter(u -> u.getId() == id)
                .findFirst();
        return resultado.orElse(null);
    }
    
    /**
     * Busca um usuário pelo CPF
     */
    public Usuario buscarPorCpf(String cpf) {
        Optional<Usuario> resultado = usuarios.stream()
                .filter(u -> u.getCpf().equals(cpf))
                .findFirst();
        return resultado.orElse(null);
    }
    
    /**
     * Lista todos os usuários
     */
    public List<Usuario> listarTodos() {
        return new ArrayList<>(usuarios);
    }
    
    /**
     * Atualiza um usuário existente
     */
    public Usuario atualizar(Usuario usuario) {
        if (usuario == null) {
            throw new IllegalArgumentException("Usuário não pode ser nulo");
        }
        Usuario existente = buscarPorId(usuario.getId());
        if (existente == null) {
            throw new IllegalArgumentException("Usuário com ID " + usuario.getId() + " não encontrado");
        }
        
        existente.setNome(usuario.getNome());
        existente.setCpf(usuario.getCpf());
        existente.setTelefone(usuario.getTelefone());
        existente.setEmail(usuario.getEmail());
        existente.setEndereco(usuario.getEndereco());
        
        System.out.println("Usuário atualizado com sucesso");
        return existente;
    }
    
    /**
     * Deleta um usuário
     */
    public boolean deletar(int id) {
        boolean removido = usuarios.removeIf(u -> u.getId() == id);
        if (removido) {
            System.out.println("Usuário com ID " + id + " deletado com sucesso");
        } else {
            System.out.println("Usuário com ID " + id + " não encontrado");
        }
        return removido;
    }
}
