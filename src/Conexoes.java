import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


// gerencia setup do JDBC e conexão com o banco de dados
public class Conexoes {
    private static final String URL = "jdbc:postgresql://localhost/campeonato";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "4213";

    static {
        try {
            // carrega o driver na memória
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Driver não encontrado.", e);
        }
    }

    // abre uma conexão JDBC com o servidor local
    public static Connection getConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
