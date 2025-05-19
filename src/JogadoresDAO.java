import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JogadoresDAO {


    public boolean inserir(Jogador jogador) throws SQLException {
        Connection conexao = Conexoes.getConexao();

        // Consulta para contar jogadores no time
        PreparedStatement countStmt = conexao.prepareStatement(
                "SELECT COUNT(*) FROM jogadores WHERE time = ?"
        );
        countStmt.setInt(1, jogador.getTimeId());
        ResultSet rs = countStmt.executeQuery();

        int nJogadores = 0;
        if (rs.next()) {
            nJogadores = rs.getInt(1);
        }

        if (nJogadores >= 25) {
            System.out.println("Este time já possui 25 jogadores.");
            return false;
        }

        // Insere o jogador se o limite não foi atingido
        PreparedStatement insertStmt = conexao.prepareStatement(
                "INSERT INTO jogadores (nome, posicao, idade, numero, time) VALUES (?, ?, ?, ?, ?)"
        );
        insertStmt.setString(1, jogador.getNome());
        insertStmt.setString(2, jogador.getPosicao());
        insertStmt.setInt(3, jogador.getIdade());
        insertStmt.setInt(4, jogador.getNumeroCamisa());
        insertStmt.setInt(5, jogador.getTimeId());

        insertStmt.executeUpdate();

        //Atualiza o número de jogadores no time
        PreparedStatement updateTimeStmt = conexao.prepareStatement(
                "UPDATE times SET n_jogadores = n_jogadores + 1 WHERE id = ?"
        );
        updateTimeStmt.setInt(1, jogador.getTimeId());
        updateTimeStmt.executeUpdate();

        return true;
    }



    public Jogador buscarPorId(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM jogadores WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Jogador jogador = new Jogador(
                    rs.getString("nome"),
                    rs.getString("posicao"),
                    rs.getInt("idade"),
                    rs.getInt("numero"),
                    rs.getInt("time")
            );
            jogador.setId(rs.getInt("id"));
            return jogador;
        }
        return null;
    }

    public List<Jogador> buscarPorNome(String nome) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "SELECT * FROM jogadores WHERE nome LIKE ?"
        );
        // Permite buscar qualquer parte do nome
        stmt.setString(1, "%" + nome + "%");

        ResultSet rs = stmt.executeQuery();
        List<Jogador> jogadores = new ArrayList<>();

        while (rs.next()) {
            Jogador jogador = new Jogador(
                    rs.getString("nome"),
                    rs.getString("posicao"),
                    rs.getInt("idade"),
                    rs.getInt("numero"),
                    rs.getInt("time")
            );
            jogador.setId(rs.getInt("id"));
            jogadores.add(jogador);
        }

        return jogadores;
    }

    public List<Jogador> buscarPorNomeDoTime(String nomeDoTime) throws SQLException {
        Connection conexao = Conexoes.getConexao();

        String sql = """
            SELECT j.*
            FROM jogadores j
            JOIN times t ON j."time" = t.id
            WHERE t.nome LIKE ?
        """;

        PreparedStatement stmt = conexao.prepareStatement(sql);
        stmt.setString(1, "%" + nomeDoTime + "%");

        ResultSet rs = stmt.executeQuery();
        List<Jogador> jogadores = new ArrayList<>();

        while (rs.next()) {
            Jogador jogador = new Jogador(
                    rs.getString("nome"),
                    rs.getString("posicao"),
                    rs.getInt("idade"),
                    rs.getInt("numero"),
                    rs.getInt("time")
            );
            jogador.setId(rs.getInt("id"));
            jogadores.add(jogador);
        }

        return jogadores;
    }


    public List<Jogador> buscarTodos() throws SQLException {
        Connection conexao = Conexoes.getConexao();
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM jogadores");

        List<Jogador> lista = new ArrayList<>();
        while (rs.next()) {
            Jogador jogador = new Jogador(
                    rs.getString("nome"),
                    rs.getString("posicao"),
                    rs.getInt("idade"),
                    rs.getInt("numero"),
                    rs.getInt("time")
            );
            jogador.setId(rs.getInt("id"));
            lista.add(jogador);
        }
        return lista;
    }

    public void atualizar(Jogador jogador) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "UPDATE jogadores SET nome = ?, posicao = ?, idade = ?, numero = ?, time = ? WHERE id = ?"
        );
        stmt.setString(1, jogador.getNome());
        stmt.setString(2, jogador.getPosicao());
        stmt.setInt(3, jogador.getIdade());
        stmt.setInt(4, jogador.getNumeroCamisa());
        stmt.setInt(5, jogador.getTimeId());
        stmt.setInt(6, jogador.getId());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();

        // Primeiro, buscar o jogador para saber o time
        Jogador jogador = buscarPorId(id); // você já tem esse método
        if (jogador != null) {
            // Deleta o jogador
            PreparedStatement stmt = conexao.prepareStatement("DELETE FROM jogadores WHERE id = ?");
            stmt.setInt(1, id);
            stmt.executeUpdate();

            // Atualiza o número de jogadores no time
            PreparedStatement updateTimeStmt = conexao.prepareStatement(
                    "UPDATE times SET n_jogadores = n_jogadores - 1 WHERE id = ?"
            );
            updateTimeStmt.setInt(1, jogador.getTimeId());
            updateTimeStmt.executeUpdate();
        }
    }