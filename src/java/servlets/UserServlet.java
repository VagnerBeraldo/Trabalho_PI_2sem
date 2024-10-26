/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.UsuarioController;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserServlet extends HttpServlet {

    private UsuarioController usuarioController;

    private static final String CADASTRAR = "cadastrar";
    private static final String DELETAR = "deletar";
    private static final String BUSCAR = "buscar";
    private static final String ATUALIZAR = "atualizar";

    @Override
    public void init() throws ServletException {
        super.init();
        usuarioController = new UsuarioController();
    }

    @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
    String action = request.getParameter("action"); // Obtenha a ação

    if (action == null) {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não especificada.");
        return; // Retorne imediatamente se a ação for nula
    }

    try {
        switch (action) {
            case CADASTRAR:
                usuarioController.cadastrarUsuario(request, response);
                break;

            case DELETAR:
                usuarioController.deletarUsuarioPorID(request, response);
                break;

            case BUSCAR:
                usuarioController.buscarUsuarioPorId(request, response);
                break;

            case ATUALIZAR:
                // Chame o método de atualizar aqui, se implementado
                // usuarioController.atualizarUsuario(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida."); // Tratamento para ação não reconhecida
                break;
        }
    } catch (ClassNotFoundException ex) {
        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro ao processar a ação."); // Retornar erro genérico
    } catch (Exception e) {
        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, e);
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro inesperado."); // Captura de erros inesperados
    }
}


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("user_name");
        String senha = request.getParameter("user_email");

        String acao = request.getParameter("action");
        

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
