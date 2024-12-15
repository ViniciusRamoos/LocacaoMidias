package locacaomidias.controladores;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import locacaomidias.dao.AtorDAO;
import locacaomidias.entidades.Ator;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
@WebServlet(name = "AtoresServlet", urlPatterns = {"/processaAtores"})
public class AtoresServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try ( AtorDAO dao = new AtorDAO() ) {
            
            if ( acao.equals("inserir") ) {
                
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("dataEstreia");

                Ator ator = new Ator();

                ator.setNome(nome);
                ator.setSobrenome(sobrenome);
                ator.setDataEstreia(Utils.getDate(dataEstreia));

                Utils.validar(ator, "id");
                dao.salvar(ator);
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp");
                
            } else if ( acao.equals("alterar") ) {
                
                Long id = Utils.getLong(request, "id");
                String nome = request.getParameter("nome");
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("dataEstreia");
                
                Ator ator = dao.obterPorId(id);
                
                ator.setNome(nome);
                ator.setSobrenome(sobrenome);
                ator.setDataEstreia(Utils.getDate(dataEstreia));
                
                Utils.validar(ator);
                dao.atualizar(ator);
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp");
                
            } else if ( acao.equals("excluir") ) {
                
                Long id = Utils.getLong(request, "id");
                Ator ator = dao.obterPorId(id);
                
                dao.excluir(ator);
                disp = request.getRequestDispatcher(
                        "/formularios/ator/listagem.jsp");
            } else {
                
                Long id = Utils.getLong(request, "id");
                Ator ator = dao.obterPorId(id);
                request.setAttribute("ator", ator);
                
                if ( acao.equals("prepararAlteracao") ) {
                    disp = request.getRequestDispatcher(
                        "/formularios/ator/alterar.jsp");
                } else if ( acao.equals("prepararExclusao") ) {
                    disp = request.getRequestDispatcher(
                        "/formularios/ator/excluir.jsp");
                }
            }
            
        } catch ( SQLException e ) {
            disp = Utils.prepararDespachoErro(request, e.getMessage());
        }
        
        if ( disp != null ) {
            disp.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
