
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
import model.Endereco;
import model.UsuarioEnderecoDTO;

public class AdminDAO {

    public static boolean atualizaUser(Usuario usuario, Endereco endereco) {
        
        System.out.println("chegou aqui");
        
        return true;
    }

    public UsuarioEnderecoDTO buscarPorIdAtualizar(Usuario usuario) throws ClassNotFoundException {
        
        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;
        Endereco endereco = new Endereco(); // Declare e inicialize a variável
        
        
        
        try {
            connection = DatabaseConnection.getConnection();

            String sql = "SELECT users.id_user, users.nome, users.sobrenome, users.nome_social, users.cpf, " +
             "users.data_nascimento, users.senha, users.email, endereco_users.cep, endereco_users.rua, " +
             "endereco_users.numero, endereco_users.complemento, endereco_users.bairro, endereco_users.cidade, " +
             "endereco_users.estado, contato_users.numero_telefone_celular, contato_users.numero_telefone_residencial, " +
             "cursos.materia, users.tipo_pagamento " +
             "FROM users " +
             "LEFT JOIN endereco_users ON users.id_user = endereco_users.usuario_id " +
             "LEFT JOIN contato_users ON users.id_user = contato_users.usuario_id " +
             "LEFT JOIN user_cursos ON users.id_user = user_cursos.id_user " +
             "LEFT JOIN cursos ON user_cursos.id_curso = cursos.id_curso " +
             "WHERE users.id_user = ?";

            PS = connection.prepareStatement(sql);
            PS.setInt(1, usuario.getId_user()); // Substitui o parâmetro ? pelo ID
            resultSet = PS.executeQuery();

            if (resultSet.next()) {
                usuario = new Usuario();
                endereco = new Endereco();

                usuario.setId_user(resultSet.getInt("id_user"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSobrenome(resultSet.getString("sobrenome"));
                usuario.setNome_social(resultSet.getString("nome_social"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setData_nascimento(resultSet.getString("data_nascimento"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipo_pagamento(resultSet.getString("tipo_pagamento"));
                usuario.setCurso(resultSet.getString("materia"));
                usuario.setTelefone_cel(resultSet.getString("numero_telefone_celular"));
                usuario.setTelefone_cel(resultSet.getString("numero_telefone_residencial"));

                endereco.setCep(resultSet.getString("cep"));
                endereco.setRua(resultSet.getString("rua"));
                endereco.setNumero(resultSet.getString("numero"));
                endereco.setComplemento(resultSet.getString("complemento"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setCidade(resultSet.getString("cidade"));                
                endereco.setEstado(resultSet.getString("estado"));
                
                
                //adaptação com DTO para retornar duas classes juntas
                return new UsuarioEnderecoDTO(usuario, endereco);
                
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

        return null; // Retorna null se não encontrar o usuário
        
    }


    
}
