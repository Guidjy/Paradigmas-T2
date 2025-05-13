import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        // Menu.exibirMenu();

        PartidasDAO dao = new PartidasDAO();

        // Buscar por time da casa
        List<Partida> partidasEmCasa = dao.buscarPorNomeTimeCasa("Corinthians");
        System.out.println(partidasEmCasa.getFirst().getGolsCasa());

        // Buscar por time visitante
        List<Partida> partidasComoVisitante = dao.buscarPorNomeTimeVisitante("Maiami");
        System.out.println(partidasComoVisitante.getFirst().getGolsCasa());

        // Buscar por ambos
        List<Partida> confronto = dao.buscarPorNomesDosTimes("Corinthians", "Inter");

        for (Partida p : confronto) {
            System.out.println("Rodada " + p.getNumero_rodada() + ": " + p.getTimeCasaId() + " x " + p.getTimeForaId());
        }


    }
}
