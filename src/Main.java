import db.DB;
import java.sql.Connection;
import java.util.Scanner;
import model.dao.UsuarioDAO;
import model.dao.impl.UsuarioDAOJDBC;
import model.entities.CLI;
import model.entities.Usuario;

public class Main {
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            Connection conn = DB.getConnection();
            UsuarioDAO usuarioDAO = new UsuarioDAOJDBC(conn);

            Usuario usuarioLogado = realizarLogin(sc, usuarioDAO);
            if (usuarioLogado == null) {
                System.out.println("Número máximo de tentativas atingido. Encerrando aplicação.");
                return;
            }

            executarMenuPrincipal(sc, usuarioLogado);
        } finally {
            DB.closeConnection();
        }
    }

    private static Usuario realizarLogin(Scanner sc, UsuarioDAO usuarioDAO) {
        final int maxTentativas = 3;
        for (int tentativa = 1; tentativa <= maxTentativas; tentativa++) {
            System.out.println("\n=== Login ===");
            System.out.print("E-mail: ");
            String email = sc.nextLine().trim(); //Removendo espaços do inicio e fim
            System.out.print("Senha: ");
            String senha = sc.nextLine().trim();

            Usuario usuario = usuarioDAO.authenticate(email, senha);
            if (usuario != null) {
                CLI.clear();
                System.out.printf("Bem-vindo(a), %s!%n", usuario.getNome());
                return usuario;
            }
            
            int restantes = maxTentativas - tentativa;
            if (restantes > 0) {
                System.out.printf("Credenciais inválidas. Você ainda tem %d tentativa(s).%n", restantes);
            }
            
        }
        
        return null;
    }

    private static void executarMenuPrincipal(Scanner sc, Usuario usuario) {
        int opcao;
        do {
            exibirMenuPrincipal();
            System.out.print("Escolha uma opção: ");
            opcao = lerOpcao(sc);

            switch (opcao) {
                case 1:
                    menuVendas(sc, usuario);
                    break;
                case 2:
                    System.out.println("Produtos selecionado (em desenvolvimento).");
                    break;
                case 3:
                    System.out.println("Clientes selecionado (em desenvolvimento).");
                    break;
                case 4:
                    System.out.println("Fornecedores selecionado (em desenvolvimento).");
                    break;
                case 5:
                    System.out.println("Funcionários selecionado (em desenvolvimento).");
                    break;
                case 6:
                    System.out.println("Relatórios selecionado (em desenvolvimento).");
                    break;
                case 0:
                    System.out.printf("Até logo, %s!%n", usuario.getNome());
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }

    private static void exibirMenuPrincipal() {
        System.out.println("\nMenu:");
        System.out.println("1. Vendas");
        System.out.println("2. Produtos");
        System.out.println("3. Clientes");
        System.out.println("4. Fornecedores");
        System.out.println("5. Funcionários");
        System.out.println("6. Relatórios");
        System.out.println("0. Sair");
    }

    private static int lerOpcao(Scanner sc) {
        while (!sc.hasNextInt()) {
            System.out.print("Entrada inválida. Digite um número: ");
            sc.nextLine();
        }
        int opcao = sc.nextInt();
        sc.nextLine();
        return opcao;
    }

    private static void menuVendas(Scanner sc, Usuario usuario) {
        int opcao;
        do {
            System.out.println("\n--- Menu de Vendas ---");
            System.out.println("1. Nova Venda");
            System.out.println("2. Consultar Venda");
            System.out.println("0. Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");

            opcao = lerOpcao(sc);

            switch (opcao) {
                case 1:
                    System.out.println("Nova Venda selecionada (em desenvolvimento).");
                    break;
                case 2:
                    System.out.println("Consultar Venda selecionada (em desenvolvimento).");
                    break;
                case 0:
                    System.out.println("Voltando ao Menu Principal...");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (opcao != 0);
    }
}
