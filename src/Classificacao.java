import java.sql.*;
import java.util.*;

public class Classificacao {

    public Map<String, Integer> gerarClassificacao() throws SQLException {
        Connection conexao = Conexoes.getConexao();
        Statement stmt = conexao.createStatement();

        ResultSet partidas = stmt.executeQuery("SELECT * FROM partidas");
        Map<Integer, Integer> pontosPorTime = new HashMap<>();

        while (partidas.next()) {
            int casa = partidas.getInt("casa");
            int visitante = partidas.getInt("visitante");
            int golsCasa = partidas.getInt("gols_casa");
            int golsVisitante = partidas.getInt("gols_visitante");

            if (golsCasa > golsVisitante) {
                pontosPorTime.put(casa, pontosPorTime.getOrDefault(casa, 0) + 3);
                pontosPorTime.put(visitante, pontosPorTime.getOrDefault(visitante, 0));
            } else if (golsVisitante > golsCasa) {
                pontosPorTime.put(visitante, pontosPorTime.getOrDefault(visitante, 0) + 3);
                pontosPorTime.put(casa, pontosPorTime.getOrDefault(casa, 0));
            } else {
                pontosPorTime.put(casa, pontosPorTime.getOrDefault(casa, 0) + 1);
                pontosPorTime.put(visitante, pontosPorTime.getOrDefault(visitante, 0) + 1);
            }
        }

        // Map com nomes dos times
        Map<String, Integer> classificacaoFinal = new TreeMap<>();
        for (var entry : pontosPorTime.entrySet()) {
            int timeId = entry.getKey();
            int pontos = entry.getValue();
            PreparedStatement nomeStmt = conexao.prepareStatement("SELECT nome FROM times WHERE id = ?");
            nomeStmt.setInt(1, timeId);
            ResultSet rs = nomeStmt.executeQuery();
            if (rs.next()) {
                classificacaoFinal.put(rs.getString("nome"), pontos);
            }
        }

        return classificacaoFinal;
    }
}
