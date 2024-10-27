package controller;

import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class UsuarioController extends HttpServlet {

    private UsuarioDAO usuarioDAO;

    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }

    public void cadastrarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        // Obtendo os parâmetros do formulário
        String nome = request.getParameter("user_nome");
        String sobrenome = request.getParameter("user_sobrenome");
        String nomeSocial = request.getParameter("user_nomeSocial");
        String cpf = request.getParameter("user_cpf");
        String nascimento = request.getParameter("user_nascimento");
        String email = request.getParameter("user_email");
        String tipo_pagamento = "PIX"; // Valor fixo para tipo de pagamento

        // Criando o objeto Usuario
        Usuario usuario = new Usuario();
        usuario.setNome(nome);
        usuario.setSobrenome(sobrenome);
        usuario.setNomeSocial(nomeSocial);
        usuario.setCpf(cpf);
        usuario.setDataNascimento(nascimento);
        usuario.setEmail(email);
        usuario.setTipoPagamento(tipo_pagamento);

        boolean result = usuarioDAO.cadastrarUsuario(usuario);

        if (result) {
            // Cadastro bem-sucedido
            // Redireciona para a página de login com uma mensagem de sucesso
            response.sendRedirect("/Trabalho_PI_2MA/viewsJSP/usuarioViews/cadastroSucesso.jsp");

        } else {
            // Falha ao cadastrar
            // Redireciona para a página de cadastro com uma mensagem de erro
           response.sendRedirect("/Trabalho_PI_2MA/viewsJSP/usuarioViews/cadastroErro.jsp");
        }

    }

    public void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarUsuarios.jsp");
        dispatcher.forward(request, response);
    }

    public void buscarUsuarioPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));

        Usuario usuario = usuarioDAO.buscarUsuarioPorID(id);

        request.setAttribute("usuario", usuario);

        RequestDispatcher dispatcher = request.getRequestDispatcher("detalhesUsuario.jsp");

        dispatcher.forward(request, response);
    }

    public void deletarUsuarioPorID(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        
        boolean sucesso = usuarioDAO.DeletarUserPorID(id);

        if (sucesso) {
            request.setAttribute("mensagem", "Usuário Excluido com Sucesso!");

        }

        listarUsuarios(request, response); // Redireciona para a lista após a exclusão
    }

}
