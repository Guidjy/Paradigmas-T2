import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TreinadoresDAO {

    public void inserir(Treinador treinador) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO treinadores (nome, time) VALUES (?, ?)"
        );
        stmt.setString(1, treinador.getNome());
        stmt.setInt(2, treinador.getTimeId());
        stmt.executeUpdate();
    }

    public Treinador buscarPorId(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM treinadores WHERE id = ?");
        stmt.setInt(1, id);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            Treinador t = new Treinador(rs.getString("nome"), rs.getInt("time"));
            t.setId(rs.getInt("id"));
            return t;
        }
        return null;
    }

    public List<Treinador> buscarTodos() throws SQLException {
        Connection conexao = Conexoes.getConexao();
        Statement stmt = conexao.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM treinadores");

        List<Treinador> lista = new ArrayList<>();
        while (rs.next()) {
            Treinador t = new Treinador(rs.getString("nome"), rs.getInt("time"));
            t.setId(rs.getInt("id"));
            lista.add(t);
        }
        return lista;
    }

    public void atualizar(Treinador treinador) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement(
                "UPDATE treinadores SET nome = ?, time = ? WHERE id = ?"
        );
        stmt.setString(1, treinador.getNome());
        stmt.setInt(2, treinador.getTimeId());
        stmt.setInt(3, treinador.getId());
        stmt.executeUpdate();
    }

    public void deletar(int id) throws SQLException {
        Connection conexao = Conexoes.getConexao();
        PreparedStatement stmt = conexao.prepareStatement("DELETE FROM treinadores WHERE id = ?");
        stmt.setInt(1, id);
        stmt.executeUpdate();
    }
}
