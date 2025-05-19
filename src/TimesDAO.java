import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TimesDAO {

    public void inserir(Time time) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO times (nome, estadio, cidade, data_de_fundacao, n_jogadores) VALUES (?, ?, ?, ?, ?)"
        );
        stmt.setString(1, time.getNome());
        stmt.setString(2, time.getEstadio());
        stmt.setString(3, time.getCidade());
        stmt.setDate(4, time.getDataDeFundacao());
        stmt.setInt(5, time.getnJogadores());
        stmt.executeUpdate();
    }

    public Time buscarPorId(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM times WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Time time = new Time(
                    rs.getString("nome"),
                    rs.getString("estadio"),
                    rs.getString("cidade"),
                    rs.getDate("data_de_fundacao")
            );
            time.setId(rs.getInt("id"));
            time.setnJogadores(rs.getInt("n_jogadores"));

            // Carregar jogadores
            JogadoresDAO jogadoresDAO = new JogadoresDAO();
            List<Jogador> jogadores = jogadoresDAO.buscarPorNomeDoTime(time.getNome());
            for (Jogador jogador : jogadores) {
                time.addJogador(jogador);
            }

            return time;
        }
        return null;
    }

    public List<Time> buscarTodos() throws SQLException {
        Connection conexao = Conexoes.getConexao();
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM times");

        List<Time> lista = new ArrayList<>();
        while (rs.next()) {
            Time time = new Time(
                    rs.getString("nome"),
                    rs.getString("estadio"),
                    rs.getString("cidade"),
                    rs.getDate("data_de_fundacao")
            );
            time.setId(rs.getInt("id"));
            time.setnJogadores(rs.getInt("n_jogadores"));
            lista.add(time);
        }
        return lista;
    }

    public void atualizar(Time time) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "UPDATE times SET nome = ?, estadio = ?, cidade = ?, data_de_fundacao = ?, n_jogadores = ? WHERE id = ?"
        );
        stmt.setString(1, time.getNome());
        stmt.setString(2, time.getEstadio());
        stmt.setString(3, time.getCidade());
        stmt.setDate(4, time.getDataDeFundacao());
        stmt.setInt(5, time.getnJogadores());
        stmt.setInt(6, time.getId());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement("DELETE FROM times WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
