import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PartidasDAO {

    public void inserir(Partida partida) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO partidas (numero_da_rodada, casa, visitante, gols_casa, gols_visitante) VALUES (?, ?, ?, ?, ?)"
        );
        stmt.setInt(1, partida.getNumero_rodada());
        stmt.setInt(2, partida.getTimeCasaId());
        stmt.setInt(3, partida.getTimeForaId());
        stmt.setInt(4, partida.getGolsCasa());
        stmt.setInt(5, partida.getGolsFora());
        stmt.executeUpdate();
    }

    public Partida buscarPorId(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM partidas WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Partida p = new Partida(
                    rs.getInt("casa"),
                    rs.getInt("visitante"),
                    rs.getInt("gols_casa"),
                    rs.getInt("gols_visitante"),
                    rs.getInt("numero_da_rodada")
            );
            p.setId(rs.getInt("id"));
            return p;
        }
        return null;
    }

    public List<Partida> buscarTodas() throws SQLException {
        Connection conexao = Conexoes.getConexao();
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM partidas");

        List<Partida> lista = new ArrayList<>();
        while (rs.next()) {
            Partida p = new Partida(
                    rs.getInt("casa"),
                    rs.getInt("visitante"),
                    rs.getInt("gols_casa"),
                    rs.getInt("gols_visitante"),
                    rs.getInt("numero_da_rodada")
            );
            p.setId(rs.getInt("id"));
            lista.add(p);
        }
        return lista;
    }

    public void atualizar(Partida partida) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "UPDATE partidas SET numero_da_rodada = ?, casa = ?, visitante = ?, gols_casa = ?, gols_visitante = ? WHERE id = ?"
        );
        stmt.setInt(1, partida.getNumero_rodada());
        stmt.setInt(2, partida.getTimeCasaId());
        stmt.setInt(3, partida.getTimeForaId());
        stmt.setInt(4, partida.getGolsCasa());
        stmt.setInt(5, partida.getGolsFora());
        stmt.setInt(6, partida.getId());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement("DELETE FROM partidas WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}