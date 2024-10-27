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

public class UsuarioDAO {

    //metodo para cadastrar usuario
    public boolean cadastrarUsuario(Usuario usuario, Endereco endereco) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement PS = null;

        String inserirUsuarioSQL = "INSERT INTO users (nome, sobrenome, nome_social, cpf, data_nascimento, email, tipo_pagamento) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            inserirUsuarioStmt.setString(6, usuario.getEmail());
            inserirUsuarioStmt.setString(7, usuario.getTipo_pagamento());

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

                usuario.setId_user(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSobrenome(resultSet.getString("sobrenome"));
                usuario.setNome_social(resultSet.getString("nome_social"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setData_nascimento(resultSet.getString("data_nascimento"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipo_pagamento(resultSet.getString("tipo_pagamento"));

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

                usuario.setId_user(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSobrenome(resultSet.getString("sobrenome"));
                usuario.setNome_social(resultSet.getString("nome_social"));
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setData_nascimento(resultSet.getString("data_nascimento"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipo_pagamento(resultSet.getString("tipo_pagamento"));
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
