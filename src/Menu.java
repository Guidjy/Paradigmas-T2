import java.sql.Date;
import java.sql.SQLException;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    public static void exibirMenu() {
        while (true) {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Cadastrar");
            System.out.println("2. Alterar");
            System.out.println("3. Excluir");
            System.out.println("4. Buscar");
            System.out.println("5. Ver classificação geral");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (opcao) {
                    case 1 -> submenuCadastro();
                    case 2 -> System.out.println("Função de alteração ainda não implementada.");
                    case 3 -> System.out.println("Função de exclusão ainda não implementada.");
                    case 4 -> System.out.println("Função de busca ainda não implementada.");
                    case 5 -> verClassificacao();
                    case 6 -> {
                        System.out.println("Saindo...");
                        return;
                    }
                    default -> System.out.println("Opção inválida.");
                }
            } catch (SQLException e) {
                System.out.println("Erro: " + e.getMessage());
            }
        }
    }

    private static void submenuCadastro() throws SQLException {
        while (true) {
            System.out.println("\n--- Submenu Cadastro ---");
            System.out.println("1. Cadastrar time");
            System.out.println("2. Cadastrar treinador");
            System.out.println("3. Cadastrar jogador");
            System.out.println("4. Cadastrar partida");
            System.out.println("5. Voltar ao menu principal");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine();

            switch (opcao) {
                case 1 -> cadastrarTime();
                case 2 -> cadastrarTreinador();
                case 3 -> cadastrarJogador();
                case 4 -> cadastrarPartida();
                case 5 -> {
                    return;
                }
                default -> System.out.println("Opção inválida.");
            }
        }
    }

    private static void cadastrarTime() throws SQLException {
        System.out.print("Nome do time: ");
        String nome = scanner.nextLine();
        System.out.print("Estádio: ");
        String estadio = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();
        System.out.print("Data de fundação (yyyy-mm-dd): ");
        Date dataFundacao = Date.valueOf(scanner.nextLine());

        Time time = new Time(nome, estadio, cidade, dataFundacao);
        new TimesDAO().inserir(time);
        System.out.println("Time cadastrado com sucesso!");
    }

    private static void cadastrarTreinador() throws SQLException {
        System.out.print("Nome do treinador: ");
        String nome = scanner.nextLine();
        System.out.print("ID do time: ");
        int timeId = scanner.nextInt();

        Treinador treinador = new Treinador(nome, timeId);
        new TreinadoresDAO().inserir(treinador);
        System.out.println("Treinador cadastrado com sucesso!");
    }

    private static void cadastrarJogador() throws SQLException {
        System.out.print("Nome do jogador: ");
        String nome = scanner.nextLine();
        System.out.print("Posição: ");
        String posicao = scanner.nextLine();
        System.out.print("Idade: ");
        int idade = scanner.nextInt();
        System.out.print("Número da camisa: ");
        int numero = scanner.nextInt();
        System.out.print("ID do time: ");
        int timeId = scanner.nextInt();

        Jogador jogador = new Jogador(nome, posicao, idade, numero, timeId);
        new JogadoresDAO().inserir(jogador);
        System.out.println("Jogador cadastrado com sucesso!");
    }

    private static void cadastrarPartida() throws SQLException {
        System.out.print("ID do time da casa: ");
        int casa = scanner.nextInt();
        System.out.print("ID do time visitante: ");
        int visitante = scanner.nextInt();
        System.out.print("Gols do time da casa: ");
        int golsCasa = scanner.nextInt();
        System.out.print("Gols do visitante: ");
        int golsVisitante = scanner.nextInt();
        System.out.print("Número da rodada: ");
        int rodada = scanner.nextInt();

        Partida partida = new Partida(casa, visitante, golsCasa, golsVisitante, rodada);
        new PartidasDAO().inserir(partida);
        System.out.println("Partida registrada com sucesso!");
    }

    private static void verClassificacao() throws SQLException {
        Classificacao service = new Classificacao();
        Map<String, Integer> ranking = service.gerarClassificacao();
        System.out.println("\n=== CLASSIFICAÇÃO GERAL ===");
        ranking.entrySet().stream()
                .sorted((e1, e2) -> Integer.compare(e2.getValue(), e1.getValue()))
                .forEach(entry ->
                        System.out.println(entry.getKey() + ": " + entry.getValue() + " pts")
                );
    }
}
