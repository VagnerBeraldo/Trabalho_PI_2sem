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

public class UsuarioDAO {
    
    
    public static Usuario loginUser(Usuario usuario) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;
       
        
        try{ 
            
            connection = DatabaseConnection.getConnection();
            
            String sql = "select * from users where email = ? and senha = ?;";
            
            
             PS = connection.prepareStatement(sql);
             
             PS.setString(1, usuario.getEmail()); // Substitui o parâmetro ? pelo email do adm
             PS.setString(2, usuario.getSenha());  //substitui o parametro  ? pela senha do adm
             
            resultSet = PS.executeQuery();
            
            if (resultSet.next()) {
                usuario.setId_user(resultSet.getInt("id_user"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setSenha(resultSet.getString("senha"));
                // Defina outros atributos do usuário, se necessário.
                return usuario;
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
                  DatabaseConnection.closeConnection(connection); // Moveu o fechamento da conexão para fora do bloco if
             }
        return null;
    }

    //metodo para cadastrar usuario
    public boolean cadastrarUsuario(Usuario usuario, Endereco endereco) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement PS = null;

        String inserirUsuarioSQL = "INSERT INTO users (nome, sobrenome, nome_social, cpf, data_nascimento, senha, email, tipo_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String inserirEnderecoSQL = "INSERT INTO endereco_users (usuario_id, cep, rua, numero, complemento, bairro, cidade, estado) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        String inserirContatoSQL = "INSERT INTO contato_users (usuario_id, numero_telefone_celular, numero_telefone_residencial) VALUES (?, ?, ?)";
        String buscarCursoIdSQL = "SELECT id_curso FROM cursos WHERE materia = ?";
        String inserirUserCursoSQL = "INSERT INTO user_cursos (id_user, id_curso) VALUES (?, ?)";

        try {
            connection = DatabaseConnection.getConnection();

            PreparedStatement inserirUsuarioStmt = connection.prepareStatement(inserirUsuarioSQL, PS.RETURN_GENERATED_KEYS);
            PreparedStatement inserirEnderecoStmt = connection.prepareStatement(inserirEnderecoSQL);
            PreparedStatement inserirContatoStmt = connection.prepareStatement(inserirContatoSQL);
            PreparedStatement buscarCursoIdStmt = connection.prepareStatement(buscarCursoIdSQL);
            PreparedStatement inserirUserCursoStmt = connection.prepareStatement(inserirUserCursoSQL);

            connection.setAutoCommit(false);

            inserirUsuarioStmt.setString(1, usuario.getNome());
            inserirUsuarioStmt.setString(2, usuario.getSobrenome());
            inserirUsuarioStmt.setString(3, usuario.getNome_social());
            inserirUsuarioStmt.setString(4, usuario.getCpf());
            inserirUsuarioStmt.setString(5, usuario.getData_nascimento());
            inserirUsuarioStmt.setString(6, usuario.getSenha());
            inserirUsuarioStmt.setString(7, usuario.getEmail());
            inserirUsuarioStmt.setString(8, usuario.getTipo_pagamento());

            inserirUsuarioStmt.executeUpdate();

            // Capturar id_user recém-criado
            ResultSet generatedKeys = inserirUsuarioStmt.getGeneratedKeys();

            // Retorna true se pelo menos uma linha foi afetada (ou seja, o usuário foi cadastrado)
            if (generatedKeys.next()) {
                int userId = generatedKeys.getInt(1);

                // Inserir endereço
                inserirEnderecoStmt.setInt(1, userId);
                inserirEnderecoStmt.setString(2, endereco.getCep());
                inserirEnderecoStmt.setString(3, endereco.getRua());
                inserirEnderecoStmt.setString(4, endereco.getNumero());
                inserirEnderecoStmt.setString(5, endereco.getComplemento());
                inserirEnderecoStmt.setString(6, endereco.getBairro());
                inserirEnderecoStmt.setString(7, endereco.getCidade());
                inserirEnderecoStmt.setString(8, endereco.getEstado());
                inserirEnderecoStmt.executeUpdate();

                // Inserir contato
                inserirContatoStmt.setInt(1, userId);
                inserirContatoStmt.setString(2, usuario.getTelefone_cel()); // Substituir com o getter
                inserirContatoStmt.setString(3, usuario.getTelefone_res()); // Substituir com o getter
                inserirContatoStmt.executeUpdate();

                // Capturar id_curso correspondente ao curso escolhido
                buscarCursoIdStmt.setString(1, usuario.getCurso());

                ResultSet cursoResultSet = buscarCursoIdStmt.executeQuery();

                if (cursoResultSet.next()) {
                    int cursoId = cursoResultSet.getInt("id_curso");

                    // Inserir na tabela user_cursos
                    inserirUserCursoStmt.setInt(1, userId);
                    inserirUserCursoStmt.setInt(2, cursoId);
                    inserirUserCursoStmt.executeUpdate();
                }
            }

            connection.commit();
            return true; // Sucesso mlk

        } catch (SQLException e) {
            connection.rollback();
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

    
    //Fechamento da classe para nao se perder nessa porra

}
