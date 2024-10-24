package dao;

import databaseConnection.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Usuario;

public class UsuarioDAO {

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();

        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseConnection.getConnection();

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
                usuario.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
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

    public Usuario buscarUsuarioPorID(int id) {
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
                usuario.setDataNascimento(resultSet.getDate("data_nascimento").toLocalDate());
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

    public boolean DeletarUserPorID(int id) {
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
