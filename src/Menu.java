import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
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
                    case 2 -> submenuAlterar();
                    case 3 -> submenuExcluir();
                    case 4 -> submenuBuscar();
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
        System.out.print("Nome do time: ");
        String nomeTime = scanner.nextLine();
        TimesDAO dao = new TimesDAO();
        int timeId = dao.buscarPorNome(nomeTime).getFirst().getId();

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
        scanner.nextLine();
        System.out.print("Nome do time: ");
        String nomeTime = scanner.nextLine();
        TimesDAO dao = new TimesDAO();
        int timeId = dao.buscarPorNome(nomeTime).getFirst().getId();

        Jogador jogador = new Jogador(nome, posicao, idade, numero, timeId);
        new JogadoresDAO().inserir(jogador);
        System.out.println("Jogador cadastrado com sucesso!");
    }

    private static void cadastrarPartida() throws SQLException {
        System.out.print("Nome do time da casa: ");
        String nomeTime = scanner.nextLine();
        TimesDAO dao = new TimesDAO();
        int casa = dao.buscarPorNome(nomeTime).getFirst().getId();
        System.out.print("Nome do time visitante: ");
        nomeTime = scanner.nextLine();
        int visitante = dao.buscarPorNome(nomeTime).getFirst().getId();
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

    private static void submenuAlterar() throws SQLException {
        System.out.println("\n--- Submenu Alterar ---");
        System.out.println("1. Alterar time");
        System.out.println("2. Alterar treinador");
        System.out.println("3. Alterar jogador");
        System.out.println("4. Alterar partida");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("ID do time: ");
                int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Novo estádio: ");
                String estadio = scanner.nextLine();
                System.out.print("Nova cidade: ");
                String cidade = scanner.nextLine();
                System.out.print("Nova data de fundação (yyyy-mm-dd): ");
                Date data = Date.valueOf(scanner.nextLine());
                System.out.print("Número de jogadores: ");
                int nJogadores = scanner.nextInt();

                Time time = new Time(nome, estadio, cidade, data);
                time.setId(id);
                time.setnJogadores(nJogadores);
                new TimesDAO().atualizar(time);
                System.out.println("Time atualizado com sucesso!");
            }
            case 2 -> {
                System.out.print("ID do treinador: ");
                int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Nome do novo time: ");
                String nomeTime = scanner.nextLine();
                int timeId = new TimesDAO().buscarPorNome(nomeTime).getFirst().getId();

                Treinador t = new Treinador(nome, timeId);
                t.setId(id);
                new TreinadoresDAO().atualizar(t);
                System.out.println("Treinador atualizado com sucesso!");
            }
            case 3 -> {
                System.out.print("ID do jogador: ");
                int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Novo nome: ");
                String nome = scanner.nextLine();
                System.out.print("Nova posição: ");
                String posicao = scanner.nextLine();
                System.out.print("Nova idade: ");
                int idade = scanner.nextInt();
                System.out.print("Novo número da camisa: ");
                int numero = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nome do novo time: ");
                String nomeTime = scanner.nextLine();
                int timeId = new TimesDAO().buscarPorNome(nomeTime).getFirst().getId();

                Jogador j = new Jogador(nome, posicao, idade, numero, timeId);
                j.setId(id);
                new JogadoresDAO().atualizar(j);
                System.out.println("Jogador atualizado com sucesso!");
            }
            case 4 -> {
                System.out.print("ID da partida: ");
                int id = scanner.nextInt(); scanner.nextLine();
                System.out.print("Nome do time da casa: ");
                String nomeCasa = scanner.nextLine();
                int casaId = new TimesDAO().buscarPorNome(nomeCasa).getFirst().getId();
                System.out.print("Nome do visitante: ");
                String nomeFora = scanner.nextLine();
                int visitanteId = new TimesDAO().buscarPorNome(nomeFora).getFirst().getId();
                System.out.print("Gols da casa: ");
                int golsCasa = scanner.nextInt();
                System.out.print("Gols do visitante: ");
                int golsFora = scanner.nextInt();
                System.out.print("Número da rodada: ");
                int rodada = scanner.nextInt();

                Partida p = new Partida(casaId, visitanteId, golsCasa, golsFora, rodada);
                p.setId(id);
                new PartidasDAO().atualizar(p);
                System.out.println("Partida atualizada com sucesso!");
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void submenuExcluir() throws SQLException {
        System.out.println("\n--- Submenu Excluir ---");
        System.out.println("1. Excluir time");
        System.out.println("2. Excluir treinador");
        System.out.println("3. Excluir jogador");
        System.out.println("4. Excluir partida");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("Nome do time a deletar: ");
                String nome = scanner.nextLine();
                TimesDAO dao = new TimesDAO();
                var lista = dao.buscarPorNome(nome);
                if (!lista.isEmpty()) {
                    int id = lista.getFirst().getId();
                    dao.deletar(id);
                    System.out.println("Time excluído com sucesso!");
                } else {
                    System.out.println("Time não encontrado.");
                }
            }
            case 2 -> {
                System.out.print("Nome do treinador a deletar: ");
                String nome = scanner.nextLine();
                TreinadoresDAO dao = new TreinadoresDAO();
                var lista = dao.buscarPorNome(nome);
                if (!lista.isEmpty()) {
                    int id = lista.getFirst().getId();
                    dao.deletar(id);
                    System.out.println("Treinador excluído com sucesso!");
                } else {
                    System.out.println("Treinador não encontrado.");
                }
            }
            case 3 -> {
                System.out.print("Nome do jogador a deletar: ");
                String nome = scanner.nextLine();
                JogadoresDAO dao = new JogadoresDAO();
                var lista = dao.buscarPorNome(nome);
                if (!lista.isEmpty()) {
                    int id = lista.getFirst().getId();
                    dao.deletar(id);
                    System.out.println("Jogador excluído com sucesso!");
                } else {
                    System.out.println("Jogador não encontrado.");
                }
            }
            case 4 -> {
                System.out.print("Nome do time da casa: ");
                String nomeCasa = scanner.nextLine();
                System.out.print("Nome do time visitante: ");
                String nomeVisitante = scanner.nextLine();
                PartidasDAO dao = new PartidasDAO();
                Partida partida = dao.buscarPorNomesDosTimes(nomeCasa, nomeVisitante).getFirst();
                if (partida != null) {
                    dao.deletar(partida.getId());
                    System.out.println("Partida excluída com sucesso!");
                } else {
                    System.out.println("Partida não encontrada.");
                }
            }
            default -> System.out.println("Opção inválida.");
        }
    }

    private static void submenuBuscar() throws SQLException {
        System.out.println("\n--- Submenu Buscar ---");
        System.out.println("1. Buscar time");
        System.out.println("2. Buscar treinador");
        System.out.println("3. Buscar jogador");
        System.out.println("4. Buscar partida");
        System.out.print("Escolha uma opção: ");
        int opcao = scanner.nextInt();
        scanner.nextLine();

        switch (opcao) {
            case 1 -> {
                System.out.print("> Nome do time: ");
                String nome = scanner.nextLine();
                TimesDAO dao = new TimesDAO();
                List<Time> times = dao.buscarPorNome(nome);
                for (int i = 0; i < times.size(); i++) {
                    Time t = times.get(i);
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                    t.printDados();
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                }
            }
            case 2 -> {
                System.out.print("> Nome do treinador: ");
                String nome = scanner.nextLine();
                TreinadoresDAO dao = new TreinadoresDAO();
                List<Treinador> treinadores = dao.buscarPorNome(nome);
                for (int i = 0; i < treinadores.size(); i++) {
                    Treinador t = treinadores.get(i);
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                    t.printDados();
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                }
            }
            case 3 -> {
                System.out.println("Buscar por [1]nome ou [2]time?");
                int escolha = scanner.nextInt(); scanner.nextLine();
                JogadoresDAO dao = new JogadoresDAO();
                List<Jogador> jogadores;
                if (escolha == 1) {
                    System.out.print("Nome do jogador: ");
                    String nome = scanner.nextLine();
                    jogadores = dao.buscarPorNome(nome);
                } else {
                    System.out.print("Time do jogador: ");
                    String time = scanner.nextLine();
                    jogadores = dao.buscarPorNomeDoTime(time);
                }
                for (int i = 0; i < jogadores.size(); i++) {
                    Jogador j = jogadores.get(i);
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                    j.printDados();
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                }
            }
            case 4 -> {
                System.out.print("Nome do time da casa: ");
                String casa = scanner.nextLine();
                System.out.print("Nome do time visitante: ");
                String visitante = scanner.nextLine();
                PartidasDAO dao = new PartidasDAO();
                List<Partida> partidas = dao.buscarPorNomesDosTimes(casa, visitante);
                for (int i = 0; i < partidas.size(); i++) {
                    Partida partida = partidas.get(i);
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                    partida.printDados();
                    System.out.println("-=-=-=-=-=-=-=-=-=-=-");
                }

            }
            default -> System.out.println("Opção inválida.");
        }
    }



}
