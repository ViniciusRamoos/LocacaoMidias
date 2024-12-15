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
import locacaomidias.dao.ClassificacaoEtariaDAO;
import locacaomidias.dao.ClassificacaoInternaDAO;
import locacaomidias.dao.GeneroDAO;
import locacaomidias.dao.MidiaDAO;
import locacaomidias.dao.TipoDAO;
import locacaomidias.entidades.Ator;
import locacaomidias.entidades.ClassificacaoEtaria;
import locacaomidias.entidades.ClassificacaoInterna;
import locacaomidias.entidades.Genero;
import locacaomidias.entidades.Midia;
import locacaomidias.entidades.Tipo;
import locacaomidias.utils.Utils;

/**
 *
 * @author User
 */
@WebServlet(name = "MidiasServlet", urlPatterns = {"/processaMidias"})
public class MidiasServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String acao = request.getParameter("acao");
        RequestDispatcher disp = null;
        
        try ( MidiaDAO daoMidia = new MidiaDAO(); 
              AtorDAO daoAtor = new AtorDAO();
              GeneroDAO daoGenero = new GeneroDAO();
              ClassificacaoEtariaDAO daoCE = new ClassificacaoEtariaDAO();
              TipoDAO daoTipo = new TipoDAO();
              ClassificacaoInternaDAO daoCI = new ClassificacaoInternaDAO() ) {
            
            if ( acao.equals("inserir") ) {
                
                String titulo = request.getParameter("titulo");
                String anoLancamento = request.getParameter("anoLancamento");
                String codigoBarras = request.getParameter("codigoBarras");
                Long duracaoEmMinutos = Utils.getLong(
                        request, "duracaoEmMinutos");
                Long idAtorPrincipal = Utils.getLong(
                        request, "idAtorPrincipal");
                Long idAtorCoadjuvante = Utils.getLong(
                        request, "idAtorCoadjuvante");
                Long idGenero = Utils.getLong(
                        request, "idGenero");
                Long idClassificacaoEtaria = Utils.getLong(
                        request, "idClassificacaoEtaria");
                Long idTipo = Utils.getLong(
                        request, "idTipo");
                Long idClassificacaoInterna = Utils.getLong(
                        request, "idClassificacaoInterna");
                
                Ator atorPrincipal = daoAtor.obterPorId(idAtorPrincipal);
                Ator atorCoadjuvante = daoAtor.obterPorId(idAtorCoadjuvante);
                Genero genero = daoGenero.obterPorId(idGenero);
                ClassificacaoEtaria ce = daoCE.obterPorId(idClassificacaoEtaria);
                Tipo tipo = daoTipo.obterPorId(idTipo);
                ClassificacaoInterna ci = daoCI.obterPorId(idClassificacaoInterna);
                
                Midia midia = new Midia();
                
                midia.setTitulo(titulo);
                midia.setAnoLancamento(anoLancamento);
                midia.setCodigoBarras(codigoBarras);
                midia.setDuracaoMinutos(duracaoEmMinutos);
                midia.setAtorPrincipal(atorPrincipal);
                midia.setAtorCoadjuvante(atorCoadjuvante);
                midia.setGenero(genero);
                midia.setClassificacaoEtaria(ce);
                midia.setTipo(tipo);
                midia.setClassificacaoInterna(ci);
                
                Utils.validar(midia, "id");
                daoMidia.salvar(midia);
                disp = request.getRequestDispatcher(
                        "/formularios/midias/listagem.jsp" );
                
            } else if ( acao.equals("alterar") ) {
                
                Long id = Utils.getLong(request, "id");
                String titulo = request.getParameter("titulo");
                String anoLancamento = request.getParameter("anoLancamento");
                String codigoBarras = request.getParameter("codigoBarras");
                Long duracaoEmMinutos = Utils.getLong(
                        request, "duracaoEmMinutos");
                Long idAtorPrincipal = Utils.getLong(
                        request, "idAtorPrincipal");
                Long idAtorCoadjuvante = Utils.getLong(
                        request, "idAtorCoadjuvante");
                Long idGenero = Utils.getLong(
                        request, "idGenero");
                Long idClassificacaoEtaria = Utils.getLong(
                        request, "idClassificacaoEtaria");
                Long idTipo = Utils.getLong(
                        request, "idTipo");
                Long idClassificacaoInterna = Utils.getLong(
                        request, "idClassificacaoInterna");
                
                Midia midia = daoMidia.obterPorId(id);
                Ator atorPrincipal = daoAtor.obterPorId(idAtorPrincipal);
                Ator atorCoadjuvante = daoAtor.obterPorId(idAtorCoadjuvante);
                Genero genero = daoGenero.obterPorId(idGenero);
                ClassificacaoEtaria ce = daoCE.obterPorId(idClassificacaoEtaria);
                Tipo tipo = daoTipo.obterPorId(idTipo);
                ClassificacaoInterna ci = daoCI.obterPorId(idClassificacaoInterna);
                
                midia.setTitulo(titulo);
                midia.setAnoLancamento(anoLancamento);
                midia.setCodigoBarras(codigoBarras);
                midia.setDuracaoMinutos(duracaoEmMinutos);
                midia.setAtorPrincipal(atorPrincipal);
                midia.setAtorCoadjuvante(atorCoadjuvante);
                midia.setGenero(genero);
                midia.setClassificacaoEtaria(ce);
                midia.setTipo(tipo);
                midia.setClassificacaoInterna(ci);
                
                Utils.validar(midia);
                daoMidia.atualizar(midia);
                disp = request.getRequestDispatcher(
                        "/formularios/midias/listagem.jsp" );
                
            } else if ( acao.equals("excluir") ) {
                
                Long id = Utils.getLong(request, "id");
                Midia midia = daoMidia.obterPorId(id);
                
                daoMidia.excluir(midia);
                disp = request.getRequestDispatcher(
                        "/formularios/midias/listagem.jsp" );
            } else {
                
                Long id = Utils.getLong(request, "id");
                Midia midia = daoMidia.obterPorId(id);
                request.setAttribute("midia", midia);
                
                if ( acao.equals("prepararAlteracao") ) {
                    disp = request.getRequestDispatcher(
                        "/formularios/midias/alterar.jsp" );
                } else if ( acao.equals("prepararExclusao") ) {
                    disp = request.getRequestDispatcher(
                        "/formularios/midias/excluir.jsp" );
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
