package databaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/am2cursos?useSSL=false&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";

    //Metodo para obter nova conexão 
    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        

        try {
            Class.forName(DRIVER); // Carrega o driver JDBC
            Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexao com o banco de dados estabelecida");
            return connection;
        } catch (SQLException e) {
            System.err.println("Erro de SQL: " + e.getErrorCode() + " - " + e.getMessage());
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

    /*
    public static void main(String[] args) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            System.out.println("Conexão bem-sucedida!");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }  */

}
