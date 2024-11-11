package controller;

import dao.UsuarioDAO;
import model.Usuario;
import model.Endereco;
import model.UsuarioEnderecoDTO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpSession;

public class UsuarioController extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void loginUsuario(HttpServletRequest request, HttpServletResponse response) throws IOException, ClassNotFoundException {

        String senha = request.getParameter("user_senha");
        String email = request.getParameter("user_email");

       Usuario usuario = new Usuario();

        usuario.setEmail(email);
        usuario.setSenha(senha);

        Usuario result = usuarioDAO.loginUser(usuario);

        //boolean teste = result;
        if (result != null) {
            // Login bem-sucedido

            // Login bem-sucedido - adiciona o administrador à sessão
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogado", result); // Armazena o objeto administrador logado na sessão , usuario logado é enviado para a adm.jsp

             // Redireciona para a página CRUD da administração
            response.sendRedirect(request.getContextPath() + "/viewsJSP/usuarioViews/cliente.jsp");

        } else {
            // Falha ao cadastrar
            // Falha ao logar - redireciona para a página de login administrativo
            response.sendRedirect(request.getContextPath() + "/viewsJSP/usuarioViews/login.jsp");
        }

    }
    
    
    public void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, SQLException {

        // Obtendo os parâmetros do formulário para a classe usuario
        String nome = request.getParameter("user_nome");
        String sobrenome = request.getParameter("user_sobrenome");
        String nomeSocial = request.getParameter("user_nomeSocial");
        String cpf = request.getParameter("user_cpf");
        String nascimento = request.getParameter("user_nascimento");
        String senha = request.getParameter("user_senha");
        String email = request.getParameter("user_email");
        String tipo_pagamento = request.getParameter("user_pagamento");
        String curso = request.getParameter("curso_user");
        String tel_celular = request.getParameter("user_telefoneCelular");
        String tel_resid = request.getParameter("user_telefoneResid");
        String cep = request.getParameter("user_ender_cep");
        String rua = request.getParameter("user_ender_rua");
        String numero = request.getParameter("user_ender_numero");
        String complemento = request.getParameter("user_ender_complemento");
        String bairro = request.getParameter("user_ender_bairro");
        String cidade = request.getParameter("user_ender_cidade");
        String estado = request.getParameter("user_ender_estado");

         //Filtros para caso o usuario não inserir nada no campo de telefones resd e celular , string vazias causam Entrada Duplicada em campos Uniques
        if (tel_celular == null || tel_celular.trim().isEmpty()) {
            tel_celular = null;
        }

        if (tel_resid == null || tel_resid.trim().isEmpty()) {
            tel_resid = null;
        }

        // Criando o objeto Usuario
        Usuario usuario = new Usuario();

        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setNome_social(nomeSocial);
        usuario.setCpf(cpf);
        usuario.setData_nascimento(nascimento);
        usuario.setSenha(senha);
        usuario.setEmail(email);
        usuario.setTipo_pagamento(tipo_pagamento);
        usuario.setCurso(curso);
        usuario.setTelefone_cel(tel_celular);
        usuario.setTelefone_res(tel_resid);

        //Criando o Objeto Endereco
        Endereco endereco = new Endereco();

        endereco.setCep(cep);
        endereco.setRua(rua);
        endereco.setNumero(numero);
        endereco.setComplemento(complemento);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);

        boolean result = usuarioDAO.cadastrarUsuario(usuario, endereco);

        //boolean teste = result;
        if (result) {
            // Cadastro bem-sucedido
            // Redireciona para a página de testes sucesso com uma mensagem de sucesso
            response.sendRedirect(request.getContextPath() + "/index.html");

        } else {
            // Falha ao cadastrar
            // Redireciona para a página de testes erro com uma mensagem de erro
            response.sendRedirect("/Trabalho_PI_2MA/viewsJSP/usuarioViews/cadastroErro.jsp");
        }

    }

    //Listar todos os ususarios e retornar uma lista
    public void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {

        // Obter a lista de usuários com endereços do DAO
        List<UsuarioEnderecoDTO> usuarioEnderecoDTOList = usuarioDAO.listarUsuarios();

        // Adiciona a lista ao request para ser acessada na JSP
        if (usuarioEnderecoDTOList != null && !usuarioEnderecoDTOList.isEmpty()) {
            request.setAttribute("usuarios", usuarioEnderecoDTOList); // Atributo 'usuarios' passa toda a lista
        } else {
            request.setAttribute("error", "Nenhum usuário encontrado.");
        }

        // Encaminha para a página JSP de listagem
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewsJSP/listarTodosUsuarios.jsp");
        dispatcher.forward(request, response);
    }

    ///retornar um unico usuario baseado na busca por id    
    public void buscarUsuarioPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {

        int user_id = Integer.parseInt(request.getParameter("id_user"));

        // Criando o objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setId_user(user_id);

        // Obtendo o UsuarioEnderecoDTO
        UsuarioEnderecoDTO usuarioEnderecoDTO = usuarioDAO.buscarUsuarioPorID(usuario);

        if (usuarioEnderecoDTO != null) {
            // Colocando o Usuario e o Endereco no request
            request.setAttribute("usuario", usuarioEnderecoDTO.getUsuario());
            request.setAttribute("endereco", usuarioEnderecoDTO.getEndereco());
        } else {
            // Se não houver usuário com o ID fornecido, você pode enviar uma mensagem de erro ou redirecionar
            request.setAttribute("error", "Usuário não encontrado.");
        }

        //Encaminhando para a view
        RequestDispatcher dispatcher = request.getRequestDispatcher("/viewsJSP/listarUsuarios.jsp");
        dispatcher.forward(request, response);

    }

    //Deletar um usuario e retornar um true
    public void deletarUsuarioPorID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException, SQLException {
        int user_id = Integer.parseInt(request.getParameter("id_user"));

        // Criando o objeto Usuario
        Usuario usuario = new Usuario();

        usuario.setId_user(user_id);

        boolean sucesso = usuarioDAO.DeletarUserPorID(usuario);

        if (sucesso) {
            // Redireciona para a página de testes sucesso com uma mensagem de sucesso
            response.sendRedirect("/Trabalho_PI_2MA/adm.html");

        } else {
            // Falha ao cadastrar
            // Redireciona para a página de testes erro com uma mensagem de erro
            response.sendRedirect("/Trabalho_PI_2MA/viewsJSP/usuarioViews/cadastroErro.jsp");
        }

        // listarUsuarios(request, response); // Redireciona para a lista após a exclusão
    }

}
