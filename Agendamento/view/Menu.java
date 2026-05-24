package view;

import services.*;
import modulo.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * Classe responsável pela interface de linha de comando
 */
public class Menu {
    
    private UsuarioService usuarioService;
    private DoutorService doutorService;
    private ServicoService servicoService;
    private AgendamentoService agendamentoService;
    private Scanner scanner;
    private Usuario usuarioLogado;
    
    public Menu() {
        this.usuarioService = new UsuarioService();
        this.doutorService = new DoutorService();
        this.servicoService = new ServicoService();
        this.agendamentoService = new AgendamentoService();
        this.scanner = new Scanner(System.in);
        this.usuarioLogado = null;
    }
    
    /**
     * Exibe o menu principal
     */
    public void exibirMenuPrincipal() {
        boolean executando = true;
        
        while (executando) {
            System.out.println("\n======================================");
            System.out.println("       CLÍNICA ODONTOLÓGICA - MENU");
            System.out.println("======================================");
            System.out.println("1. Cadastrar Usuário");
            System.out.println("2. Agendar Horário");
            System.out.println("3. Ver Horários Disponíveis");
            System.out.println("4. Cadastrar Doutor");
            System.out.println("5. Listar Meus Agendamentos");
            System.out.println("6. Cancelar Agendamento");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerInteiro();
            
            switch (opcao) {
                case 1:
                    cadastrarUsuario();
                    break;
                case 2:
                    agendarHorario();
                    break;
                case 3:
                    verHorariosDisponiveis();
                    break;
                case 4:
                    cadastrarDoutor();
                    break;
                case 5:
                    listarMeusAgendamentos();
                    break;
                case 6:
                    cancelarAgendamento();
                    break;
                case 0:
                    executando = false;
                    System.out.println("\nEncerrando aplicação...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }
    
    /**
     * Cadastra um novo usuário
     */
    private void cadastrarUsuario() {
        System.out.println("\n========== CADASTRAR USUÁRIO ==========");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        
        Usuario usuario = new Usuario(nome, cpf, telefone, email, endereco);
        usuarioService.criar(usuario);
        usuarioLogado = usuario;
        System.out.println("Usuário cadastrado com sucesso! ID: " + usuario.getId());
    }
    
    /**
     * Agenda um novo horário para o usuário
     */
    private void agendarHorario() {
        if (usuarioLogado == null) {
            System.out.println("\n⚠️  Você precisa cadastrar um usuário primeiro!");
            return;
        }
        
        System.out.println("\n========== AGENDAR HORÁRIO ==========");
        
        // Seleciona o doutor
        Doutor doutor = selecionarDoutor();
        if (doutor == null) {
            return;
        }
        
        // Seleciona o serviço
        Servico servico = selecionarServico();
        if (servico == null) {
            return;
        }
        
        // Seleciona data e hora
        System.out.print("Digite a data (DD/MM/YYYY): ");
        String data = scanner.nextLine();
        System.out.print("Digite a hora (HH:MM): ");
        String hora = scanner.nextLine();
        
        try {
            LocalDateTime dataHora = converterStringParaLocalDateTime(data, hora);
            
            if (agendamentoService.estaDisponivel(doutor.getId(), dataHora)) {
                Agendamento agendamento = new Agendamento(usuarioLogado, doutor, servico, dataHora);
                agendamentoService.criar(agendamento);
                System.out.println("✓ Agendamento realizado com sucesso! ID: " + agendamento.getId());
            } else {
                System.out.println("✗ Este horário não está disponível com este doutor.");
            }
        } catch (Exception e) {
            System.out.println("✗ Erro ao agendar: " + e.getMessage());
        }
    }
    
    /**
     * Visualiza horários disponíveis por doutor
     */
    private void verHorariosDisponiveis() {
        System.out.println("\n========== VER HORÁRIOS DISPONÍVEIS ==========");
        
        Doutor doutor = selecionarDoutor();
        if (doutor == null) {
            return;
        }
        
        List<Agendamento> agendamentos = agendamentoService.listarPorDoutor(doutor.getId());
        
        if (agendamentos.isEmpty()) {
            System.out.println("✓ Doutor " + doutor.getNome() + " tem todos os horários disponíveis!");
        } else {
            System.out.println("\nHorários ocupados para " + doutor.getNome() + ":");
            agendamentos.forEach(a -> {
                System.out.println("  - " + a.getDataHora() + " (" + a.getServico().getNome() + ")");
            });
        }
    }
    
    /**
     * Cadastra um novo doutor
     */
    private void cadastrarDoutor() {
        System.out.println("\n========== CADASTRAR DOUTOR ==========");
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CRP (número): ");
        String crp = scanner.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        
        Doutor doutor = new Doutor(nome, crp, especialidade, telefone, email);
        doutorService.criar(doutor);
        System.out.println("✓ Doutor cadastrado com sucesso! ID: " + doutor.getId());
    }
    
    /**
     * Lista agendamentos do usuário logado
     */
    private void listarMeusAgendamentos() {
        if (usuarioLogado == null) {
            System.out.println("\n⚠️  Você precisa cadastrar um usuário primeiro!");
            return;
        }
        
        System.out.println("\n========== MEUS AGENDAMENTOS ==========");
        
        List<Agendamento> agendamentos = agendamentoService.listarTodos().stream()
                .filter(a -> a.getUsuario() != null && a.getUsuario().getId() == usuarioLogado.getId())
                .collect(Collectors.toList());
        
        if (agendamentos.isEmpty()) {
            System.out.println("Você não possui agendamentos.");
        } else {
            agendamentos.forEach(a -> {
                System.out.println("\n[ID: " + a.getId() + "]");
                System.out.println("  Doutor: " + a.getDoutor().getNome());
                System.out.println("  Serviço: " + a.getServico().getNome());
                System.out.println("  Data/Hora: " + a.getDataHora());
                System.out.println("  Valor: R$ " + String.format("%.2f", a.getServico().getValor()));
                System.out.println("  Status: " + a.getStatus());
            });
        }
    }
    
    /**
     * Cancela um agendamento
     */
    private void cancelarAgendamento() {
        if (usuarioLogado == null) {
            System.out.println("\n⚠️  Você precisa cadastrar um usuário primeiro!");
            return;
        }
        
        System.out.println("\n========== CANCELAR AGENDAMENTO ==========");
        System.out.print("ID do agendamento: ");
        int id = lerInteiro();
        
        Agendamento agendamento = agendamentoService.buscarPorId(id);
        if (agendamento == null) {
            System.out.println("Agendamento não encontrado.");
        } else if (agendamento.getUsuario().getId() != usuarioLogado.getId()) {
            System.out.println("Você não tem permissão para cancelar este agendamento.");
        } else {
            agendamentoService.cancelar(id);
            System.out.println("✓ Agendamento cancelado com sucesso!");
        }
    }
    
    /**
     * Permite ao usuário selecionar um doutor
     */
    private Doutor selecionarDoutor() {
        List<Doutor> doutores = doutorService.listarTodos();
        
        if (doutores.isEmpty()) {
            System.out.println("Nenhum doutor cadastrado.");
            return null;
        }
        
        System.out.println("\nDoutores disponíveis:");
        for (Doutor d : doutores) {
            System.out.println(d);
        }
        
        System.out.print("Selecione o ID do doutor: ");
        int id = lerInteiro();
        
        Doutor doutor = doutorService.buscarPorId(id);
        if (doutor == null) {
            System.out.println("Doutor não encontrado.");
        }
        return doutor;
    }
    
    /**
     * Permite ao usuário selecionar um serviço
     */
    private Servico selecionarServico() {
        List<Servico> servicos = servicoService.listarTodos();
        
        if (servicos.isEmpty()) {
            System.out.println("Nenhum serviço cadastrado.");
            return null;
        }
        
        System.out.println("\nServiços disponíveis:");
        for (Servico s : servicos) {
            System.out.println(s);
        }
        
        System.out.print("Selecione o ID do serviço: ");
        int id = lerInteiro();
        
        Servico servico = servicoService.buscarPorId(id);
        if (servico == null) {
            System.out.println("Serviço não encontrado.");
        }
        return servico;
    }
    
    /**
     * Converte string de data e hora para LocalDateTime
     */
    private LocalDateTime converterStringParaLocalDateTime(String data, String hora) {
        String[] dataParts = data.split("/");
        String[] horaParts = hora.split(":");
        
        int dia = Integer.parseInt(dataParts[0]);
        int mes = Integer.parseInt(dataParts[1]);
        int ano = Integer.parseInt(dataParts[2]);
        int horas = Integer.parseInt(horaParts[0]);
        int minutos = Integer.parseInt(horaParts[1]);
        
        return LocalDateTime.of(ano, mes, dia, horas, minutos);
    }
    
    /**
     * Lê um inteiro da entrada do usuário
     */
    private int lerInteiro() {
        try {
            int valor = Integer.parseInt(scanner.nextLine());
            return valor;
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Digite um número.");
            return lerInteiro();
        }
    }
    
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.exibirMenuPrincipal();
    }
}
