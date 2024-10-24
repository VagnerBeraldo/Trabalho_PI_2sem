
package controller;



import dao.UsuarioDAO;
import model.Usuario;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Set;


public class UsuarioController extends HttpServlet{
    private UsuarioDAO usuarioDAO;
    
    
    public UsuarioController() {
        this.usuarioDAO = new UsuarioDAO();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acao = request.getParameter("acao");

        switch (acao) {
            case "listar":
                listarUsuarios(request, response);
                break;
            case "buscar":
                buscarUsuarioPorId(request, response);
                break;
            case "deletar":
                deletarUsuario(request, response);
                break;
            default:
                listarUsuarios(request, response); // Ação padrão
                break;
        }
    }
    
     private void listarUsuarios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Usuario> usuarios = usuarioDAO.listarUsuarios();
        request.setAttribute("usuarios", usuarios);
        RequestDispatcher dispatcher = request.getRequestDispatcher("listarUsuarios.jsp");
        dispatcher.forward(request, response);
    }

    private void buscarUsuarioPorId(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        
        Usuario usuario = usuarioDAO.buscarUsuarioPorID(id);
        
        request.setAttribute("usuario", usuario);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("detalhesUsuario.jsp");
        
        dispatcher.forward(request, response);
    }

    private void deletarUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        boolean sucesso = usuarioDAO.DeletarUserPorID(id);
        
        if (sucesso) {
            request.setAttribute("mensagem", "Usuário Excluido com Sucesso!");
            
        }
        
        listarUsuarios(request, response); // Redireciona para a lista após a exclusão
    }
    
      
}


