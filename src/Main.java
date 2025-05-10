import java.sql.Date;
import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // teste
        Time time = new Time("Corinthians", "Neo Química Arena", "São Paulo", Date.valueOf("1920-05-01"));
        TimesDAO daoTime = new TimesDAO();
        daoTime.inserir(time);
    }
}