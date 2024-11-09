
package servlets;

import controller.AdminController;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminServlet extends HttpServlet {
    
    private AdminController adminController;

    private static final String CADASTRAR = "cadastrar";
    private static final String DELETAR = "deletar";
    private static final String BUSCAR = "buscar";
    private static final String ATUALIZAR = "atualizar";
    private static final String LISTAR = "listar";
    
    
    
      /*   
         //Comando para debug das rotas e valores de metodos
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>metodo crud: " + metodocrud + "</h1>");
        out.println("<h1>nome: " + nombre + "</h1>");
        out.println("</body></html>"); 
         */
    
    
    @Override
    public void init() throws ServletException {
        super.init();
        adminController = new AdminController();
    }


   @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String metodocrud = request.getParameter("metodo");
         
        
  
        // Obtenha a ação
  
        if (metodocrud == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não especificada.");
            return; // Retorne imediatamente se a ação for nula
        }

        try {
            switch (metodocrud) {

                case BUSCAR:
                    adminController.buscarUsuarioPorId(request, response);
                    break;

                    
                case LISTAR:
                    adminController.listarUsuarios(request, response);
                    break;
                    
               case ATUALIZAR:
                    adminController.buscarPorIdAtualizar(request, response);
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

        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");

        String metodocrud = request.getParameter("metodo");
      
       
        
        if (metodocrud == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não especificada.");
            return; // Retorne imediatamente se a ação for nula
        }

        try {
            switch (metodocrud) {
                case CADASTRAR:
                      adminController.cadastrarUsuario(request, response);
                    break;
                            
                case ATUALIZAR:
                    
                      adminController.atualizarUsuario(request, response);                             
                    break;
                    

                case DELETAR:

                      adminController.deletarUsuarioPorID(request, response);
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
}

