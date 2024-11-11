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

    //Metodo para listar todos os usuarios, Retorna uma Lista<> de usuarios
    public List<UsuarioEnderecoDTO> listarUsuarios() throws ClassNotFoundException {

        List<UsuarioEnderecoDTO> usuarios = new ArrayList<>();

        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.DatabaseConnection.getConnection();

            String sql = "SELECT users.id_user, users.nome, users.sobrenome, users.nome_social, "
                    + "users.data_nascimento, users.email, users.tipo_pagamento, "
                    + "endereco_users.rua, endereco_users.bairro, endereco_users.numero, "
                    + "endereco_users.cep, endereco_users.estado, endereco_users.cidade, "
                    + "contato_users.numero_telefone_celular, contato_users.numero_telefone_residencial, "
                    + "cursos.materia "
                    + "FROM users "
                    + "LEFT JOIN endereco_users ON users.id_user = endereco_users.usuario_id "
                    + "LEFT JOIN contato_users ON users.id_user = contato_users.usuario_id "
                    + "LEFT JOIN user_cursos ON users.id_user = user_cursos.id_user "
                    + "LEFT JOIN cursos ON user_cursos.id_curso = cursos.id_curso";

            PS = connection.prepareStatement(sql);
            resultSet = PS.executeQuery();

            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                Endereco endereco = new Endereco();

                usuario.setId_user(resultSet.getInt("id_user"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setSobrenome(resultSet.getString("sobrenome"));
                usuario.setNome_social(resultSet.getString("nome_social"));
                usuario.setData_nascimento(resultSet.getString("data_nascimento"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipo_pagamento(resultSet.getString("tipo_pagamento"));

                endereco.setRua(resultSet.getString("rua"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setNumero(resultSet.getString("numero"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setEstado(resultSet.getString("estado"));
                endereco.setCidade(resultSet.getString("cidade"));

                usuario.setTelefone_cel(resultSet.getString("numero_telefone_celular"));
                usuario.setTelefone_res(resultSet.getString("numero_telefone_residencial"));
                usuario.setCurso(resultSet.getString("materia"));

                // Adiciona o objeto DTO à lista
                usuarios.add(new UsuarioEnderecoDTO(usuario, endereco));
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

         return usuarios; // Retorna a lista com todos os usuários e endereços
    }

    //Metodo para buscar usuario pelo ID
    public UsuarioEnderecoDTO buscarUsuarioPorID(Usuario usuario) throws ClassNotFoundException {

        Connection connection = null;
        PreparedStatement PS = null;
        ResultSet resultSet = null;
        Endereco endereco = new Endereco(); // Declare e inicialize a variável

        try {
            connection = DatabaseConnection.getConnection();

            String sql = "SELECT users.id_user, users.nome, users.sobrenome, users.nome_social, "
                    + "users.data_nascimento, users.email, users.tipo_pagamento, "
                    + "endereco_users.rua, endereco_users.bairro, endereco_users.numero, "
                    + "endereco_users.cep, endereco_users.estado, endereco_users.cidade, "
                    + "contato_users.numero_telefone_celular, contato_users.numero_telefone_residencial, "
                    + "cursos.materia "
                    + "FROM users "
                    + "LEFT JOIN endereco_users ON users.id_user = endereco_users.usuario_id "
                    + "LEFT JOIN contato_users ON users.id_user = contato_users.usuario_id "
                    + "LEFT JOIN user_cursos ON users.id_user = user_cursos.id_user "
                    + "LEFT JOIN cursos ON user_cursos.id_curso = cursos.id_curso "
                    + "WHERE users.id_user = ?";

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
                usuario.setData_nascimento(resultSet.getString("data_nascimento"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setTipo_pagamento(resultSet.getString("tipo_pagamento"));

                endereco.setRua(resultSet.getString("rua"));
                endereco.setBairro(resultSet.getString("bairro"));
                endereco.setNumero(resultSet.getString("numero"));
                endereco.setCep(resultSet.getString("cep"));
                endereco.setEstado(resultSet.getString("estado"));
                endereco.setCidade(resultSet.getString("cidade"));
                usuario.setTelefone_cel(resultSet.getString("numero_telefone_celular"));
                usuario.setTelefone_res(resultSet.getString("numero_telefone_residencial"));
                usuario.setCurso(resultSet.getString("materia"));

                return new UsuarioEnderecoDTO(usuario, endereco);

                //adaptação com DTO para retornar duas classes juntas
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

    public boolean DeletarUserPorID(Usuario usuario) throws ClassNotFoundException, SQLException {

        Connection connection = null;
        PreparedStatement PS = null;

        try {

            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            String sql = "DELETE FROM users WHERE id_user = ?";

            PS = connection.prepareStatement(sql);
            PS.setInt(1, usuario.getId_user()); // Substitui o parâmetro ? pelo ID

            int linhasAfetadas = PS.executeUpdate(); // Executa a atualização
            connection.commit();
            return linhasAfetadas > 0;
            // Retorna true se pelo menos uma linha foi afetada (ou seja, o usuário foi deletado)

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
            DatabaseConnection.closeConnection(connection);
        }
    }

    //Fechamento da classe para nao se perder nessa porra

}
