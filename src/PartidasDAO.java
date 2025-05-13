import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PartidasDAO {

    public void inserir(Partida partida) throws SQLException {
        Connection conexao = Conexoes.getConexao();

        PreparedStatement statement = conexao.prepareStatement(
                "INSERT INTO partidas (numero_da_rodada, casa, visitante, gols_casa, gols_visitante) VALUES (?, ?, ?, ?, ?)"
        );

        statement.setInt(1, partida.getNumero_rodada());
        statement.setInt(2, partida.getTimeCasaId());
        statement.setInt(3, partida.getTimeForaId());
        statement.setInt(4, partida.getGolsCasa());
        statement.setInt(5, partida.getGolsFora());

        statement.executeUpdate();
    }
}
