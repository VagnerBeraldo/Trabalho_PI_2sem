package dao;

import databaseConnection.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Usuario;

public class UsuarioDAO {

    //metodo para cadastrar usuario
    public boolean cadastrarUsuario(Usuario usuario) throws ClassNotFoundException {

        Connection connection = null;
        PreparedStatement PS = null;

        try {
            connection = DatabaseConnection.getConnection();

            String sql = """
             INSERT INTO am2cursos.users 
             (nome, sobrenome, nome_social, cpf, data_nascimento, email, tipo_pagamento) 
             VALUES (?, ?, ?, ?, ?, ?, ?);
             """;

            PS = connection.prepareStatement(sql);
            PS.setString(1, usuario.getNome());
            PS.setString(2, usuario.getSobrenome());
            PS.setString(3, usuario.getNomeSocial());
            PS.setString(4, usuario.getCpf());
            PS.setString(5, usuario.getDataNascimento());
            PS.setString(6, usuario.getEmail());
            PS.setString(7, usuario.getTipoPagamento());

            PS.executeUpdate(); // Executa a atualização

            // Retorna true se pelo menos uma linha foi afetada (ou seja, o usuário foi cadastrado)
            return PS.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;

        } finally {
            // Fechamento dos recursos
            if (PS != null) {
                try {
                    PS.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            // Fechar a conexão usando o meu método utilitário / gasto menos linha que o trycatch pra connection
            DatabaseConnection.closeConnection(connection);
        }
    }

    //Metodo para listar todos os usuarios, Retorna uma Lista<> de usuarios
    public List<Usuario> listarUsuarios() throws ClassNotFoundException {
        List<Usuario> usuarios = new ArrayList<>();

        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.DatabaseConnection.getConnection();

            String sql = "SELECT * FROM users";

            PS = connection.prepareStatement(sql);
            resultSet = PS.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();

                usuario.setID(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSobrenome(resultSet.getString("sobrenome"));
                usuario.setNomeSocial(resultSet.getString("nome_social"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setDataNascimento(resultSet.getString("data_nascimento"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipoPagamento(resultSet.getString("tipo_pagamento"));

                // Adiciona o usuário à lista
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechamento dos recursos
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (PS != null) {
                try {
                    PS.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConnection(connection);
        }

        // Retorna a lista de usuários
        return usuarios;
    }

    //Metodo para buscar usuario pelo ID
    public Usuario buscarUsuarioPorID(int id) throws ClassNotFoundException {
        Usuario usuario = null;

        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();

            String sql = "SELECT * FROM users WHERE id = ?";

            PS = connection.prepareStatement(sql);
            PS.setInt(1, id); // Substitui o parâmetro ? pelo ID
            resultSet = PS.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();

                usuario.setID(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSobrenome(resultSet.getString("sobrenome"));
                usuario.setNomeSocial(resultSet.getString("nome_social"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setDataNascimento(resultSet.getString("data_nascimento"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipoPagamento(resultSet.getString("tipo_pagamento"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fechamento dos recursos
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (PS != null) {
                try {
                    PS.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConnection(connection);
        }

        // Retorna o usuário encontrado ou null se não houver
        return usuario;
    }

    public boolean DeletarUserPorID(int id) throws ClassNotFoundException {
        Usuario usuario = null;

        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();

            String sql = "DELETE FROM users WHERE id = ?";

            PS = connection.prepareStatement(sql);
            PS.setInt(1, id); // Substitui o parâmetro ? pelo ID

            int rowsAffected = PS.executeUpdate(); // Executa a atualização

            // Retorna true se pelo menos uma linha foi afetada (ou seja, o usuário foi deletado)
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
            // Em caso de erro, retorna false
        } finally {
            // Fechamento dos recursos
            if (PS != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (PS != null) {
                try {
                    PS.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            DatabaseConnection.closeConnection(connection);
        }
    }

    //Fechamento da classe para nao se perder nessa porra
}
