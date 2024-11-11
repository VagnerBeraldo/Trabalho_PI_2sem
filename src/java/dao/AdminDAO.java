package dao;

import databaseConnection.DatabaseConnection;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Administrador;

import model.Usuario;
import model.Endereco;
import model.UsuarioEnderecoDTO;

public class AdminDAO {

    public static boolean atualizaUser(Usuario usuario, Endereco endereco) throws ClassNotFoundException, SQLException {
        
        Connection connection = null;
        PreparedStatement PS = null;
       

           String updateUsersSQL = "UPDATE users SET nome = ?, sobrenome = ?, nome_social = ?, email = ?, tipo_pagamento = ? WHERE id_user = ?";
           String updateEnderecoSQL = "UPDATE endereco_users SET cep = ?, rua = ?, numero = ?, complemento = ?, bairro = ?, cidade = ? WHERE usuario_id = ?";
           String updateContatoUsersSQL = "UPDATE contato_users SET numero_telefone_celular = ? ,numero_telefone_residencial = ? WHERE usuario_id = ?";
          
           String buscarCursoIdSQL = "SELECT id_curso FROM cursos WHERE materia = ?";
           String updateUserCursosSQL = "UPDATE user_cursos SET id_curso = ? WHERE id_user = ?";
         
        
        

        try {
            connection = DatabaseConnection.getConnection();

            PreparedStatement updateUsuarioStmt = connection.prepareStatement(updateUsersSQL);
            PreparedStatement updateEnderecoStmt = connection.prepareStatement(updateEnderecoSQL);
            PreparedStatement updateContatoStmt = connection.prepareStatement(updateContatoUsersSQL);
            PreparedStatement buscarCursoIdStmt = connection.prepareStatement(buscarCursoIdSQL);
            PreparedStatement updateUserCursoStmt = connection.prepareStatement(updateUserCursosSQL);

            connection.setAutoCommit(false);
            
            // Busca o ID do curso com base no nome da matéria
            buscarCursoIdStmt.setString(1, usuario.getCurso());
            ResultSet rsCurso = buscarCursoIdStmt.executeQuery();
            int cursoId = -1;
                if (rsCurso.next()) {
                    cursoId = rsCurso.getInt("id_curso");
                } else {
                    connection.rollback();  // Faz rollback se o curso não for encontrado
                    throw new SQLException("Curso não encontrado para a matéria: " + usuario.getCurso());
                 }

                //Atualizando os campos do usuario na tabela users 
                updateUsuarioStmt.setString(1, usuario.getNome());
                updateUsuarioStmt.setString(2, usuario.getSobrenome());
                updateUsuarioStmt.setString(3, usuario.getNome_social());
                updateUsuarioStmt.setString(4, usuario.getEmail());
                updateUsuarioStmt.setString(5, usuario.getTipo_pagamento());
                updateUsuarioStmt.setInt(6, usuario.getId_user());

                updateUsuarioStmt.executeUpdate();

       

                // Atualizar os campos do endereço do usuario relacionado ao ID dele                
                updateEnderecoStmt.setString(1, endereco.getCep());
                updateEnderecoStmt.setString(2, endereco.getRua());
                updateEnderecoStmt.setString(3, endereco.getNumero());
                updateEnderecoStmt.setString(4, endereco.getComplemento());
                updateEnderecoStmt.setString(5, endereco.getBairro());
                updateEnderecoStmt.setString(6, endereco.getCidade());
                updateEnderecoStmt.setInt(7, usuario.getId_user());
                
                updateEnderecoStmt.executeUpdate();
                

                //Atualizar contato usuario
               
                updateContatoStmt.setString(1, usuario.getTelefone_cel()); // Substituir com o getter
                updateContatoStmt.setString(2, usuario.getTelefone_res()); // Substituir com o getter
                updateContatoStmt.setInt(3, usuario.getId_user());
                
                updateContatoStmt.executeUpdate();
                
                

                //Atualizar o campo ID curso recuperado anteriormente no campo relacionado ao ID do usuario
                updateUserCursoStmt.setInt(1, cursoId);
                updateUserCursoStmt.setInt(2, usuario.getId_user());
               
                updateUserCursoStmt.executeUpdate();
                
            

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

    public static Administrador loginAdmin(Administrador administrador) throws ClassNotFoundException {
        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;
       
        
        try{ 
            
            connection = DatabaseConnection.getConnection();
            
            String sql = "select * from administrador where email = ? and senha = ?;";
            
            
             PS = connection.prepareStatement(sql);
             
             PS.setString(1, administrador.getEmail()); // Substitui o parâmetro ? pelo email do adm
             PS.setString(2, administrador.getSenha());  //substitui o parametro  ? pela senha do adm
             
            resultSet = PS.executeQuery();
            
            if (resultSet.next()) {
                administrador.setId_admin(resultSet.getInt("id_admin"));
                administrador.setEmail(resultSet.getString("email"));
                administrador.setSenha(resultSet.getString("senha"));
                // Defina outros atributos do usuário, se necessário.
                return administrador;
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
                usuario.setTelefone_res(resultSet.getString("numero_telefone_residencial"));

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
