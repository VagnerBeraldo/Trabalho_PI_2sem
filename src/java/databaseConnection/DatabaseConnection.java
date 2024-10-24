package databaseConnection;

/**
 *
 * @author erick
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/am2cursos?useSSL=false";
    private static final String USER = "root";
    private static final String PASSWORD = "";

    //Metodo para obter nova conexão 
    public static Connection getConnection() throws SQLException {
        try {

            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexao com o banco de dados estabelecida");
            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Erro ao conectar ao banco de dados", e);

        }
    }

    //Metodo para fechar a conexao
    public static void closeConnection(Connection connection) {
        if (connection != null) {
                
            try {
                connection.close();
                System.out.println("Conexao com o banco de dados Fechada");
                
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("Erro ao fechar a conexao com o banco de dados");
            }
        }
    }

}
