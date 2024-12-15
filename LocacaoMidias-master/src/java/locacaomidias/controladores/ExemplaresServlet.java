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
import locacaomidias.dao.MidiaDAO;
import locacaomidias.dao.ExemplarDAO;
import locacaomidias.entidades.Exemplar;
import locacaomidias.entidades.Midia;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
@WebServlet(name = "ExemplaresServlet", urlPatterns = {"/processaExemplares"})
public class ExemplaresServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try ( ExemplarDAO daoExemplar = new ExemplarDAO();
              MidiaDAO daoMidia = new MidiaDAO() ) {
            
            if ( acao.equals("inserir") ) {
                
                Long idMidia = Utils.getLong(request, "idMidia");
                
                Midia midia = daoMidia.obterPorId(idMidia);
                
                Exemplar exemplar = new Exemplar();
                
                exemplar.setDisponivel(true);
                exemplar.setMidia(midia);
                
                Utils.validar(exemplar, "codigoInterno");
                daoExemplar.salvar(exemplar);
                disp = request.getRequestDispatcher(
                        "/formularios/exemplares/listagem.jsp");
            } else if ( acao.equals("alterar") ) {
                
                Long codigoInterno = Utils.getLong(request, 
                        "codigoInterno");
                
                Long idMidia = Utils.getLong(request, "idMidia");
                
                Exemplar exemplar = daoExemplar.obterPorId(codigoInterno);
                
                Midia midia = daoMidia.obterPorId(idMidia);
                
                exemplar.setMidia(midia);
                
                Utils.validar(exemplar);
                daoExemplar.atualizar(exemplar);
                disp = request.getRequestDispatcher(
                        "/formularios/exemplares/listagem.jsp");
                
            } else if ( acao.equals("excluir") ) {
                
                Long codigoInterno = Utils.getLong(request, 
                        "codigoInterno");
                
                Exemplar exemplar = daoExemplar.obterPorId(codigoInterno);
                
                daoExemplar.excluir(exemplar);
                disp = request.getRequestDispatcher(
                        "/formularios/exemplares/listagem.jsp");
            } else {
                
                Long codigoInterno = Utils.getLong(request, 
                        "codigoInterno");
                
                Exemplar exemplar = daoExemplar.obterPorId(codigoInterno);
                request.setAttribute("exemplar", exemplar);
                
                if ( acao.equals("prepararAlteracao") ) {
                    disp = request.getRequestDispatcher(
                        "/formularios/exemplares/alterar.jsp");
                } else if ( acao.equals("prepararExclusao") ) {
                    disp = request.getRequestDispatcher(
                        "/formularios/exemplares/excluir.jsp");
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
